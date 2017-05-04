CREATE SCHEMA orderfood AUTHORIZATION postgres;

SET search_path = 'orderfood';
SHOW search_path;


CREATE TABLE orderfood.produto (
  id INTEGER NOT NULL,
  nome VARCHAR(80),
  CONSTRAINT produto_pkey PRIMARY KEY(id)
) ;

insert into produto (ID, NAME) values (1, 'arroz');
insert into produto (ID, NAME) values (2, 'Mr. Foo');
insert into produto (ID, NAME) values (3, 'Ms. Bar');