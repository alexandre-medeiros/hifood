create table users (
	id bigint not null auto_increment,
	name varchar(80) not null,
	email varchar(255) not null,
	password varchar(255) not null,
	created_at datetime not null,

	primary key (id)
) engine=InnoDB default charset=utf8;

create table users_group (
	users_id bigint not null,
	group_id bigint not null,

	primary key (users_id, group_id)
) engine=InnoDB default charset=utf8;

alter table users_group add constraint fk_users_group_group
foreign key (group_id) references group_security (id);

alter table users_group add constraint fk_users_group_users
foreign key (users_id) references users (id);
