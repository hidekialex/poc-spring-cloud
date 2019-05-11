INSERT INTO oauth_client_details
    (client_id, resource_ids, client_secret, scope, authorized_grant_types,
    web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity,
    additional_information, autoapprove)
VALUES
    ('cliente-app', 'usuarios', '123456',
    'read,write', 'password',
    NULL,
    'read,write', 120, -1, NULL, 'false');


INSERT INTO usuario
	(nome, email, senha)
VALUES
	('Teste', 'teste@teste.com', '123')
