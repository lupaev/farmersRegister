--liquibase formatted sql

--changeset sergej:5
--очистка тестовых данных в таблицах

truncate table farmer, region, farmer_regions;



