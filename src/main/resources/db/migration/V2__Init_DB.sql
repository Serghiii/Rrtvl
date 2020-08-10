create table visits (
  id bigint not null auto_increment,
  date date not null,
  btime time not null,
  etime time not null,
  name varchar(255) not null,
  primary key (id),
  unique index uniq (date ASC, btime ASC, etime ASC)
);
