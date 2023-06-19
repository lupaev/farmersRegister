--liquibase formatted sql

--changeset sergej:2
insert into region (name, code_region, status) values ( 'region1', 1, 'active' ),
                                                      ( 'region2', 2, 'active' ),
                                                      ( 'region3', 3, 'active' );

insert into farmer (name, legal_form, inn, kpp, ogrn, registration_region_id,
                    date_registration, status)
values
( 'farmer1', 'ip', 11111111, 12121212, 1234567, 1, now(), 'active'),
( 'farmer2', 'org', 22222222, 12121212, 1234567, 2, now(), 'active'),
( 'farmer3', 'fl', 33333333, 12121212, 1234567, 3, now(), 'active');





