--liquibase formatted sql

--changeset Rabysh239:7
create table if not exists handymans
(
    id      bigserial primary key,
    user_id UUID  not null,
    photo   bytea not null
);
