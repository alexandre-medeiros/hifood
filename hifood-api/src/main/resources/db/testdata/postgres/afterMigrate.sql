delete from product;
delete from restaurant_payment_way;
delete from restaurant;
delete from city;
delete from state;
delete from kitchen;
delete from payment_way;
delete from permission;

-- Insert data into state table
insert into state (name) values ('Minas Gerais');
insert into state (name) values ('São Paulo');
insert into state (name) values ('Ceará');

-- Insert data into city table
insert into city (name, state_id) values ('Uberlândia', 1);
insert into city (name, state_id) values ('Belo Horizonte', 1);
insert into city (name, state_id) values ('São Paulo', 2);
insert into city (name, state_id) values ('Campinas', 2);
insert into city (name, state_id) values ('Fortaleza', 3);

-- Insert data into kitchen table
INSERT INTO kitchen (name) VALUES ('Tailandesa');
INSERT INTO kitchen (name) VALUES ('Indiana');
insert into kitchen (name) values ('Argentina');
insert into kitchen (name) values ('Brasileira');

-- Insert data into restaurant table
insert into restaurant (name, delivery_fees, kitchen_id, created_at, updated_at, address_city_id, address_zip_code, address_street, address_number) values ('Thai Gourmet', 10, 1, '2024-01-12 12:00:00', '2024-01-13 14:30:00', 1, '38400-999', 'Rua João Pinheiro', '1000');
insert into restaurant (name, delivery_fees, kitchen_id, created_at, updated_at, address_city_id, address_zip_code, address_street, address_number) values ('Italian Delight', 8, 2, '2024-01-12 13:30:00', '2024-01-13 15:45:00', 2, '38401-001', 'Avenida Italia', '500');
insert into restaurant (name, delivery_fees, kitchen_id, created_at, updated_at, address_city_id, address_zip_code, address_street, address_number) values ('Burger Haven', 12, 3, '2024-01-12 14:45:00', '2024-01-13 17:00:00', 1, '38402-002', 'Rua Burgerland', '123');
insert into restaurant (name, delivery_fees, kitchen_id, created_at, updated_at, address_city_id, address_zip_code, address_street, address_number) values ('Sushi Palace', 15, 4, '2024-01-12 16:00:00', '2024-01-13 18:15:00', 3, '38403-003', 'Avenida Sushiville', '789');
insert into restaurant (name, delivery_fees, kitchen_id, created_at, updated_at, address_city_id, address_zip_code, address_street, address_number) values ('Mexican Fiesta', 10, 2, '2024-01-12 17:15:00', '2024-01-13 19:30:00', 4, '38404-004', 'Calle Mexicana', '234');
insert into restaurant (name, delivery_fees, kitchen_id, created_at, updated_at, address_city_id, address_zip_code, address_street, address_number) values ('Mediterranean Flavors', 12, 2, '2024-01-12 18:30:00', '2024-01-13 20:45:00', 2, '38405-005', 'Rua Mediterranea', '567');
insert into restaurant (name, delivery_fees, kitchen_id, created_at, updated_at, address_city_id, address_zip_code, address_street, address_number) values ('Vegetarian Bliss', 8, 1, '2024-01-12 19:45:00', '2024-01-13 22:00:00', 1, '38406-006', 'Avenida Veggie', '890');

-- Insert data into payment_way table
insert into payment_way (description) values ('Cartão de crédito');
insert into payment_way (description) values ('Cartão de débito');
insert into payment_way (description) values ('Dinheiro');

-- Insert data into relationship between restaurant and payment_way tables
insert into restaurant_payment_way (restaurant_id, payment_way_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3), (4, 1), (4, 2), (5, 1), (5, 2), (6, 3);

-- Insert data into permission table
insert into permission (name, description) values ('CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permission (name, description) values ('EDITAR_COZINHAS', 'Permite editar cozinhas');

-- Insert data into permission table
insert into product (name, description, price, active, restaurant_id) values ('Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90, 'true', 1);
insert into product (name, description, price, active, restaurant_id) values ('Camarão tailandês', '16 camarões grandes ao molho picante', 110, 'true', 1);
insert into product (name, description, price, active, restaurant_id) values ('Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20, 'true', 2);
insert into product (name, description, price, active, restaurant_id) values ('Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21, 'true', 3);
insert into product (name, description, price, active, restaurant_id) values ('Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43, 'true', 3);
insert into product (name, description, price, active, restaurant_id) values ('Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79, 'true', 4);
insert into product (name, description, price, active, restaurant_id) values ('T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89, 'true', 4);
insert into product (name, description, price, active, restaurant_id) values ('Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19, 'true', 5);
insert into product (name, description, price, active, restaurant_id) values ('Espetinho de Cupim', 'Acompanha farinha, mandioca e vinagrete', 8, 'true', 6);
