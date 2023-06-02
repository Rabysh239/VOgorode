--liquibase formatted sql

--changeset Rabysh239:5
create table if not exists ranchers
(
    id      bigserial primary key,
    user_id UUID not null
);
