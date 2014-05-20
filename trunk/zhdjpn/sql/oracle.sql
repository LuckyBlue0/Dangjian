-- Create table(apn_user表)
create table APN_USER
(
  id           NUMBER not null,
  username     VARCHAR2(64),
  password     VARCHAR2(64),
  email        VARCHAR2(64),
  name         VARCHAR2(64),
  created_date DATE,
  updated_date DATE
);

-- Add comments to the columns 
comment on column APN_USER.username
  is '用户名(设备编号)';
comment on column APN_USER.password
  is '密码';
comment on column APN_USER.email
  is '邮箱';
comment on column APN_USER.name
  is '姓名';
comment on column APN_USER.created_date
  is '创建时间';
comment on column APN_USER.updated_date
  is '更新时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table APN_USER
  add constraint APN_USER_PK primary key (ID);
  
  
  
-- Create table(设备管理表)
create table DEVICE_USER
(
  id            NUMBER not null,
  username      VARCHAR2(64),
  device_number VARCHAR2(200),
  device_type   NUMBER(6),
  created_date  DATE,
  updated_date  DATE
);
-- Add comments to the table 
comment on table DEVICE_USER
  is '设备管理表';
-- Add comments to the columns 
comment on column DEVICE_USER.username
  is '用户名';
comment on column DEVICE_USER.device_number
  is '设备编号';
comment on column DEVICE_USER.device_type
  is '设备类型';
comment on column DEVICE_USER.created_date
  is '创建时间';
comment on column DEVICE_USER.updated_date
  is '更新时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table DEVICE_USER
  add constraint DEVICE_USER_PK primary key (ID);
  
  
