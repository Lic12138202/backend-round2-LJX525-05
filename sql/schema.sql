-- ============================================
-- 第6题建表 SQL（MySQL 8.x）
-- 数据库：workshop
-- 表：records（演示「客户端数据入库」）
-- ============================================

CREATE DATABASE IF NOT EXISTS workshop
    DEFAULT CHARACTER SET utf8mb4
    DEFAULT COLLATE utf8mb4_unicode_ci;

USE workshop;

DROP TABLE IF EXISTS records;

CREATE TABLE records (
    id          BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键',
    title       VARCHAR(100) NOT NULL                COMMENT '标题',
    content     VARCHAR(1000) NULL                   COMMENT '内容',
    created_at  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COMMENT = '客户端数据存储演示表';

-- 普通索引：按标题查询场景（任务二要求加索引并说明原因）
CREATE INDEX idx_records_title ON records (title);
