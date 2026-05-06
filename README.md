# 河海校园猫咪地图 (webgis_hhucat)

## 项目简介
面向河海大学师生的校园流浪动物管理小程序，支持地图标记猫咪位置、创建猫咪档案、偶遇打卡互动、发布救助/领养公告以及志愿者协同管理。

## 技术栈
| 层级 | 技术 | 版本 |
|------|------|------|
| 前端 | Vue 3 + Vite | 3.x |
| 样式 | Tailwind CSS | 3.x |
| 地图 | 高德地图 JS API | 2.0 |
| 后端 | Spring Boot | 3.2.5 |
| 构建 | Maven | 3.9+ |
| 数据库 | PostgreSQL | 16+ |
| ORM | Spring Data JPA (Hibernate) | — |
| 工具 | Lombok | — |

## 启动步骤

### 1. 数据库配置
- 安装并启动 PostgreSQL 服务  
- 创建数据库（命令行示例）：
  ```bash
  psql -U postgres -c "CREATE DATABASE hhucat;"
  导入表结构（在项目根目录执行）：

bash
psql -U postgres -d hhucat -f sql/init.sql
修改后端配置文件 backend/src/main/resources/application.yml 中的数据库用户名和密码：

yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/hhucat
    username: postgres       
    password: 1360qtip        
### 2. 后端运行
bash
cd backend
mvn clean spring-boot:run
后端默认运行在 http://localhost:8080。

### 3. 前端运行
bash
cd frontend
npm install
npm run dev
前端开发服务器默认运行在 http://localhost:5173，已配置代理转发 /api 请求到后端。

### 接口入口
基础地址：http://localhost:8080/api

### 接口文档：见 docs/API.md

### 项目结构
text
hhucat/
├── backend/          # Spring Boot 后端项目
├── frontend/         # Vue 3 前端项目
├── sql/              # 数据库初始化脚本
│   └── init.sql
├── docs/             # 需求文档、API 文档
│   └── API.md
├── README.md
└── .gitignore