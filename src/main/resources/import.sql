

INSERT INTO tb_user(id, name, email, password) VALUES ('1','André Tipolt Lopes', 'andretipoltlopes@gmail.com', '$2a$10$KG1rXZc1PsexHQRvKV8f8OJFJk.wEYfK6kaOVcj/XRiLWrNayeVma');


INSERT INTO tb_category(id, name) VALUES ('1', 'Tecnologia');
INSERT INTO tb_category(id, name) VALUES ('2', 'Aparelhos Domésticos');


INSERT INTO tb_product(id, name, price, category_id) VALUES ('1','Televisão 40 Polegadas', 1999.99, '1');
INSERT INTO tb_product(id, name, price, category_id) VALUES ('2','Mouse Gamer', 199.99, '1');

INSERT INTO tb_product(id, name, price, category_id) VALUES ('3','Geladeira 2 Portas Frost Free', 2499.99, '2');