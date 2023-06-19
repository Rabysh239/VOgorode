create type work_type as enum ('SHOVEL', 'PLANT', 'WATER', 'SOW');
create type order_status as enum ('CREATED', 'IN_PROGRESS', 'DONE', 'APPROVED');

create table if not exists orders
(
    id        bigserial primary key,
    field_id  bigint       not null,
    user_id   uuid         not null,
    work_type work_type    not null,
    status    order_status not null,
    created   timestamp    not null,
    foreign key (user_id) references users (id)
);