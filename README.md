# Project-Order-Spring

<p>O projeto foi realizado em Java, no Spring Framework. PostgreSQL foi utilizado como banco de dados.</p>

<p>O projeto foi feito para uma empresa de controle de pedidos, onde podemos cadastrar produtos, usuários, realizar pedidos e muito mais.</p>

<p>O login é feito por meio do token JWT gerado em "/login", e por meio dele será permitido ou não o acesso as rotas.</p> 


<p>É possível rodar a aplicação pelo Docker, lembre-se de preencher as informações no .env usando como base o .env.example. Após isso digite no terminal: </p>

```bash
docker compose up --build
```

<p>Caso queira rodar a aplicação usando o Maven basta digitar o seguinte comando no terminal:</p>

```bash
./mvnw spring-boot:run -Ptest
```

<p>Para efetuar os testes, use o seguinte dominio: </p>

```
http://order.127.0.0.1.nip.io:8080
```

<img src="https://github.com/AndreTipolt/Project-Order-Spring/blob/main/images/listProducts.png" alt="" style=" display: block; margin-bottom: 40px;">

<hr/>

<h2>Meus Pedidos</h2>
<img src="https://github.com/AndreTipolt/Project-Order-Spring-Angular/blob/dev/images/Orders.png" alt="" style=" display: block; margin-bottom: 40px;">

<hr/>

<h2>Meus Endereços</h2>
<img src="https://github.com/AndreTipolt/Project-Order-Spring-Angular/blob/dev/images/Enderecos.png" alt="" style=" display: block; margin-bottom: 40px;">

<img src="https://github.com/AndreTipolt/Project-Order-Spring/blob/main/images/login.png" alt="" style=" display: block; margin-bottom: 40px;">