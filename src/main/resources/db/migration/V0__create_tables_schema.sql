set search_path to shop;

create table customer (id uuid not null, email_address varchar(255), first_name varchar(255), last_name varchar(255), password varchar(255), username varchar(255), primary key (id));
create table location (id uuid not null, city varchar(255), country varchar(255), county varchar(255), name varchar(255), street_address varchar(255), primary key (id));
create table order_detail (quantity integer not null, orders_id uuid not null, product_id uuid not null, shipped_from_id uuid not null, primary key (orders_id, product_id, shipped_from_id));
create table orders (created_at timestamp(6), customer_id uuid, id uuid not null, city varchar(255), country varchar(255), county varchar(255), street_address varchar(255), primary key (id));
create table product (price float4 not null, weight float(53) not null, category_id uuid, id uuid not null, description varchar(255), imageurl varchar(255), name varchar(255), primary key (id));
create table product_category (id uuid not null, description varchar(255), name varchar(255), primary key (id));
create table stock (quantity integer not null, location_id uuid not null, product_id uuid not null, primary key (location_id, product_id));