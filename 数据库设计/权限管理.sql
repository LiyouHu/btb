/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018/10/26 22:53:43                          */
/*==============================================================*/


drop table if exists auth_function;

drop table if exists auth_role;

drop table if exists role_function;

drop table if exists emp_role;

/*==============================================================*/
/* Table: auth_function                                         */
/*==============================================================*/
create table auth_function
(
   id                   varchar(32) not null,
   name                 varchar(255),
   code                 varchar(255),
   description          varchar(255),
   page                 varchar(255),
   generatemenu         varchar(255),
   zindex               int,
   pid                  varchar(32),
   primary key (id),
   key AK_Key_2 (name)
);

/*==============================================================*/
/* Table: auth_role                                             */
/*==============================================================*/
create table auth_role
(
   id                   varchar(32) not null,
   name                 varchar(255),
   code                 varchar(255),
   description          varchar(255),
   primary key (id),
   key AK_Key_2 (name)
);

/*==============================================================*/
/* Table: role_function                                         */
/*==============================================================*/
create table role_function
(
   role_id              varchar(32) not null,
   function_id          varchar(32) not null,
   primary key (role_id, function_id)
);


/*==============================================================*/
/* Table: emp_role                                             */
/*==============================================================*/
create table emp_role
(
   emp_id              bigint(20) not null,
   role_id              varchar(32) not null,
   primary key (emp_id, role_id)
);

alter table auth_function add constraint FK_Reference_111 foreign key (pid)
      references auth_function (id) on delete restrict on update restrict;

alter table role_function add constraint FK_Reference_221 foreign key (function_id)
      references auth_function (id) on delete restrict on update restrict;

alter table role_function add constraint FK_Reference_331 foreign key (role_id)
      references auth_role (id) on delete restrict on update restrict;

alter table emp_role add constraint FK_Reference_441 foreign key (emp_id)
      references emp(eid) on delete restrict on update restrict;

alter table emp_role add constraint FK_Reference_551 foreign key (role_id)
      references auth_role (id) on delete restrict on update restrict;

