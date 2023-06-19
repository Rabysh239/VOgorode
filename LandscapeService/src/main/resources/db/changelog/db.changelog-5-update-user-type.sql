--liquibase formatted sql

--changeset Rabysh239:3 splitStatements:false
create or replace procedure update_user_type(p_type_id smallint, p_batch_size integer, p_pause_time integer)
    language plpgsql
as
$$
declare
    v_size           integer;
    v_processed_size integer := 0;
begin
    select count(*) into v_size from users;
    while v_processed_size < v_size
        loop
            raise notice '% - Start processing next % rows.', now(), p_batch_size;
            update users
            set type_id = p_type_id
            where id in (
                select id
                from users
                order by created
                limit p_batch_size offset v_processed_size
            );
            v_processed_size := v_processed_size + p_batch_size;
            perform pg_sleep(p_pause_time);
            raise notice '% - Committed % rows. Migration paused for % sec.', now(), p_batch_size, p_pause_time;
        end loop;
end;
$$;
