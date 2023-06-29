--liquibase formatted sql

--changeset sergej:1
--Создание таблицы Регион и Фермер
create table region
(
    id bigserial primary key ,
    name varchar not null ,
    code_region integer ,
    status varchar
);
create table farmer
(
    id bigserial primary key ,
    name varchar not null ,
    legal_form varchar ,
    inn bigint not null ,
    kpp bigint ,
    ogrn bigint ,
    registration_region_id bigint ,
    date_registration date ,
    status varchar ,
    constraint fk_region_id foreign key (registration_region_id) references region (id)
);


