create table restaurant (
	id bigint not null auto_increment,
	kitchen_id bigint not null,
	name varchar(80) not null,
	delivery_fees decimal(10,2) not null,
	updated_at datetime not null,
	created_at datetime not null,
    address_city_id bigint,
    address_zip_code varchar(9),
    address_street varchar(100),
    address_number varchar(10),

	primary key (id)
) engine=InnoDB default charset=utf8;

create table restaurant_payment_way (
	restaurant_id bigint not null,
	payment_way_id bigint not null,

	primary key (restaurant_id, payment_way_id)
) engine=InnoDB default charset=utf8;

alter table restaurant add constraint fk_restaurant_city foreign key (address_city_id) references city (id);
alter table restaurant add constraint fk_restaurant_kitchen foreign key (kitchen_id) references kitchen (id);

alter table restaurant_payment_way add constraint fk_rest_payment_way_restaurant foreign key (restaurant_id) references restaurant (id);
alter table restaurant_payment_way add constraint fk_rest_payment_way_payment_way foreign key (payment_way_id) references payment_way (id);
