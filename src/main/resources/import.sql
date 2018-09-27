insert into role (role_id,role) values(1,'ADMIN');
insert into role (role_id,role) values(2,'USER');

insert into user (user_id,active, email, last_name, name, password) values (99,1,"admin@admin.com","admin","admin","$2a$10$OeCwWQdSt1vnkmeZbySstefqaTtlTpM8tYKNzlGQZzQ/MgorJw/Va");

insert into user_role (user_id,role_id) values (99,1);