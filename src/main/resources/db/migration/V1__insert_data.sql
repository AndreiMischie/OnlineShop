INSERT INTO product_category
(description,
name)
VALUES ( 'new kitchen supplies',
'kitchen'
);

INSERT INTO product
(price, weight,
category_id,
description,
imageurl,
name)
VALUES (5, 2, (select id from product_category where name ='kitchen' limit 1), 'blabla', 'ahsdvkahvd', 'spoon');

INSERT INTO product
(price, weight,
category_id,
description,
imageurl,
name)
VALUES (5, 2, (select id from product_category where name ='kitchen' limit 1), 'blabla', 'ahsdvkahvd', 'fork');


INSERT INTO product
(price, weight,
category_id,
description,
imageurl,
name)
VALUES (5, 2, (select id from product_category where name ='kitchen' limit 1), 'blabla', 'ahsdvkahvd', 'knife');

INSERT INTO product
(price, weight,
category_id,
description,
imageurl,
name)
VALUES (5, 2, (select id from product_category where name ='kitchen' limit 1), 'blabla', 'ahsdvkahvd', 'glass');

INSERT INTO product
(price, weight,
category_id,
description,
imageurl,
name)
VALUES (5, 2, (select id from product_category where name ='kitchen' limit 1), 'blabla', 'ahsdvkahvd', 'bowl');

INSERT INTO location
(country,
city,
county,
street_address,
name)
VALUES ('Romania','Timisoara','Timis','Aleea Studentilor', 'Depozit1');

INSERT INTO location
(country,
city,
county,
street_address,
name)
VALUES ('Romania','Timisoara','Timis','Aleea Studentilor', 'Depozit2');

INSERT INTO customer
(first_name,
last_name,
username,
password,
email_address)
VALUES ('John','Doe','JohnDoe','password', 'john_doe@joe.com');

INSERT INTO stock
(product_id,
location_id,
quantity)
VALUES ((select id from product where name ='fork' limit 1),  (select id from location where name ='Depozit1' limit 1), 15);

INSERT INTO stock
(product_id,
location_id,
quantity)
VALUES ((select id from product where name ='spoon' limit 1),  (select id from location where name ='Depozit1' limit 1), 10);


INSERT INTO stock
(product_id,
location_id,
quantity)
VALUES ((select id from product where name ='knife' limit 1),  (select id from location where name ='Depozit1' limit 1), 7);

INSERT INTO stock
(product_id,
location_id,
quantity)
VALUES ((select id from product where name ='fork' limit 1),  (select id from location where name ='Depozit2' limit 1), 4);

INSERT INTO stock
(product_id,
location_id,
quantity)
VALUES ((select id from product where name ='glass' limit 1),  (select id from location where name ='Depozit2' limit 1), 9);

INSERT INTO stock
(product_id,
location_id,
quantity)
VALUES ((select id from product where name ='bowl' limit 1),  (select id from location where name ='Depozit2' limit 1), 6);



