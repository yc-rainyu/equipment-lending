# 資訊設備借用管理系統

## 系統介紹

簡易資訊設備借用管理網頁

功能：
1. 查看現有設備
2. 新增/修改/刪除可借用的設備
3. 新增借用設備紀錄
4. 歸還設備
5. 查看借用歷史
6. 依設備類型、狀態篩選設備

---

## 使用技術

- 前端：Vue3, Vue Router, Vite, axios, eslint
- 後端：Java 21, Spring Boot 4.1, Maven Wrapper, JdbcTemplate 
- 資料庫：PostgreSQL 17
- 環境：JDK 21, Node.js 24.22, Docker

---

## 資料庫設計

`equipment` 存設備，`loan_record` 存每次借用

一台設備可被借很多次，一對多，`loan_record.equipment_id` 指向 `equipment.id`

### equipment

| 欄位 | 型別 | 說明 |
|---|---|---|
| id | BIGSERIAL | 主鍵 |
| name | VARCHAR(100) | 設備名稱 |
| type | VARCHAR(20) | 類型，預設有 NOTEBOOK、MONITOR、PROJECTOR、TABLET、OTHER |
| status | VARCHAR(20) | 狀態，AVAILABLE 或 BORROWED，預設 AVAILABLE |
| created_at | TIMESTAMPTZ | 建立時間 |

### loan_record

| 欄位 | 型別 | 說明 |
|---|---|---|
| id | BIGSERIAL | 主鍵 |
| equipment_id | BIGINT | 借用的設備，外鍵指向 equipment.id |
| borrower_name | VARCHAR(50) | 借用人 |
| borrowed_at | TIMESTAMPTZ | 借出時間 |
| returned_at | TIMESTAMPTZ | 歸還時間，還沒還是 NULL |

### Procedure

| 名稱 | 用途 |
|---|---|
| fn_list_equipment | 查詢設備 |
| sp_create_equipment | 新增設備 |
| sp_update_equipment | 修改設備名稱和類型 |
| sp_delete_equipment | 刪除設備 |
| sp_borrow_equipment | 借用設備 + 新增借用紀錄 |
| sp_return_equipment | 歸還設備 + 填歸還時間 |
| fn_list_loan_records | 查詢借用歷史 |

---

## 啟動方式

### 資料庫

Docker
專案根目錄
```
docker run --name lending-db -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=equipment_lending -p 5432:5432 -d postgres:17
docker exec -i lending-db psql -U postgres -d equipment_lending < DB/01_schema.sql
docker exec -i lending-db psql -U postgres -d equipment_lending < DB/02_procedures.sql
docker exec -i lending-db psql -U postgres -d equipment_lending < DB/03_seed.sql
```

### 後端

資料夾 backend/
```
./mvnw spring-boot:run
```

### 前端

資料夾 frontend/
```
npm install
npm run dev
```

---

## API

| Method | 路徑 | 用途 | 輸入 | 回應 |
|---|---|---|---|---|
| GET | `/api/equipment` | 設備列表 | query `type`、`status` 可省略 | 200，陣列 |
| POST | `/api/equipment` | 新增設備 | `{ name, type }` | 201 |
| PUT | `/api/equipment/{id}` | 修改設備 | `{ name, type }` | 204 |
| DELETE | `/api/equipment/{id}` | 刪除設備 | | 204 |
| POST | `/api/equipment/{id}/borrow` | 新增借用紀錄 | `{ borrowerName }` | 204 |
| POST | `/api/equipment/{id}/return` | 歸還設備 | | 204 |
| GET | `/api/loans` | 借用歷史 | query `equipmentId` 可省略 | 200，陣列 |
