package org.example.user_interfaces.web_interface.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.cart_components.Cart;
import org.example.cart_components.manager.CartManager;
import org.example.product.ProductDefinition;
import org.example.user_interfaces.web_interface.services.ProductManagerService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/customer")
@RequiredArgsConstructor
@Slf4j
public class CustomerController {
    private final Cart cart;
    private final CartManager cartManager;
    private final ProductManagerService productManagerService;

    @PostMapping("/{id}")
    public ResponseEntity<?> addToCart(@PathVariable("id") String input, @RequestBody ProductDefinition productDefinition, @RequestHeader HttpHeaders headers) {
        cart.addItem(productManagerService.getProductById(input).orElseThrow(), 1);
        log.info("{} {} {}", input, productDefinition, headers);
        return new ResponseEntity<>(productDefinition, HttpStatus.ACCEPTED);
    }

    @PostMapping("/add/{id}")
    public void addProductToCart(@PathVariable("productId") String productId, @PathVariable("cartId") String cartId) {
        cart.addItem(productManagerService.getProductById(productId).orElseThrow(), 1);
    }

    public List<ProductDefinition> getProducts() {
        List<ProductDefinition> productDefinitions = new ArrayList<>();
        for (Map.Entry<ProductDefinition, Integer> entry : cart.getProducts().entrySet()) {
            productDefinitions.add(entry.getKey());
        }
        return productDefinitions;
    }
}
