delete from T_USER_ROLE;
delete from T_ROLE;
delete from T_USER;

insert into T_ROLE values(1, 'ROLE_USER', '一般ロール');
insert into T_ROLE values(2, 'ROLE_ADMIN', '管理ロール');

insert into T_USER values(1, 'user', 'userpassword', '一般 太郎', '開発部');
insert into T_USER values(2, 'admin', 'adminpassword', '管理 次郎', '管理部');

insert into T_USER_ROLE values(1, 1);
insert into T_USER_ROLE values(2, 2);
