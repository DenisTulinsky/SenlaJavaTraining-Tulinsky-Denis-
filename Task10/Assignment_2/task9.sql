Select distinct product.maker from product 
inner join pc on product.model=pc.model
where pc.speed >= '450'
