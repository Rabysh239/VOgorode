--liquibase formatted sql

--changeset Rabysh239:2
create table if not exists user_types
(
    id   smallserial primary key,
    name varchar(255)
);

insert into user_types
values (1, 'handyman'),
       (2, 'rancher');

alter table users
    add column type_id smallserial not null references user_types (id);

update users
set type_id = (select id from user_types where user_types.name = users.type);

alter table users
    drop column type;