--liquibase formatted sql

--changeset Rabysh239:6
create extension if not exists postgis;

create table if not exists fields
(
    id         bigserial primary key,
    rancher_id bigint           not null,
    address    varchar(255)     not null,
    latitude   double precision not null,
    longitude  double precision not null,
    area       geometry         not null,
    foreign key (rancher_id) references ranchers (id)
);
