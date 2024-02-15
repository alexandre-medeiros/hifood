set foreign_key_checks = 0;
delete from city;
delete from state;
delete from kitchen;
delete from payment_way;
delete from restaurant;
delete from restaurant_payment_way;
delete from permission;
set foreign_key_checks = 1;

-- Insert data into state table
insert into state (id, name) values (1, 'Minas Gerais');
insert into state (id, name) values (2, 'São Paulo');
insert into state (id, name) values (3, 'Ceará');

-- Insert data into city table
insert into city (id, name, state_id) values (1, 'Uberlândia', 1);
insert into city (id, name, state_id) values (2, 'Belo Horizonte', 1);
insert into city (id, name, state_id) values (3, 'São Paulo', 2);
insert into city (id, name, state_id) values (4, 'Campinas', 2);
insert into city (id, name, state_id) values (5, 'Fortaleza', 3);

-- Insert data into kitchen table
INSERT INTO kitchen (id, name) VALUES (1,'Tailandesa');
INSERT INTO kitchen (id, name) VALUES (2,'Indiana');
insert into kitchen (id, name) values (3, 'Argentina');
insert into kitchen (id, name) values (4, 'Brasileira');

-- Insert data into restaurant table
insert into restaurant (id, name, delivery_fees, kitchen_id, created_at, updated_at, address_city_id, address_zip_code, address_street, address_number) values (1, 'Thai Gourmet', 10, 1, '2024-01-12 12:00:00', '2024-01-13 14:30:00', 1, '38400-999', 'Rua João Pinheiro', '1000');
insert into restaurant (id, name, delivery_fees, kitchen_id, created_at, updated_at, address_city_id, address_zip_code, address_street, address_number) values (2, 'Italian Delight', 8, 2, '2024-01-12 13:30:00', '2024-01-13 15:45:00', 2, '38401-001', 'Avenida Italia', '500');
insert into restaurant (id, name, delivery_fees, kitchen_id, created_at, updated_at, address_city_id, address_zip_code, address_street, address_number) values (3, 'Burger Haven', 12, 3, '2024-01-12 14:45:00', '2024-01-13 17:00:00', 1, '38402-002', 'Rua Burgerland', '123');
insert into restaurant (id, name, delivery_fees, kitchen_id, created_at, updated_at, address_city_id, address_zip_code, address_street, address_number) values (4, 'Sushi Palace', 15, 4, '2024-01-12 16:00:00', '2024-01-13 18:15:00', 3, '38403-003', 'Avenida Sushiville', '789');
insert into restaurant (id, name, delivery_fees, kitchen_id, created_at, updated_at, address_city_id, address_zip_code, address_street, address_number) values (5, 'Mexican Fiesta', 10, 2, '2024-01-12 17:15:00', '2024-01-13 19:30:00', 4, '38404-004', 'Calle Mexicana', '234');
insert into restaurant (id, name, delivery_fees, kitchen_id, created_at, updated_at, address_city_id, address_zip_code, address_street, address_number) values (6, 'Mediterranean Flavors', 12, 2, '2024-01-12 18:30:00', '2024-01-13 20:45:00', 2, '38405-005', 'Rua Mediterranea', '567');
insert into restaurant (id, name, delivery_fees, kitchen_id, created_at, updated_at, address_city_id, address_zip_code, address_street, address_number) values (7, 'Vegetarian Bliss', 8, 1, '2024-01-12 19:45:00', '2024-01-13 22:00:00', 1, '38406-006', 'Avenida Veggie', '890');

-- Insert data into payment_way table
insert into payment_way (id, description) values (1, 'Cartão de crédito');
insert into payment_way (id, description) values (2, 'Cartão de débito');
insert into payment_way (id, description) values (3, 'Dinheiro');

-- Insert data into relationship between restaurant and payment_way tables
insert into restaurant_payment_way (restaurant_id, payment_way_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3), (4, 1), (4, 2), (5, 1), (5, 2), (6, 3);

-- Insert data into permission table
insert into permission (id, name, description) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permission (id, name, description) values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');

-- Insert data into permission table
insert into product (name, description, price, active, restaurant_id) values ('Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90, 1, 1);
insert into product (name, description, price, active, restaurant_id) values ('Camarão tailandês', '16 camarões grandes ao molho picante', 110, 1, 1);
insert into product (name, description, price, active, restaurant_id) values ('Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20, 1, 2);
insert into product (name, description, price, active, restaurant_id) values ('Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21, 1, 3);
insert into product (name, description, price, active, restaurant_id) values ('Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43, 1, 3);
insert into product (name, description, price, active, restaurant_id) values ('Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79, 1, 4);
insert into product (name, description, price, active, restaurant_id) values ('T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89, 1, 4);
insert into product (name, description, price, active, restaurant_id) values ('Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19, 1, 5);
insert into product (name, description, price, active, restaurant_id) values ('Espetinho de Cupim', 'Acompanha farinha, mandioca e vinagrete', 8, 1, 6);
