create sequence hibernate_sequence start 1 increment 1;

create table player
(
    id int8 not null,
    comment varchar(255),
    name    varchar(255),
    primary key (id)
);

create table message
(
    id int8 not null,
    tag  varchar(255),
    filename varchar(255),
    text     varchar(255),
    user_id int8,
    primary key (id)
);

create table user_role
(
    user_id int8 not null,
    roles varchar(255)
);

create table usr
(
    id int8 not null,
    activation_code varchar(255),
    active          boolean not null,
    email           varchar(255),
    password        varchar(255) not null,
    username        varchar(255) not null,
    primary key (id)
);

create table user_subscriptions(
                                   channel_id int8 not null references usr,
                                   subscriber_id int8 not null references usr,
                                   primary key (channel_id,subscriber_id)
);
alter table if exists message add constraint message_user_fk foreign key (user_id) references usr;

alter table if exists user_role add constraint user_role_user_fk foreign key (user_id) references usr;
