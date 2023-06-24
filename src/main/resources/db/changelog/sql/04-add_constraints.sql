--liquibase formatted sql

--changeset sergej:4
--Создание ограничений для вспомогательной таблицы farmer_regions

alter table farmer_regions
    add constraint fk_regions_id foreign key (regions_id) references region on delete cascade;

alter table farmer_regions
    add constraint fk_farmer_id foreign key (farmer_id) references farmer on delete cascade;



