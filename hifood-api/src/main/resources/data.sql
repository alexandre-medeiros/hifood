set foreign_key_checks = 0;

delete from kitchen;
delete from restaurant;

-- Insert data into kitchen table
INSERT INTO kitchen (id,name) VALUES (1,'Main Kitchen');
INSERT INTO kitchen (id,name) VALUES (2,'Barbecue Kitchen');
INSERT INTO kitchen (id,name) VALUES (3,'Bakery Kitchen');

-- Insert data into restaurant table
INSERT INTO restaurant (id,delivery_fees, name, kitchen_id) VALUES (1, 5.00, 'Tasty Bites', 2);
INSERT INTO restaurant (id,delivery_fees, name, kitchen_id) VALUES (2, 7.50, 'Grill Haven', 1);
INSERT INTO restaurant (id,delivery_fees, name, kitchen_id) VALUES (3, 3.00, 'Sweet Delights', 3);
