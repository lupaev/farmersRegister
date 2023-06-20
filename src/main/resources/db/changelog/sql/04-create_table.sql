--liquibase formatted sql

--changeset sergej:4
create table farmer_region_fields
(
    farmer_id bigint,
    region_id bigint,
    foreign key (farmer_id) references farmer (id),
    foreign key (region_id) references region (id)

);



