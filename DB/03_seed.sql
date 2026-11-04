TRUNCATE loan_record, equipment RESTART IDENTITY;

INSERT INTO equipment (name, type) VALUES
    ('MacBook', 'NOTEBOOK'),
    ('Surface', 'NOTEBOOK'),
    ('螢幕', 'MONITOR'),
    ('投影機', 'PROJECTOR'),
    ('iPad', 'TABLET'),
    ('觸控筆', 'OTHER');
