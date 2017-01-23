select model from (
select model, price from pc
union
select model, price from laptop
union
select model, price from printer) as tabl
where price = (select max(price) from 
(select model, price from pc
union
select model, price from laptop
union
select model, price from printer) as tabl2)

