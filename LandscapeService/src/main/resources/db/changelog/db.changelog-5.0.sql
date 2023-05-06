--liquibase formatted sql

--changeset Rabysh239:1
create extension if not exists "uuid-ossp";

create table if not exists users
(
    id      UUID default uuid_generate_v4() primary key,
    type    varchar(255) not null,
    login   varchar(255) not null,
    email   varchar(255) not null,
    phone   varchar(255) not null,
    created timestamp    not null,
    updated timestamp    not null
);
