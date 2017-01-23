select speed, avg(price) as avg_price
from pc
where speed > 600
group by speed;
