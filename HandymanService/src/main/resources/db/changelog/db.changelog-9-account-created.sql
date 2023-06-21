--liquibase formatted sql

--changeset Rabysh239:10
alter table accounts
    add column created timestamp;