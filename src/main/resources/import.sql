

INSERT INTO tb_user(id, name, email, password) VALUES ('1','André Tipolt Lopes', 'andretipoltlopes@gmail.com', '$2a$10$u6g4Y6BSt6e7iM2LnSxfOuvSu6737p2g41uhz0KbqKuuNHhAlRhM6');
INSERT INTO tb_user(id, name, email, password) VALUES ('2','Luana Rodrigues', 'luanarodrigues@gmail.com', '$2a$10$VaFKWo1BkCRJNuK73at3v.3kiY1iRfrcYvr13f1BtegGj5fwlQiui');
INSERT INTO tb_user(id, name, email, password) VALUES ('3','Daniela Figueiredo', 'danielafigueiredo@gmail.com', '$2a$10$VaFKWo1BkCRJNuK73at3v.3kiY1iRfrcYvr13f1BtegGj5fwlQiui');


INSERT INTO tb_role(id, authority) VALUES ('1', 'OPERATOR');
INSERT INTO tb_role(id, authority) VALUES ('2', 'ADMIN');

INSERT INTO tb_user_role(user_id, role_id) VALUES ('1', '2');
INSERT INTO tb_user_role(user_id, role_id) VALUES ('1', '1');
INSERT INTO tb_user_role(user_id, role_id) VALUES ('2', '1');

INSERT INTO tb_category(id, name) VALUES ('1', 'Tecnologia');
INSERT INTO tb_category(id, name) VALUES ('2', 'Aparelhos Domésticos');


INSERT INTO tb_product(id, name, price, category_id) VALUES ('1','Televisão 40 Polegadas', 1999.99, '1');
INSERT INTO tb_product(id, name, price, category_id) VALUES ('2','Mouse Gamer', 199.99, '1');

INSERT INTO tb_product(id, name, price, category_id) VALUES ('3','Geladeira 2 Portas Frost Free', 2499.99, '2');

INSERT INTO tb_order(id, moment, client_id, status) VALUES ('1','2023-06-02 15:39:41.577911-03', '1', '1')

INSERT INTO tb_order_item(product_id, order_id, quantity) VALUES ('2', '1', 2);
INSERT INTO tb_order_item(product_id, order_id, quantity) VALUES ('3', '1', 2);
