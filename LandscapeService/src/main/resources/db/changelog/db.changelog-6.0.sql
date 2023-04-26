--liquibase formatted sql

--changeset Rabysh239:4
alter table users
    add column latitude double precision;
alter table users
    add column longitude double precision;