select * from user;
insert into user values(1,1,100,"ssafy","김싸피","ssafy","01000000000");

select * from island;
insert into island values(1,"1번섬");

select * from store;
insert into store values(1,"김싸피상점",1,"김상점",1,"www.google.com",1,1);

select * from goods;
insert into goods values(1,"1번상품의설명","","1번상품",100,2,1);
insert into goods values(2,"2번상품의설명","","2번상품",100,2,1);
insert into goods values(3,"3번상품의설명","","3번상품",300,5,1);
insert into goods values(4,"4번상품의설명","","4번상품",300,5,1);
insert into goods values(5,"5번상품의설명","","5번상품",300,5,1);
insert into goods values(6,"6번상품의설명","","6번상품",300,5,1);

select * from goods where store_id=1;

commit;