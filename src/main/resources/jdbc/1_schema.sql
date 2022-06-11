drop table if exists genre;
drop table if exists movie;

create table movie(
  id bigserial not null primary key,
  last_modified timestamp not null,
  aggregate_id varchar(36) not null,
  content varchar(4096) not null
);

create table genre(
  id bigserial not null primary key,
  name varchar(255) not null,
  movie bigint not null,
  foreign key (movie) references movie(id)
);

-- alter table genre
--   add foreign key (movie)
--   references movie(id)
