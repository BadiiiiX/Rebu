create table if not exists users
(
    user_id       serial
        primary key,
    user_email    varchar(50),
    user_username varchar(50),
    user_password varchar(50)
);