SELECT * FROM
articulos ar LEFT OUTER JOIN articulos_ventas ve ON (ar.id = ve.articulo_id) where ve.venta_id = 6
