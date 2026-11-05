CREATE OR REPLACE FUNCTION fn_list_equipment(p_type VARCHAR, p_status VARCHAR)
RETURNS SETOF equipment
LANGUAGE plpgsql
AS $$
BEGIN
    RETURN QUERY
    SELECT *
    FROM equipment
    WHERE (p_type IS NULL OR type = p_type)
      AND (p_status IS NULL OR status = p_status)
    ORDER BY id;
END;
$$;

CREATE OR REPLACE PROCEDURE sp_create_equipment(p_name VARCHAR, p_type VARCHAR)
LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO equipment (name, type) VALUES (p_name, p_type);
END;
$$;

CREATE OR REPLACE PROCEDURE sp_update_equipment(p_id BIGINT, p_name VARCHAR, p_type VARCHAR)
LANGUAGE plpgsql
AS $$
BEGIN
    UPDATE equipment SET name = p_name, type = p_type WHERE id = p_id;

    IF NOT FOUND THEN
        RAISE EXCEPTION '找不到設備';
    END IF;
END;
$$;

CREATE OR REPLACE PROCEDURE sp_delete_equipment(p_id BIGINT)
LANGUAGE plpgsql
AS $$
BEGIN
    IF EXISTS (SELECT 1 FROM loan_record WHERE equipment_id = p_id) THEN
        RAISE EXCEPTION '設備已有借用紀錄，無法刪除';
    END IF;

    DELETE FROM equipment WHERE id = p_id;

    IF NOT FOUND THEN
        RAISE EXCEPTION '找不到設備';
    END IF;
END;
$$;

CREATE OR REPLACE PROCEDURE sp_borrow_equipment(p_equipment_id BIGINT, p_borrower_name VARCHAR)
LANGUAGE plpgsql
AS $$
BEGIN
    -- update first so two requests cannot borrow the same equipment
    UPDATE equipment SET status = 'BORROWED' WHERE id = p_equipment_id AND status = 'AVAILABLE';

    IF NOT FOUND THEN
        IF NOT EXISTS (SELECT 1 FROM equipment WHERE id = p_equipment_id) THEN
            RAISE EXCEPTION '找不到設備';
        END IF;
        RAISE EXCEPTION '設備已借出';
    END IF;

    INSERT INTO loan_record (equipment_id, borrower_name) VALUES (p_equipment_id, p_borrower_name);
END;
$$;

CREATE OR REPLACE PROCEDURE sp_return_equipment(p_equipment_id BIGINT)
LANGUAGE plpgsql
AS $$
BEGIN
    UPDATE equipment SET status = 'AVAILABLE' WHERE id = p_equipment_id AND status = 'BORROWED';

    IF NOT FOUND THEN
        IF NOT EXISTS (SELECT 1 FROM equipment WHERE id = p_equipment_id) THEN
            RAISE EXCEPTION '找不到設備';
        END IF;
        RAISE EXCEPTION '設備不在借出狀態';
    END IF;

    UPDATE loan_record SET returned_at = now() WHERE equipment_id = p_equipment_id AND returned_at IS NULL;
END;
$$;
