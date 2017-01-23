SELECT DISTINCT
    maker
FROM
    product
        JOIN
    pc ON product.model = pc.model
        AND ram IN (SELECT 
            MIN(ram)
        FROM
            pc)
        AND speed IN (SELECT 
            MAX(speed)
        FROM
            pc
        WHERE
            ram IN (SELECT 
                    MIN(ram)
                FROM
                    pc))
        AND maker IN (SELECT 
            maker
        FROM
            product
        WHERE
            type = 'printer')