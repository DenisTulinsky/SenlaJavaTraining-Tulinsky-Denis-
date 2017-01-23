select maker, max(pc.price) as max_price
from product join pc
on product.model=pc.model
where product.type = 'pc'
group by maker;