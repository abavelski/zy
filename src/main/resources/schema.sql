--drop schema
drop table invoice;
drop type invoice_status;

drop table invoice_line;
drop table users;

drop table subscription;
drop type subscription_status;

drop table running_fee;
drop type running_fee_status;

drop table a_number;
drop type anumber_status;
drop type anumber_type;

drop table service;
drop type service_status;

drop table billing_record;
drop type billing_record_status;
drop type usage_type;
drop type traffic_type;

drop table bundle;

drop table subscription_campaign;
drop type campaign_type;

drop table rating_session;
drop table balance;

--create schema
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

create type running_fee_status as enum ('INITIAL', 'ACTIVE', 'TERMINATED');

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

create table bundle(
  id serial primary key,
  campaign_code text,
  subscription_campaign_id integer,
  remaining_amount bigint,
  next_release_date date
);

create type campaign_type as enum('BUNDLE');
create table subscription_campaign(
  id serial primary key,
  subscription_id integer,
  campaign_plugin campaign_type,
  campaign_code text
);

create table rating_session(
  session_key text primary key,
  charge_date timestamp,
  used_units bigint,
  reserved_units bigint,
  price numeric(8,2)
);

create table balance(
  id serial primary key,
  subscription_id integer,
  amount numeric(8,2),
  reserved_amount numeric(8,2)
);
