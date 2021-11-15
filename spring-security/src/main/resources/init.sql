--liquibase formatted sql

--changeset aknyazhev:init

create table users
(
    id       serial constraint users_pkey primary key,
    username varchar(32) not null,
    password varchar(64) not null,
    role     varchar(32) not null
);

insert into users (username, password, role) values
    ('user', '$2a$08$9noS/3HPxkBsp7r92EWjIOSbFZf1h6Lskj9aqjlZPfeevQgcv4jSy', 'ROLE_USER'),
    ('admin', '$2a$08$xTHh1/dDb9a78PUlUW56jePjhWa0WXWlb63UPzNL3XiOW9BVtKmWa', 'ROLE_ADMIN'),
    ('api', '$2a$08$xCrPHKHy2lMoHXq0VJutFeoAOuQdNGTJauPZdDG/zxo4vs9TOxJkK', 'ROLE_API');