# data-workshop

招新题第 6 题：数据存取（MySQL + MyBatis + 索引 + 分页）

## 快速开始

### 方式 A：本地没有 MySQL（先跑通）

```powershell
mvn -DskipTests package
java -jar target/data-workshop-1.0.0.jar --spring.profiles.active=local
```

### 方式 B：连真实 MySQL

1. 执行 `sql/schema.sql` 建库建表
2. 设置环境变量后启动：

```powershell
$env:DB_URL="jdbc:mysql://localhost:3306/workshop?useUnicode=true&characterEncoding=utf8mb4&serverTimezone=Asia/Shanghai&useSSL=false&allowPublicKeyRetrieval=true"
$env:DB_USER="root"
$env:DB_PASSWORD="你的密码"
java -jar target/data-workshop-1.0.0.jar
```

## 接口

| 方法 | 路径 | 说明 |
|------|------|------|
| POST | `/api/records` | 保存（body: `{"title":"..","content":".."}`） |
| GET | `/api/records?page=1&size=10` | 分页读取 |
| GET | `/api/records/{id}` | 按 id 读取 |
| GET | `/api/records/title?title=x` | 按标题查（索引） |

## 安全说明

数据库账号密码通过环境变量 `DB_USER` / `DB_PASSWORD` 注入，**不写死在代码、不提交到仓库**。
