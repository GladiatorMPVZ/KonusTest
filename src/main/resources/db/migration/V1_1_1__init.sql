create table author
(
    id         bigserial
        primary key,
    birth_year integer,
    death_year integer,
    first_name varchar(255),
    last_name  varchar(255)
);

alter table author
    owner to postgres;

create table genre
(
    id    bigserial
        primary key,
    title varchar(255)
);

alter table genre
    owner to postgres;

create table book
(
    id               bigserial
        primary key,
    isbn             varchar(255),
    pages            integer,
    publication_year integer,
    title            varchar(255),
    author_id        bigint
        constraint fkklnrv3weler2ftkweewlky958
            references author,
    genre_id         bigint
        constraint fkm1t3yvw5i7olwdf32cwuul7ta
            references genre
);

alter table book
    owner to postgres;

