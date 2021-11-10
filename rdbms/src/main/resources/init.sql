--liquibase formatted sql

--changeset rrmasgutov:init
--changeset aknyazhev:upd

create table account1
(
    id bigserial constraint account_pk primary key,
    amount int,
    version int
);

create table account2
(
    id bigserial constraint account_pk2 primary key,
    amount int
);