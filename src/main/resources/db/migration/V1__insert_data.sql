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

