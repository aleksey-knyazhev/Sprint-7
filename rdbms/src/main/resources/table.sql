--liquibase formatted sql

--changeset aknyazhev:init

create table account3
(
    id bigserial constraint account_pk3 primary key,
    amount int check (amount >= 0),
    version int
);

insert into account3 (id, amount, version) values (1, 100, 1);
insert into account3 (id, amount, version) values (2, 100, 2);