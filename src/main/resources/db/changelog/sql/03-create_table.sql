--liquibase formatted sql

--changeset sergej:3
--Создание вспомогательной таблицы, районы посевных полей
-- create table farmer_regions
-- (
--     farmer_id  bigint not null ,
--     regions_id bigint not null ,
--     CONSTRAINT fk_farmer_id_farmer_regions FOREIGN KEY (farmer_id)
--         REFERENCES farmer (id)  on delete cascade ,
--     CONSTRAINT fk_regions_id_farmer_regions FOREIGN KEY (regions_id)
--         REFERENCES region (id) on delete cascade
-- );





