Select distinct maker 
from product join pc
on product.model=pc.model
where speed >= 750
and maker in (Select maker 
from product join laptop
on product.model=laptop.model
where speed >= 750)
order by maker;
