create table news (
  id bigint not null auto_increment,
  date datetime not null,
  title varchar(255) not null,
  description varchar(255),
  content text,
  img mediumblob,
  primary key (id)
);

create table photogallery (
  id bigint not null auto_increment,
  date datetime not null,
  title varchar(255) not null,
  filename varchar(255),
  primary key (id)
);

create table images (
  id bigint not null auto_increment,
  filename varchar(255) not null,
  photogallery_id bigint  not null,
  primary key (id),
  key `images_id_fk_idx` (`photogallery_id`),
  constraint `images_id_fk` foreign key (`photogallery_id`) references `photogallery` (`id`)
);

create table videogallery (
  id bigint not null auto_increment,
  date datetime not null,
  title varchar(255) not null,
  filename varchar(255),
  videofilename varchar(255),
  primary key (id)
);
