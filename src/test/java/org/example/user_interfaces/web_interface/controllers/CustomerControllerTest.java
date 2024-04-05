package org.example.user_interfaces.web_interface.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.example.account.UserId;
import org.example.cart_components.Cart;
import org.example.config.serializers.MoneyJacksonSerializer;
import org.example.coupons.discount.repository.DiscountRepository;
import org.example.coupons.manager.CouponManager;
import org.example.coupons.manager.CouponManagerImpl;
import org.example.currency_exchange_money.Currency;
import org.example.currency_exchange_money.Money;
import org.example.product.ProductDefinition;
import org.example.product.components.Name;
import org.example.product.components.Price;
import org.example.product.converters.CsvConverter;
import org.example.product.manager.ProductManager;
import org.example.product.manager.ProductManagerImpl;
import org.example.product.repository.ProductRepositoryFileImpl;
import org.example.user_interfaces.web_interface.services.ProductManagerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.function.Consumer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
class CustomerControllerTest {

    RestClient restClient = RestClient.create();
    @Autowired
    ObjectMapper objectMapper;

    @LocalServerPort
    Long port;
    static final ProductDefinition EXAMPLE_PRODUCT = new ProductDefinition(new Name("Milk"), new Price(Money.of(BigDecimal.TEN, Currency.PLN)), LocalDate.now());

    private final DiscountRepository discountRepository = new DiscountRepository();
    CouponManager couponManager = new CouponManagerImpl(discountRepository);
    private final UserId userId = new UserId();
    private final Cart cart = new Cart(couponManager, userId);
    private final CsvConverter csvConverter = new CsvConverter();
    private final ProductRepositoryFileImpl productRepositoryFile = new ProductRepositoryFileImpl(csvConverter);
    private final ProductManager productManager = new ProductManagerImpl(productRepositoryFile);
    private final ProductManagerService productManagerService = new ProductManagerService(productManager);


    @Test
    void name() throws JsonProcessingException {
//        String abc = "{\"name\": \"oskar\"}";
//        String product = "{\"price\": \"2.65 PLN\"" +
//                ",\"name\": \"banana3\",\"creationDate\": \"2024-02-06\"" +
//                ",\"productId\": \"2f5cd2c6-a71c-4c57-a06a-bf4c422e9a33\"}";
//        JsonNode root = objectMapper.readTree(abc);
//
//        CustomerController.Test root1 = objectMapper.readValue(abc, CustomerController.Test.class);
//        CustomerController.Test root2 = objectMapper.treeToValue(root, CustomerController.Test.class);
//
////        // json { }
////        // root -> konwencyjna nazwa
////        ObjectNode root = objectMapper.createObjectNode();
////
////        // odpowiednik jsona "Oskar"
////        TextNode name = objectMapper.getNodeFactory().textNode("Oskar");
////
////        // tworzy jsona {"name":"Oskar"}
////        root.set("name", name);
////
////        log.info("{} {}", root, root.toPrettyString());
//
//
//        CustomerController.Test body = new CustomerController.Test();
//        //body.setName("Oskar");
//        var response = restClient.post()
//                .uri("http://localhost:" + port + "/customer/" + "2f5cd2c6-a71c-4c57-a06a-bf4c422e9a56")
//                .contentType(APPLICATION_JSON)
//                .body(root2)
//                .retrieve()
//                .toEntity(CustomerController.Test.class);
//        log.info("{}", response);

    }

    @Test
    void name1() {
        Money money = Money.of(BigDecimal.ONE, Currency.PLN);

        JsonNode root = objectMapper.valueToTree(money);

        log.info("{}", root.toString());
        Assertions.assertThat(root.toString()).isEqualTo("\"1 PLN\"");
    }

    @Test
    void name3() {
        Money money = Money.of(BigDecimal.ONE, Currency.PLN);

        JsonNode root = new ObjectMapper().valueToTree(money);

        log.info("{}", root.toString());
        Assertions.assertThat(root.toString()).isEqualTo("{\"amount\":1,\"currency\":\"PLN\"}");
    }

    @Test
    void name4() {
        Money money = Money.of(BigDecimal.ONE, Currency.PLN);

        ObjectMapper objectMapper1 = getSerializerObjectMapper(simpleModule -> simpleModule.addSerializer(Money.class, new MoneyJacksonSerializer()));

        JsonNode root = objectMapper1.valueToTree(money);

        log.info("{}", root.toString());
        Assertions.assertThat(root.toString()).isEqualTo("\"1 PLN\"");
    }

    private static ObjectMapper getSerializerObjectMapper(Consumer<SimpleModule> consumer) {
        ObjectMapper objectMapper1 = new ObjectMapper();

        SimpleModule module = new SimpleModule();
        consumer.accept(module);
        objectMapper1.registerModule(module);
        return objectMapper1;
    }
}