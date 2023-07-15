--liquibase formatted sql

--changeset sergej:2
--Наполнение таблиц тестовыми данными
insert into region (name, code_region)
values ('region1', 1),
       ('region2', 2),
       ('region3', 3);

insert into farmer (name, legal_form, inn, kpp, ogrn, registration_region_id,
                    date_registration)
values ('farmer1', 'IP', '11111111', '12121212', '1234567', 1, '2001-01-01'),
       ('farmer2', 'ORG', '22222222', '12121212', '1234567', 2, '2001-01-01'),
       ('farmer3', 'FL', '33333333', '12121212', '1234567', 3, '2001-01-01');

insert into farmer_regions (farmer_id, regions_id)
values (1, 2),
       (1, 3),
       (2, 1),
       (2, 3),
       (3, 2),
       (3, 1);



