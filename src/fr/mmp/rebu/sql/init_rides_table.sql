create table if not exists rides
(
    ride_id          serial
        constraint rides_pk
            primary key,
    car_plate        varchar(15)
        constraint rides_cars_car_plate_fk
            references cars,
    driver_id        integer
        constraint rides_users_user_id_fk
            references users,
    ride_origin      varchar(100),
    ride_destination varchar(100),
    ride_start_date  timestamp
);

create table if not exists ride_passengers
(
    ride_id      integer not null
        constraint ride_passengers_rides_ride_id_fk
            references rides,
    passenger_id integer not null
        constraint ride_passengers_users_user_id_fk
            references users,
    constraint ride_passengers_pk
        primary key (ride_id, passenger_id)
);