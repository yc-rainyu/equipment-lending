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
