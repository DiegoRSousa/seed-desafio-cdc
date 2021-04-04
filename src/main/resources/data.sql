insert into categoria (nome) values ('Java');

insert into autor (nome, email, descricao) values ('Diego', 'diegors90@gmail.com', 'desenvolvedor java');

insert into livro (titulo, resumo, sumario, preco, numero_de_paginas, isbn, data_de_publicacao, categoria_id, autor_id)
values ('Spring Boot 2.0', 'teste', 'sumario', 20.00, 110, '45233', '2021-12-12', 1, 1);

insert into livro (titulo, resumo, sumario, preco, numero_de_paginas, isbn, data_de_publicacao, categoria_id, autor_id)
values ('Java e Orientacao a Objetos', 'teste', '', 60.00, 120, '446543', '2021-12-13', 1, 1);

insert into pais (nome) values ('Brasil');
insert into pais (nome) values ('Portugal');
insert into pais (nome) values ('Alemanha');

insert into estado (nome, pais_id) values ('Paraíba', 1);
insert into estado (nome, pais_id) values ('São P aulo', 1);

insert into cupom (codigo, percentual, validade) values ('XBS100', 10, '2021-04-02');
insert into cupom (codigo, percentual, validade) values ('XBS101', 10, '2021-04-03');