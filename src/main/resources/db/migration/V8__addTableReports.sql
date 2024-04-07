create table reports(
	 id int primary key,
	 item varchar(50),
	 purchased_by varchar(50),
	 purchased_from varchar(50),
	 purchased_date date,
	 amount varchar(10),
	 paid_by varchar(10)
);