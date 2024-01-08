// package tipolt.andre.spring.controllers;

// import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Test;
// import org.mockito.Mock;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.boot.json.JacksonJsonParser;
// import org.springframework.http.MediaType;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.ResultActions;
// import org.springframework.util.LinkedMultiValueMap;
// import org.springframework.util.MultiValueMap;

// import com.fasterxml.jackson.databind.ObjectMapper;

// import tipolt.andre.spring.ApplicationTestConfig;
// import tipolt.andre.spring.dtos.OrderDTO;
// import tipolt.andre.spring.factories.OrderFactory;
// import tipolt.andre.spring.repositories.OrderRepository;
// import tipolt.andre.spring.services.OrderService;

// public class OrderControllerTests extends ApplicationTestConfig {

//     @Mock
//     private OrderService orderService;

//     @Mock
//     private OrderRepository orderRepository;

//     @Autowired
//     private MockMvc mockMvc;

//     @Autowired
//     private ObjectMapper objectMapper;

//     @Value("${security.oauth2.client.client-id}")
//     private String clientId;

//     @Value("${security.oauth2.client.client-secret}")
//     private String clientSecret;

//     private String username;
//     private String password;

//     private String acessToken;

//     @BeforeEach
//     void setUp() throws Exception {
//         this.username = "andretipoltlopes@gmail.com";
//         this.password = "andre1234";
//         this.acessToken = obtainAccessToken(username, password);
//     }

//     @Test
//     @DisplayName("Find All should return list of orders")
//     public void findAllShouldReturnListOfOrders() throws Exception {

//         ResultActions result = mockMvc.perform(get("/orders")
//                 .header("Authorization", "Bearer " + acessToken)
//                 .accept(MediaType.APPLICATION_JSON));

//         result.andExpect(status().isOk());
//     }

//     @Test
//     @DisplayName("Save Order should return not found when userId does not exists")
//     public void saveOrderShouldReturnNotFoundWhenUserIdDoesNotExists() throws Exception {

//         OrderDTO orderWithInvalidUserId = OrderFactory.createOrderDTOWithInvalidUserId();

//         String jsonBody = objectMapper.writeValueAsString(orderWithInvalidUserId);

//         ResultActions result = mockMvc.perform(post("/orders")
//                 .content(jsonBody)
//                 .header("Authorization", "Bearer " + acessToken)
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .accept(MediaType.APPLICATION_JSON));

//         result.andExpect(status().isNotFound());
//     }

//     @Test
//     @DisplayName("Save Order should return not found when someone productId does not exists")
//     public void saveOrderShouldReturnNotFoundWhenSomeoneProductIdDoesNotExists() throws Exception {

//         OrderDTO orderWithInvalidProductId = OrderFactory.createOrderDTOWithInvalidProductID();

//         String jsonBody = objectMapper.writeValueAsString(orderWithInvalidProductId);

//         ResultActions result = mockMvc.perform(post("/orders")
//                 .content(jsonBody)
//                 .header("Authorization", "Bearer " + acessToken)
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .accept(MediaType.APPLICATION_JSON));

//         result.andExpect(status().isNotFound());
//     }

//     @Test
//     @DisplayName("save order should return unprocessable entity when someone field is missing")
//     public void saveOrderShouldReturnUnprocessableEntityWhenSomeoneFieldIsMissing() throws Exception {
//         OrderDTO orderDTOWithoutUserIdField = OrderFactory.createOrderDTOWithoutUserIdField();

//         String jsonBody = objectMapper.writeValueAsString(orderDTOWithoutUserIdField);

//         ResultActions result = mockMvc.perform(post("/orders")
//                 .content(jsonBody)
//                 .header("Authorization", "Bearer " + acessToken)
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .accept(MediaType.APPLICATION_JSON));

//         result.andExpect(status().isUnprocessableEntity());
//     }

//     @Test
//     @DisplayName("Save Order should return created when orderDTO is valid")
//     public void saveOrderShouldReturnCreatedWhenOrderDTOIsValid() throws Exception {

//         OrderDTO orderDTO = OrderFactory.createOrderDTO();

//         String jsonBody = objectMapper.writeValueAsString(orderDTO);

//         ResultActions result = mockMvc.perform(post("/orders")
//                 .content(jsonBody)
//                 .header("Authorization", "Bearer " + acessToken)
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .accept(MediaType.APPLICATION_JSON));

//         result.andExpect(status().isNoContent());
//     }

//     private String obtainAccessToken(String username, String password) throws Exception {

// 		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
// 		params.add("grant_type", "password");
// 		params.add("client_id", clientId);
// 		params.add("username", username);
// 		params.add("password", password);

// 		ResultActions result = mockMvc
// 				.perform(post("/oauth/token").params(params).with(httpBasic(clientId, clientSecret))
// 						.accept("application/json;charset=UTF-8"))
// 				.andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"));

// 		String resultString = result.andReturn().getResponse().getContentAsString();

// 		JacksonJsonParser jsonParser = new JacksonJsonParser();
// 		return jsonParser.parseMap(resultString).get("access_token").toString();
// 	}	
// }
