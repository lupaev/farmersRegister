--liquibase formatted sql

--changeset sergej:4
-- create table farmer_regions
-- (
--     farmer_id bigint not null ,
--     region_id bigint not null ,
--     foreign key (farmer_id) references farmer (id),
--     foreign key (region_id) references region (id)
--
-- );

create table farmer_regions
(
    farmer_id  bigint not null,
    regions_id bigint not null
);
alter table farmer_regions
    add constraint fk_regions_id foreign key (regions_id) references region on delete cascade;

alter table farmer_regions
    add constraint fk_farmer_id foreign key (farmer_id) references farmer on delete cascade;



