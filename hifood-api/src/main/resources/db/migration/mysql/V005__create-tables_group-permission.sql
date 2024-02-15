create table group_security (
	id bigint not null auto_increment,
	name varchar(60) not null,

	primary key (id)
) engine=InnoDB default charset=utf8;

create table group_security_permission (
	group_security_id bigint not null,
	permission_id bigint not null,

	primary key (group_security_id, permission_id)
) engine=InnoDB default charset=utf8;

create table permission (
	id bigint not null auto_increment,
	description varchar(60) not null,
	name varchar(100) not null,

	primary key (id)
) engine=InnoDB default charset=utf8;

alter table group_security_permission add constraint fk_group_security_permission_permission
foreign key (permission_id) references permission (id);

alter table group_security_permission add constraint fk_group_security_permission_group_security
foreign key (group_security_id) references group_security (id);