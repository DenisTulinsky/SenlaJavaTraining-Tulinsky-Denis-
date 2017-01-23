select distinct product.type, laptop.model, laptop.speed
from product, laptop 
where product.model=laptop.model
and laptop.speed < (select min(speed) from pc);