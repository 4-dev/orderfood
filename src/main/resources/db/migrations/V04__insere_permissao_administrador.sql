INSERT into permissao (id, nome) values (1, 'ACESSO_ADMIN');
INSERT into permissao (id, nome) values (2, 'CADASTRAR_USUARIO');
INSERT into permissao (id, nome) values (3, 'CADASTRAR_MESA');
INSERT into permissao (id, nome) values (4, 'CADASTRAR_PRODUTO');
INSERT into permissao (id, nome) values (5, 'LISTAR_MESA');
INSERT into permissao (id, nome) values (6, 'LISTAR_PEDIDO');

INSERT into grupo_permissao (id_grupo, id_permissao) values (1,1);
INSERT into grupo_permissao (id_grupo, id_permissao) values (1,2);
INSERT into grupo_permissao (id_grupo, id_permissao) values (1,3);
INSERT into grupo_permissao (id_grupo, id_permissao) values (1,4);
INSERT into grupo_permissao (id_grupo, id_permissao) values (1,5);
INSERT into grupo_permissao (id_grupo, id_permissao) values (1,6);

INSERT into usuario_grupo (id_usuario, id_grupo) values (
	(select id from usuario where email = 'admin@orderfood.com'), 1);
	 