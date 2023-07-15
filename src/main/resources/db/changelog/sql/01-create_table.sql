--liquibase formatted sql

--changeset sergej:1
--Создание таблицы Регион и Фермер
create table region
(
    id          bigserial primary key,
    name        varchar unique not null,
    code_region integer unique
);
create table region_archive
(
    id          bigserial primary key,
    name        varchar unique not null,
    code_region integer unique
);

create table farmer
(
    id                     bigserial primary key,
    name                   varchar unique not null,
    legal_form             varchar,
    inn                    varchar unique not null,
    kpp                    varchar,
    ogrn                   varchar,
    registration_region_id bigserial,
    date_registration      date,
    constraint fk_region_id foreign key (registration_region_id) references region (id)
);

create table farmer_archive
(
    id                     bigserial primary key,
    name                   varchar unique not null,
    legal_form             varchar,
    inn                    varchar unique not null,
    kpp                    varchar,
    ogrn                   varchar,
    registration_region_id bigserial,
    date_registration      date,
    constraint fk_region_id_archive foreign key (registration_region_id) references region (id)
);

create table farmer_regions
(
    farmer_id  bigint not null,
    regions_id bigint not null,
    CONSTRAINT fk_farmer_id_farmer_regions FOREIGN KEY (farmer_id)
        REFERENCES farmer (id) on delete cascade,
    CONSTRAINT fk_regions_id_farmer_regions FOREIGN KEY (regions_id)
        REFERENCES region (id) on delete cascade
);


