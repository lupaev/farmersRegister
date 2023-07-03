--liquibase formatted sql

--changeset sergej:2
--Наполнение таблиц тестовыми данными
insert into region (name, code_region, status) values ( 'region1', 1, 'ACTIVE' ),
                                                      ( 'region2', 2, 'ACTIVE' ),
                                                      ( 'region3', 3, 'ACTIVE' );

insert into farmer (name, legal_form, inn, kpp, ogrn, registration_region_id,
                    date_registration, status)
values
( 'farmer1', 'IP', '11111111', '12121212', '1234567', 1, '2001-01-01', 'ACTIVE'),
( 'farmer2', 'ORG', '22222222', '12121212', '1234567', 2, '2001-01-01', 'ACTIVE'),
( 'farmer3', 'FL', '33333333', '12121212', '1234567', 3, '2001-01-01', 'ACTIVE');





