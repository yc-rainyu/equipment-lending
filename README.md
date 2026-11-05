# 資訊設備借用管理系統

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

## API

| Method | 路徑 | 用途 | 輸入 | 回應 |
|---|---|---|---|---|
| GET | `/api/equipment` | 設備列表 | query `type`、`status` 可省略 | 200，陣列 |
