-- 本地 H2（MySQL 兼容模式）建表，语法与 MySQL 基本一致
CREATE TABLE IF NOT EXISTS records (
    id         BIGINT       NOT NULL AUTO_INCREMENT,
    title      VARCHAR(100) NOT NULL,
    content    VARCHAR(1000) NULL,
    created_at TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE INDEX IF NOT EXISTS idx_records_title ON records (title);
