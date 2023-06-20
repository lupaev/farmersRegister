--liquibase formatted sql

--changeset sergej:1
create table region
(
    id bigserial primary key ,
    name varchar not null ,
    code_region integer not null ,
    status varchar not null
);
create table farmer
(
    id bigserial primary key ,
    name varchar not null ,
    legal_form varchar not null ,
    inn integer not null ,
    kpp integer not null ,
    ogrn integer not null ,
    registration_region_id bigint not null ,
    date_registration date not null ,
    status varchar not null ,
    constraint fk_region_id foreign key (registration_region_id) references region (id)
);


