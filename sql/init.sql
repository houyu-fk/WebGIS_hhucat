

-- ========== 可选：启用 PostGIS 空间扩展以支持地理位置查询 ==========
-- 如有定位附近猫咪的需求，可执行：
CREATE EXTENSION IF NOT EXISTS postgis;
-- 然后在 cats 表上添加地理列：
ALTER TABLE cats ADD COLUMN geom GEOMETRY(Point, 4326);
UPDATE cats SET geom = ST_SetSRID(ST_MakePoint(longitude, latitude), 4326);
-- 创建空间索引：
CREATE INDEX idx_cats_geom ON cats USING GIST (geom);
-- 查询附近猫咪：
SELECT * FROM cats WHERE ST_DWithin(geom, ST_SetSRID(ST_MakePoint(118.78, 31.91), 4326), 500);