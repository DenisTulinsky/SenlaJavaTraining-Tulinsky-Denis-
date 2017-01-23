select distinct p.model as mbig, p2.model as msmall, p.speed, p.ram
from pc as p, pc as p2
where p.speed=p2.speed and p.ram=p2.ram
and p.model > p2.model;