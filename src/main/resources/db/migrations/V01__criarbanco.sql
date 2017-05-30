------------------------------- PRODUTOCREATE SEQUENCE produto_produto_id_seq  INCREMENT 1  MINVALUE 1  MAXVALUE 9223372036854775807  START 1  CACHE 1;CREATE TABLE orderfood.produto (  id INTEGER NOT NULL DEFAULT NEXTVAL('produto_produto_id_seq'),  nome VARCHAR(60) NOT NULL,  descricao VARCHAR(80) NOT NULL,  foto VARCHAR(100),  contenttype VARCHAR(100),  volume NUMERIC(6,3),  valor NUMERIC(12,2) NOT NULL,  unidade VARCHAR(20) NOT NULL,  ativo BOOLEAN DEFAULT FALSE,  qtestoque INTEGER NOT NULL,  categoria VARCHAR(20) NOT NULL,  CONSTRAINT produto_id PRIMARY KEY(id)) WITH (oids = false);------------------------------- USUARIOCREATE SEQUENCE usuario_usuario_id_seq  INCREMENT 1  MINVALUE 1  MAXVALUE 9223372036854775807  START 1  CACHE 1;CREATE TABLE orderfood.usuario (    id INTEGER NOT NULL DEFAULT NEXTVAL('usuario_usuario_id_seq'),    nome VARCHAR(60) NOT NULL,    email VARCHAR(50) NOT NULL,    senha VARCHAR(120) NOT NULL,    ativo BOOLEAN DEFAULT TRUE,    data_nascimento DATE,   CONSTRAINT usuario_id PRIMARY KEY(id)) WITH (oids = false);------------------------------- GRUPOCREATE TABLE orderfood.grupo (    id INTEGER NOT NULL,    nome VARCHAR(50) NOT NULL,CONSTRAINT grupo_id PRIMARY KEY(id)) WITH (oids = false);------------------------------- PERMISSAOCREATE TABLE orderfood.permissao (    id INTEGER NOT NULL,    nome VARCHAR(50) NOT NULL,CONSTRAINT permissao_id PRIMARY KEY(id)) WITH (oids = false);-------------------------------TABELA RELACIONAMENTO USUARIO GRUPOCREATE TABLE orderfood.usuario_grupo (    id_usuario INTEGER NOT NULL,    id_grupo INTEGER NOT NULL,    PRIMARY KEY (id_usuario, id_grupo),    FOREIGN KEY (id_usuario) REFERENCES orderfood.usuario(id),    FOREIGN KEY (id_grupo) REFERENCES orderfood.grupo(id)) WITH (oids = false);-------------------------------TABELA RELACIONAMENTO GRUPO PERMISSAOCREATE TABLE orderfood.grupo_permissao (    id_grupo INTEGER NOT NULL,    id_permissao INTEGER NOT NULL,    PRIMARY KEY (id_grupo, id_permissao),    FOREIGN KEY (id_grupo) REFERENCES orderfood.grupo(id),    FOREIGN KEY (id_permissao) REFERENCES orderfood.permissao(id)) WITH (oids = false);INSERT INTO orderfood.grupo (id, nome) VALUES (1, 'Administrador');INSERT INTO orderfood.grupo (id, nome) VALUES (2, 'Atendimento');INSERT INTO orderfood.grupo (id, nome) VALUES (3, 'Cozinha');--------------------------------- CABPEDIDOCREATE TABLE orderfood.cabpedido (  numped INTEGER NOT NULL,  datacriacao DATE,  valordesconto NUMERIC(12,6),  valortotal NUMERIC(12,6),  observacao VARCHAR(60),  dataentrega DATE,  datacancel DATE,  status VARCHAR(15),  idcliente INTEGER,  idmesa INTEGER,  idusuario INTEGER,  CONSTRAINT cabpedido_pkey PRIMARY KEY(numped)) WITH (oids = false);   ------------------------------ 	ITEMPEDIDOCREATE TABLE orderfood.itempedido (  numped INTEGER NOT NULL,  produto INTEGER NOT NULL,  quantidade INTEGER NOT NULL,  valorunitario INTEGER NOT NULL,  CONSTRAINT itempedido_pkey PRIMARY KEY(numped, produto)) WITH (oids = false);CREATE UNIQUE INDEX uniq_ped ON orderfood.itempedido  USING btree (numped, produto);  ALTER TABLE orderfood.itempedido  ADD CONSTRAINT itempedido_fk FOREIGN KEY (produto)  REFERENCES orderfood.produto(id)    ON DELETE NO ACTION    ON UPDATE NO ACTION    NOT DEFERRABLE;              ------------------------------ 	MESA     CREATE SEQUENCE mesa_mesa_id_seq  INCREMENT 1  MINVALUE 1  MAXVALUE 9223372036854775807  START 3  CACHE 1;    CREATE TABLE orderfood.mesa (  idmesa INTEGER NOT NULL DEFAULT NEXTVAL('mesa_mesa_id_seq'),  descricao VARCHAR(40) NOT NULL,  status VARCHAR(20),  CONSTRAINT idmesa_pkey PRIMARY KEY(idmesa)) WITH (oids = false);     