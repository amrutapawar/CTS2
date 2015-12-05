# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table tools (
  id                        bigint not null,
  name                      varchar(255),
  description               varchar(255),
  owner                     varchar(255),
  category                  varchar(255),
  comment                   varchar(255),
  toollist                  varchar(255),
  constraint pk_tools primary key (id))
;

create table toolcategory (
  cid                       bigint not null,
  cname                     varchar(255),
  constraint pk_toolcategory primary key (cid))
;

create table user (
  id                        bigint not null,
  name                      varchar(255),
  email                     varchar(255),
  username                  varchar(255),
  password                  varchar(255),
  phone                     varchar(255),
  constraint pk_user primary key (id))
;

create sequence tools_seq;

create sequence toolcategory_seq;

create sequence user_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists tools;

drop table if exists toolcategory;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists tools_seq;

drop sequence if exists toolcategory_seq;

drop sequence if exists user_seq;

