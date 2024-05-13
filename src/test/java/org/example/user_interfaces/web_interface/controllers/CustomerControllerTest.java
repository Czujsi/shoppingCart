package org.example.user_interfaces.web_interface.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.example.account.UserId;
import org.example.cart_components.Cart;
import org.example.cart_components.CartId;
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
    private final CartId cartId = new CartId("4562c2d7-5a73-4427-b7a0-e00cfea7a755");
    CouponManager couponManager = new CouponManagerImpl(discountRepository);
    private final UserId userId = new UserId();
    private final Cart cart = new Cart(cartId, couponManager);
    private final CsvConverter csvConverter = new CsvConverter();
    private final ProductRepositoryFileImpl productRepositoryFile = new ProductRepositoryFileImpl(csvConverter);
    private final ProductManager productManager = new ProductManagerImpl(productRepositoryFile);
    private final ProductManagerService productManagerService = new ProductManagerService(productManager);


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