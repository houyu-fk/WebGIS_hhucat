import psycopg2
import os
from pathlib import Path

# ==================== 配置 ====================
IMAGE_FOLDER = r"D:/university_documents/2025-2026学习资料/WebGIS/hhucat/backend/src/main/resources/cat_image"

DB_CONFIG = {
    "dbname": "hhucat",
    "user": "postgres",
    "password": "1360qtip",  # ←←← 改成正确密码
    "host": "localhost",
    "port": "5432"
}


# =============================================

def get_db_connection():
    return psycopg2.connect(**DB_CONFIG)


def update_existing_cats():
    conn = get_db_connection()
    cur = conn.cursor()

    success = 0
    not_found = 0
    failed = 0

    print("开始为已有猫咪更新图片...\n")

    for filename in os.listdir(IMAGE_FOLDER):
        if not filename.lower().endswith(('.jpg', '.jpeg', '.png', '.gif')):
            continue

        cat_name = Path(filename).stem  # 如 "黑猫"
        filepath = os.path.join(IMAGE_FOLDER, filename)

        try:
            # 先尝试更新已有记录
            cur.execute('''
                UPDATE cats 
                SET 
                    image_path = %s,
                    image_filename = %s,
                    image_url = '/static/cat_images/' || %s,   -- 根据你的前端需求调整
                    updated_at = NOW()
                WHERE breed = %s
                RETURNING id, breed
            ''', (filepath, filename, filename, cat_name))

            result = cur.fetchone()

            if result:
                conn.commit()
                print(f"✅ 更新成功 ID={result[0]} | {result[1]} ← {filename}")
                success += 1
            else:
                # 如果没找到对应名称的猫，可以选择跳过或提示
                print(f"⚠️  未找到匹配的猫咪: {cat_name}（文件名：{filename}）")
                not_found += 1
                # 可选：如果你想自动新建，就在这里加 INSERT 逻辑

        except Exception as e:
            conn.rollback()
            print(f"❌ 更新失败 {filename}: {e}")
            failed += 1

    cur.close()
    conn.close()
    print(f"\n🎉 更新完成！成功更新: {success} 张，未找到匹配: {not_found} 张，失败: {failed} 张")


if __name__ == "__main__":
    update_existing_cats()