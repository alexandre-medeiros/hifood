create table `order` (
  id bigint not null auto_increment,
  subtotal decimal(10,2) not null,
  delivery_fees decimal(10,2) not null,
  total decimal(10,2) not null,

  restaurant_id bigint not null,
  client_id bigint not null,
  payment_way_id bigint not null,

  address_city_id bigint,
  address_zip_code varchar(9),
  address_street varchar(100),
  address_number varchar(10),
  
  status varchar(10) not null,
  created_at datetime not null,
  confirmed_at datetime null,
  canceled_at datetime null,
  delivered_at datetime null,

  primary key (id),

  constraint fk_order_restaurant foreign key (restaurant_id) references restaurant (id),
  constraint fk_order_client foreign key (client_id) references users (id),
  constraint fk_order_payment_way foreign key (payment_way_id) references payment_way (id)
) engine=InnoDB default charset=utf8;

create table order_item (
  id bigint not null auto_increment,
  quantity smallint(6) not null,
  price_unit decimal(10,2) not null,
  price_total decimal(10,2) not null,
  observation varchar(255) null,
  order_id bigint not null,
  product_id bigint not null,
  
  primary key (id),
  unique key uk_order_item_product (order_id, product_id),

  constraint fk_order_item_order foreign key (order_id) references `order` (id),
  constraint fk_order_item_product foreign key (product_id) references product (id)
) engine=InnoDB default charset=utf8;