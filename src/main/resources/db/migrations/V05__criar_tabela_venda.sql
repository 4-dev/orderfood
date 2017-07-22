CREATE SEQUENCE venda_venda_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;

CREATE TABLE orderfood.venda (
    id INTEGER NOT NULL DEFAULT NEXTVAL('venda_venda_id_seq'),
    numero_mesa INTEGER,
    valor NUMERIC(12,2),
    data DATE,
   CONSTRAINT venda_id PRIMARY KEY(id)
) 
WITH (oids = false);

ALTER TABLE orderfood.cabpedido
  ADD idvenda INTEGER;
   

