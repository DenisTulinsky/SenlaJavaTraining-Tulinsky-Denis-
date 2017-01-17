Select distinct product.maker from product
where product.type in ('PC')
and product.maker not in ( select maker from product   where
product.type='Laptop')
