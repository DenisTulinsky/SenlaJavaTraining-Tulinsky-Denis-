select distinct product.maker, price
from product join printer 
on product.model=printer.model
where printer.color= 'y' and printer.price = ( select min(price) from printer where color = 'y');