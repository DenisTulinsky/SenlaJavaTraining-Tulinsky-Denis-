select hd
from PC
group by hd
having COUNT(hd)>= 2;