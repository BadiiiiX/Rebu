create table if not exists cars
(
    car_plate             varchar(15) not null
        primary key,
    car_passengers_number integer     not null
        constraint cars_passengers_number_check
            check ((car_passengers_number >= 1) AND (car_passengers_number <= 9)),
    user_id               integer     not null
        constraint cars_users_user_id_fk
            references users
);