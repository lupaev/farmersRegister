--liquibase formatted sql

--changeset sergej:3
--Создание вспомогательной таблицы
create table farmer_regions
(
    farmer_id  bigint not null,
    regions_id bigint not null
);





