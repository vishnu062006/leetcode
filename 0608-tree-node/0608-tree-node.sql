SELECT t1.id, CASE WHEN t1.p_id IS NULL THEN 'Root'
        WHEN NOT EXISTS (
            SELECT 1 
            FROM Tree t2 
            WHERE t2.p_id = t1.id
        ) THEN 'Leaf'
        ELSE 'Inner'
    END AS type
FROM Tree t1;