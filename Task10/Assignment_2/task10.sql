select product.model, printer.price from product 
inner join printer
on product.model=printer.model
where printer.price = (select max(price) from printer)
order by printer.price desc
