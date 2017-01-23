select maker, count(distinct model) as modelsnumber
from product 
where product.type = 'pc'
group by maker
having count(distinct model)>=3