Select avg(speed) from pc
inner join product 
on product.model=pc.model
where product.maker= 'A'