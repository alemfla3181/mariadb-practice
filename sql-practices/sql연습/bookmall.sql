show tables;

select * from category;

insert into category values(null, 'IT');

update category set name='시사' where no=6;

insert into book values(null,'사피엔스', 26000, 1);
select a.no, a.title, a.price, b.no, b.name from book a, category b 
 where a.category_no = b.no;
 
 update book set title="이것이 C언어다" where no=2;
 select * from book;
 
 select * from member;
 insert into member values(null, "둘리", "010-1234-5678", "dooly@naver.com", "1234");

update member set name="또치" where no=1;

select a.no, date_format(a.date, "%Y년 %m월 %d일"), a.payment, a.address, b.no, b.name, b.email from orders a, member b where a.member_no = b.no;

select a.name, b.title, c.amount 
 from member a, book b, cart c 
 where a.no = c.member_no 
 and b.no = c.book_no;

select * from cart;

insert into cart values(2, 2, 1);


update cart set amount='3' where book_no=2;

select b.no, b.title, b.price, a.amount 
 from order_book a, book b 
 where a.book_no = b.no;


select * from order_book;
select * from orders;

insert into orders(no, payment, address, member_no) values(null, "46800", "LA", 1);

update orders set address="LA" where no = 1;
update orders set payment=45800 where no =1;
delete from order_book where book_no = 1; 
insert into order_book values(1, 1, "5");

insert into order_book values(1, 2, 3);