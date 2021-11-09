--liquibase formatted sql

--changeset aknyazhev:init

create index moreEfficiency on account3(version);