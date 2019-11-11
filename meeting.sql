/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     11/11/2019 3:49:01 PM                        */
/*==============================================================*/


drop table if exists admin;

drop table if exists department;

drop index unique_email on m_user;

drop index unique_employeeid on m_user;

drop table if exists m_user;

drop table if exists meeting;

drop table if exists meetingRuser;

drop table if exists room;

/*==============================================================*/
/* Table: admin                                                 */
/*==============================================================*/
create table admin
(
   id                   bigint not null auto_increment,
   user_name            varchar(255),
   password             varchar(255),
   primary key (id)
);

/*==============================================================*/
/* Table: department                                            */
/*==============================================================*/
create table department
(
   id                   bigint not null auto_increment,
   name                 varchar(255),
   primary key (id)
);

/*==============================================================*/
/* Table: m_user                                                */
/*==============================================================*/
create table m_user
(
   id                   bigint not null,
   name                 varchar(255) not null,
   email                varchar(255) not null,
   phone                varchar(255) not null,
   pre_time             datetime,
   pre_id               varchar(255),
   status               int not null default 1,
   password             varchar(255) not null,
   employee_id          varchar(255) not null,
   department_id        bigint not null,
   primary key (id)
);

/*==============================================================*/
/* Index: unique_employeeid                                     */
/*==============================================================*/
create unique index unique_employeeid on m_user
(
   employee_id
);

/*==============================================================*/
/* Index: unique_email                                          */
/*==============================================================*/
create unique index unique_email on m_user
(
   email
);

/*==============================================================*/
/* Table: meeting                                               */
/*==============================================================*/
create table meeting
(
   id                   bigint not null,
   meeting_name         varchar(255),
   room_id              bigint,
   start_time           datetime,
   end_time             datetime,
   reserve_time         datetime,
   canceled_time        datetime,
   description          text,
   status               int default 0,
   canceled_reason      text,
   primary key (id)
);

/*==============================================================*/
/* Table: meetingRuser                                          */
/*==============================================================*/
create table meetingRuser
(
   meeting_id           bigint not null,
   user_id              bigint not null,
   id                   bigint not null auto_increment,
   primary key (id)
);

/*==============================================================*/
/* Table: room                                                  */
/*==============================================================*/
create table room
(
   id                   bigint not null auto_increment,
   room_num             varchar(255),
   room_name            varchar(255),
   capacity             int,
   area                 decimal(6,2),
   status               int default 0,
   discription          text,
   primary key (id)
);

alter table m_user add constraint FK_Reference_1 foreign key (department_id)
      references department (id) on delete restrict on update restrict;

alter table meeting add constraint FK_Reference_4 foreign key (room_id)
      references room (id) on delete restrict on update restrict;

alter table meetingRuser add constraint FK_Reference_2 foreign key (user_id)
      references m_user (id) on delete restrict on update restrict;

alter table meetingRuser add constraint FK_Reference_3 foreign key (meeting_id)
      references meeting (id) on delete restrict on update restrict;

