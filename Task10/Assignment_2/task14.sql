select distinct speed as dist_speed,
(select avg(price)from pc where speed = dist_speed)as avg_price
from pc
group by dist_speed asc; 