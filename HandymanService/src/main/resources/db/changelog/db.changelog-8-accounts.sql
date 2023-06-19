--liquibase formatted sql

--changeset Rabysh239:8
create type payment_system as enum ('VISA', 'MASTER_CARD', 'MIR', 'UNION_PAY');

create table if not exists accounts
(
    id             bigserial primary key,
    handyman_id    bigint         not null,
    card_number    varchar(255)   not null,
    payment_system payment_system not null,
    foreign key (handyman_id) references handymans (id)
);