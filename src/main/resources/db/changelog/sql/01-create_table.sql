--liquibase formatted sql

--changeset sergej:1
create table region
(
    id bigserial,
    name varchar,
    code_region integer,
    status varchar,
    primary key (id)
);
create table farmer
(
    id bigserial,
    name varchar,
    legal_form varchar,
    inn integer,
    kpp integer,
    ogrn integer,
    registration_region_id bigint,
    date_registration date,
    status varchar,
    primary key (id),
    constraint fk_region_id foreign key (registration_region_id) references region (id)
);


