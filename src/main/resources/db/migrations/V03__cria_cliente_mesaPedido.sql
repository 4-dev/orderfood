

--------------------------------- CLIENTE

CREATE TABLE orderfood.cliente (
  idcliente INTEGER NOT NULL,
  nome VARCHAR(80) NOT NULL,
  sexo VARCHAR(10),
  PRIMARY KEY(idcliente)
) 
WITH (oids = false);	



--------------------------------- MESA_PEDIDO

CREATE TABLE orderfood.mesa_pedido (
  idmesa INTEGER NOT NULL,
  numped INTEGER NOT NULL,  
  data DATE,
  CONSTRAINT mesapedido_pkey PRIMARY KEY(idmesa, numped)
) 
WITH (oids = false);

 
  
  -------------------- TRIGGER MESA PEDIDO
CREATE OR REPLACE FUNCTION orderfood.fnc_mesa_pedido (
)
RETURNS trigger AS
$body$
BEGIN
  INSERT INTO ORDERFOOD.mesa_pedido
  (idmesa, numped, data)
  VALUES
  (new.idmesa, new.numped, CURRENT_DATE);
  RETURN NEW;
  END;
$body$
LANGUAGE 'plpgsql'
VOLATILE
CALLED ON NULL INPUT
SECURITY INVOKER
COST 100;

CREATE TRIGGER "TRG_MESA_PEDIDO"
  AFTER INSERT 
  ON orderfood.cabpedido FOR EACH ROW 
  EXECUTE PROCEDURE orderfood.fnc_mesa_pedido();
  
  
  
-- insert produto
INSERT INTO orderfood.produto ( nome,  descricao,  foto,  contenttype,  volume,  valor,  unidade,  ativo,  qtestoque,  categoria)
VALUES ( 'PRODUTO 1',  ' ESTE É O PRODUTO UM 1',  '',  '',  23,  232,  'UN',  NULL,  29,  'BEBIDAS');

INSERT INTO orderfood.produto (   nome,  descricao,  foto,  contenttype,  volume,  valor,  unidade,  ativo,  qtestoque,  categoria)
VALUES (  'PRODUTO 2',  ' ESTE É O PRODUTO UM 2',  '',  '',  23,  232,  'UN',  NULL,  29,  'BEBIDAS');

-- insert Mesa
INSERT INTO MESA (idmesa, descricao, status) values (1,'MESA 1', 'OCUPADA');
INSERT INTO MESA (idmesa, descricao, status) values (2,'MESA 2', 'LIBERADA');

-- insert Cliente
INSERT INTO cliente (idcliente, nome, sexo) values (1,'cliente 1', 'Masculino');
INSERT INTO cliente (idcliente, nome, sexo) values (2,'cliente 2', 'Feminino');


-- insert pedido
INSERT INTO 
  orderfood.itempedido(  numped,  produto,  quantidade,  valorunitario)
VALUES (  1,  1,  10,  10);


INSERT INTO 
  orderfood.itempedido(  numped,  produto,  quantidade,  valorunitario)
VALUES (  2,  2,  10,  10);


insert into orderfood.cabpedido(  numped ,  datacriacao ,  valordesconto ,  valortotal ,  observacao ,  dataentrega, datacancel ,  status ,  idcliente ,  idmesa ,  idusuario ) 
values (1, CURRENT_DATE, 0, 10, 'teste pedido 1', CURRENT_DATE, null, 'ABERTO', 1, 1, 1);

insert into orderfood.cabpedido(  numped ,  datacriacao ,  valordesconto ,  valortotal ,  observacao ,  dataentrega, datacancel ,  status ,  idcliente ,  idmesa ,  idusuario ) 
values (2, CURRENT_DATE, 0, 10, 'teste pedido 2', CURRENT_DATE, null, 'ABERTO', 1, 1, 1);





