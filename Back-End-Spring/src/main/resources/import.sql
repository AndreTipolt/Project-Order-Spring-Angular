INSERT INTO tb_user(id, name, email, password) VALUES ('1','André Tipolt Lopes', 'andretipoltlopes@gmail.com', '$2a$10$u6g4Y6BSt6e7iM2LnSxfOuvSu6737p2g41uhz0KbqKuuNHhAlRhM6');
INSERT INTO tb_user(id, name, email, password) VALUES ('2','Luana Rodrigues', 'luanarodrigues@gmail.com', '$2a$10$VaFKWo1BkCRJNuK73at3v.3kiY1iRfrcYvr13f1BtegGj5fwlQiui');
INSERT INTO tb_user(id, name, email, password) VALUES ('3','Daniela Figueiredo', 'danielafigueiredo@gmail.com', '$2a$10$VaFKWo1BkCRJNuK73at3v.3kiY1iRfrcYvr13f1BtegGj5fwlQiui');


INSERT INTO tb_role(id, authority) VALUES ('1', 'ROLE_OPERATOR');
INSERT INTO tb_role(id, authority) VALUES ('2', 'ROLE_ADMIN');

INSERT INTO tb_user_role(user_id, role_id) VALUES ('1', '2');
INSERT INTO tb_user_role(user_id, role_id) VALUES ('1', '1');
INSERT INTO tb_user_role(user_id, role_id) VALUES ('2', '1');

INSERT INTO tb_category(id, name) VALUES ('1', 'Tecnologia');
INSERT INTO tb_category(id, name) VALUES ('2', 'Aparelhos Domésticos');


INSERT INTO tb_product(id, name, spot_price, category_id, foward_price, installments, description, pix_discount, imageurl) VALUES ('1', 'Televisão 40 Polegadas', 1999.99, '1', 2200.00, 12, 'Televisão de 40 polegadas', 5, 'https://imgs.casasbahia.com.br/1561529029/1xg.jpg');
INSERT INTO tb_product(id, name, spot_price, category_id, foward_price, installments, description, pix_discount, imageurl) VALUES ('2','Mouse Gamer', 199.99, '1', 230.00, 3, 'Mouse Gamer da marca X', 10, 'https://a-static.mlcdn.com.br/450x450/mouse-gamer-luminoso-usb-e-rgb/inoveaqui/16033937231/7a5d258eb83c3b3e08c993820cfa85d0.jpeg');
INSERT INTO tb_product(id, name, spot_price, category_id, foward_price, installments, description, pix_discount, imageurl) VALUES ('3','Geladeira 2 Portas Frost Free', 2499.99, '2', 2700.00, 12, 'Geladeira de duas portas', 0, 'https://images-americanas.b2w.io/produtos/2487659586/imagens/refrigerador-528-litros-2-portas-frost-free-side-by-side-midea/2830375055_1_large.jpg');

INSERT INTO tb_order(id, moment, client_id, status) VALUES ('1','2023-06-02 15:39:41.577911-03', '1', 1)

INSERT INTO tb_order_item(product_id, order_id, quantity) VALUES ('2', '1', 2);
INSERT INTO tb_order_item(product_id, order_id, quantity) VALUES ('3', '1', 2);