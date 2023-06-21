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
    add constraint FKq7l8q39dcodojvpvdtwb01k2m foreign key (regions_id) references region;

alter table farmer_regions
    add constraint FKggmkncr3omth46bpv1kppq64k foreign key (farmer_id) references farmer;



