DROP TABLE IF EXISTS loan_record CASCADE;
DROP TABLE IF EXISTS equipment CASCADE;

CREATE TABLE equipment (
    id         BIGSERIAL PRIMARY KEY,
    name       VARCHAR(100) NOT NULL,
    type       VARCHAR(20)  NOT NULL CHECK (type IN ('NOTEBOOK', 'MONITOR', 'PROJECTOR', 'TABLET', 'OTHER')),
    status     VARCHAR(20)  NOT NULL DEFAULT 'AVAILABLE' CHECK (status IN ('AVAILABLE', 'BORROWED')),
    created_at TIMESTAMPTZ  NOT NULL DEFAULT now()
);

CREATE TABLE loan_record (
    id            BIGSERIAL PRIMARY KEY,
    equipment_id  BIGINT      NOT NULL REFERENCES equipment (id),
    borrower_name VARCHAR(50) NOT NULL,
    borrowed_at   TIMESTAMPTZ NOT NULL DEFAULT now(),
    returned_at   TIMESTAMPTZ
);
