
create type invoice_status as enum ('OPEN', 'CLOSED');

create table invoice (
  id serial primary key,
  subscription_id integer not null,
  start_date date not null,
  end_date date not null,
  status invoice_status not null,
  total_excl_vat numeric(8,2),
  total_vat numeric(8,2),
  close_date timestamp
);

create table invoice_line(
  id serial primary key,
  reference_id integer,
  subscription_id integer not null,
  total numeric(8,2),
  description text,
  invoice_id integer,
  charge_date timestamp
);

create table users(
  id serial primary key,
  first_name text,
  last_name text,
  address text,
  city text,
  zip text
);

create type subscription_status as enum ('INITIAL', 'ACTIVE', 'TERMINATED');

create table subscription(
  id serial primary key,
  user_id integer,
  start_date timestamp,
  price_plan_code text,
  status subscription_status
);

create type running_fee_status as enum ('ACTIVE', 'TERMINATED');

create table running_fee(
  id serial primary key,
  fee_code text,
  subscription_id integer,
  next_charge_date date,
  status running_fee_status
);

create type anumber_status as enum('OPEN', 'ACTIVE', 'CLOSED');
create type anumber_type as enum('NORMAL', 'GOLD');

create table a_number(
  number int primary key,
  status anumber_status,
  type anumber_type,
  reserved_until timestamp,
  assigned_to_service_id integer,
  created timestamp,
  reservation_id text
);

create type service_status as enum('INITIAL', 'ACTIVE', 'TERMINATED');

create table service(
  id serial primary key,
  subscription_id integer,
  phone_number integer,
  status service_status
);

create type billing_record_status as enum ('INITIAL', 'RATED', 'ERROR');
create type usage_type as enum('VOICE', 'DATA', 'SMS', 'MMS');
create type traffic_type as enum('HOME', 'INT', 'ROAM_IN', 'ROAM_OUT');

create table billing_record(
  id serial primary key,
  phone_number integer,
  destination text,
  amount integer,
  charge_date timestamp,
  usage_type usage_type,
  traffic_type traffic_type,
  status billing_record_status
);

