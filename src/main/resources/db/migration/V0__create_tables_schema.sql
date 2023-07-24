set search_path to shop;

CREATE EXTENSION "uuid-ossp";

create table customer (
id uuid not null default uuid_generate_v4(),
email_address varchar(255),
first_name varchar(255),
last_name varchar(255),
password varchar(255),
username varchar(255),
PRIMARY KEY (id));

CREATE TABLE location (
id uuid not null default uuid_generate_v4(),
city varchar(255), country varchar(255),
county varchar(255), name varchar(255),
street_address varchar(255),
PRIMARY KEY (id));

CREATE TABLE order_detail (
quantity integer not null,
orders_id uuid not null,
product_id uuid not null,
shipped_from_id uuid not null,
PRIMARY KEY (orders_id, product_id, shipped_from_id));

CREATE TABLE orders (
created_at timestamp(6),
customer_id uuid,
id uuid not null default uuid_generate_v4(),
city varchar(255),
country varchar(255),
county varchar(255),
street_address varchar(255),
PRIMARY KEY (id));

CREATE TABLE product (
price float4 not null,
weight float(53) not null,
category_id uuid,
id uuid not null default uuid_generate_v4(),
description varchar(255),
imageurl varchar(255),
name varchar(255),
PRIMARY KEY (id));

CREATE TABLE product_category (
id uuid not null default uuid_generate_v4(),
description varchar(255),
name varchar(255),
PRIMARY KEY (id));

CREATE TABLE stock (
quantity integer not null,
location_id uuid not null,
product_id uuid not null,
PRIMARY KEY (location_id, product_id));