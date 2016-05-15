# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table borrowerdetails (
  id                        bigserial not null,
  firstname                 varchar(255),
  lastname                  varchar(255),
  address                   varchar(255),
  city                      varchar(255),
  state                     varchar(255),
  zip                       varchar(255),
  email                     varchar(255),
  phone                     varchar(255),
  constraint pk_borrowerdetails primary key (id))
;

create table comments (
  cid                       bigserial not null,
  user_id                   bigint,
  tool_id                   bigint,
  commentbody               varchar(255),
  dt                        timestamp,
  constraint pk_comments primary key (cid))
;

create table tools (
  id                        bigserial not null,
  toolname                  varchar(255),
  description               varchar(255),
  owner                     varchar(255),
  category                  varchar(255),
  constraint pk_tools primary key (id))
;

create table users (
  id                        bigserial not null,
  username                  varchar(255),
  password_hash             varchar(255),
  firstname                 varchar(255),
  lastname                  varchar(255),
  address                   varchar(255),
  email                     varchar(255),
  phone                     varchar(255),
  constraint uq_users_username unique (username),
  constraint pk_users primary key (id))
;

alter table comments add constraint fk_comments_user_1 foreign key (user_id) references users (id);
create index ix_comments_user_1 on comments (user_id);
alter table comments add constraint fk_comments_tool_2 foreign key (tool_id) references tools (id);
create index ix_comments_tool_2 on comments (tool_id);



# --- !Downs

drop table if exists borrowerdetails cascade;

drop table if exists comments cascade;

drop table if exists tools cascade;

drop table if exists users cascade;

