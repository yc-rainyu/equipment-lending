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
    DELETE FROM equipment WHERE id = p_id;

    IF NOT FOUND THEN
        RAISE EXCEPTION '找不到設備';
    END IF;
END;
$$;
