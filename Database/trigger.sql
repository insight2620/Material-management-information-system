DROP PROCEDURE if EXISTS  check_ex_warehouse;
delimiter $$
create procedure check_ex_warehouse(in goodsid int,in startingdate date,in finaldate date)
begin
	select SUM(ex_warehouse.number),(SUM(ex_warehouse.number)*goods.outputPrice)
	FROM ex_warehouse,goods
	where ex_warehouse.EX_time >= startingdate and ex_warehouse.EX_time <= finaldate and ex_warehouse.goodsID = goods.id
	and ex_warehouse.goodsID = goodsid  ;
end$$
delimiter ;


/*实际操作*/
call check_ex_warehouse('2','2019-01-01','2021-02-21');

DROP PROCEDURE if EXISTS  check_in_warehouse;
delimiter $$
create procedure check_in_warehouse(in goodsid int,in startingdate date,in finaldate date)
begin
	select SUM(in_warehouse.number),(SUM(in_warehouse.number)*goods.inputPrice)
	FROM in_warehouse,goods
	where in_warehouse.IN_time >= startingdate and in_warehouse.IN_time <= finaldate and in_warehouse.goodsID = goods.id
	and in_warehouse.goodsID = goodsid;
end$$
delimiter ;

call check_in_warehouse('1','2019-01-01','2021-02-21');

,(SUM(in_warehouse.number)*goods.inputPrice)