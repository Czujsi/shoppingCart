package org.example.user_interfaces.web_interface.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.example.currency_exchange_money.Currency;
import org.example.currency_exchange_money.Money;
import org.example.product.ProductDefinition;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductManagerControllerTest {
    private static final String PRODUCT_IN_JSON = "{\"price\": \"2.65 PLN\",\"name\": \"ahahahahahhaaa\",\"creationDate\": \"2024-02-06\",\"productId\": \"2f5cd2c6-a71c-4c57-a06a-bf4c422e9a33\"}";

    RestClient restClient = RestClient.create();
    @LocalServerPort
    Long port;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void creatingNewProductByProductManagerControllerMethodCreateProduct() throws JsonProcessingException {
        JsonNode root = objectMapper.readTree(PRODUCT_IN_JSON);
        var response = restClient.post()
                .uri("http://localhost:" + port + "/products/create")
                .contentType(APPLICATION_JSON)
                .body(root.toString())
                .retrieve()
                .toEntity(ProductDefinition.class);
        ProductDefinition responseBody = response.getBody();
        assert responseBody != null;
        ProductDefinition PRODUCT_DEFINITION_FROM_PRODUCT_IN_JSON = ProductDefinition.of("ahahahahahhaaa", Money.of(BigDecimal.valueOf(2.65), Currency.PLN), responseBody.getProductId().getValue().toString());
        log.info("{}", response);

        assertThat(responseBody).isEqualTo(PRODUCT_DEFINITION_FROM_PRODUCT_IN_JSON);
    }

    @Test
    void wwww() throws JsonProcessingException {
        JsonNode root = objectMapper.readTree(PRODUCT_IN_JSON);
        // drzewo JSON jest zamienione na PD
        // string Price jest zamieniony na klase Price
        ProductDefinition productDefinition = objectMapper.treeToValue(root, ProductDefinition.class);

        JsonNode jsonNode = objectMapper.valueToTree(root);

        log.info("{}", jsonNode.toPrettyString());
    }
}