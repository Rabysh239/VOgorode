--liquibase formatted sql

--changeset Rabysh239:2
create table if not exists user_types
(
    id   smallserial primary key,
    name varchar(255)
);

INSERT INTO user_types
VALUES (1, 'handyman'),
       (2, 'rancher');

alter table users
    add column user_type_id smallserial not null references user_types (id);

update users
set user_type_id = (select id from user_types where user_types.name = users.user_type);

alter table users
    drop column user_type;