TRUNCATE loan_record, equipment RESTART IDENTITY;

INSERT INTO equipment (name, type) VALUES
    ('MacBook', 'NOTEBOOK'),
    ('Surface', 'NOTEBOOK'),
    ('螢幕', 'MONITOR'),
    ('投影機', 'PROJECTOR'),
    ('iPad', 'TABLET'),
    ('觸控筆', 'OTHER');

INSERT INTO loan_record (equipment_id, borrower_name, borrowed_at, returned_at) VALUES
    (1, 'A', '2026-10-01 09:00+08', '2026-10-22 17:30+08'),
    (4, 'B', '2026-10-31 13:00+08', '2026-10-28 18:00+08'),
    (2, 'C', '2026-11-03 10:00+08', NULL);

UPDATE equipment SET status = 'BORROWED' WHERE id = 2;
