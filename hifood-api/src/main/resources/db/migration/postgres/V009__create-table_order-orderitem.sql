create table "order" (
  id integer PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
  subtotal decimal(10,2) not null,
  delivery_fees decimal(10,2) not null,
  total decimal(10,2) not null,

  restaurant_id integer not null,
  client_id integer not null,
  payment_way_id integer not null,

  address_city_id integer,
  address_zip_code varchar(9),
  address_street varchar(100),
  address_number varchar(10),
  
  status varchar(10) not null,
  created_at TIMESTAMP not null,
  confirmed_at TIMESTAMP null,
  canceled_at TIMESTAMP null,
  delivered_at TIMESTAMP null,

  constraint fk_order_restaurant foreign key (restaurant_id) references restaurant (id),
  constraint fk_order_client foreign key (client_id) references users (id),
  constraint fk_order_payment_way foreign key (payment_way_id) references payment_way (id)
);

create table order_item (
  id integer PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
  quantity integer not null,
  price_unit decimal(10,2) not null,
  price_total decimal(10,2) not null,
  observation varchar(255) null,
  order_id integer not null,
  product_id integer not null,
  unique(order_id, product_id),

  constraint fk_order_item_order foreign key (order_id) references "order" (id),
  constraint fk_order_item_product foreign key (product_id) references product (id)
);