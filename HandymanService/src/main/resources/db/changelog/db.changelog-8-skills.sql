--liquibase formatted sql

--changeset Rabysh239:9
create table if not exists skills
(
    id          bigserial primary key,
    handyman_id bigint       not null,
    name        varchar(255) not null,
    foreign key (handyman_id) references handymans (id)
);