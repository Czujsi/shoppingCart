package org.example.user_interfaces.web_interface.controllers;

import lombok.RequiredArgsConstructor;
import org.example.currency_exchange_money.Money;
import org.example.product.ProductDefinition;
import org.example.product.components.Name;
import org.example.product.components.Price;
import org.example.product.components.ProductId;
import org.example.product.manager.ProductManager;
import org.example.user_interfaces.web_interface.services.ProductManagerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/products")
@RequiredArgsConstructor
public class ProductManagerController {

    private final ProductManagerService productManagerService;
    private final ProductManager productManager;


    @PostMapping(path = "/create")
    public ResponseEntity<?> createProduct(@RequestBody ProductDefinition newProductDefinition) {
        ProductDefinition productDefinition = new ProductDefinition(
                new Name(newProductDefinition.getName().getValue())
                , new Price(Money.of(newProductDefinition.getPrice().getAmount(), newProductDefinition.getPrice().getCurrency()))
                , LocalDate.now());
        productManager.createProduct(productDefinition);

        return new ResponseEntity<>(productDefinition, HttpStatus.CREATED);
    }

    @PostMapping(path = "/delete/{id}")
    public ResponseEntity<?> removeProduct(@PathVariable("id") String id) {
        productManager.removeProduct(new ProductId(id));

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(path = "/update/name/{id}")
    public ResponseEntity<Optional<ProductDefinition>> updateProductName(@PathVariable String id, @RequestBody String newName) {
        productManager.updateProductName(new ProductId(id), newName);

        Optional<ProductDefinition> changedProduct = productManager.getProductById(new ProductId(id));
        return new ResponseEntity<>(changedProduct, HttpStatus.ACCEPTED);
    }

    @PostMapping(path = "/update/price/{id}")
    public ResponseEntity<Optional<ProductDefinition>> updateProductPrice(@PathVariable String id, @RequestBody Money money) {
        productManager.updateProductPrice(new ProductId(id), money);

        Optional<ProductDefinition> changedProduct = productManager.getProductById(new ProductId(id));

        return new ResponseEntity<>(changedProduct, HttpStatus.ACCEPTED);
    }

    public boolean exist(ProductId productId) {
        return productManager.exist(productId);
    }

    public Optional<ProductDefinition> getProductById(ProductId productId) {
        return productManager.getProductById(productId);
    }

    @GetMapping("/list")
    public List<ProductDefinition> getAllProducts() {
        return productManagerService.getAllProducts();
    }

    public Collection<ProductDefinition> refreshStock() {
        throw new RuntimeException("Unimplemented Method");
    }

    @GetMapping("/{productId}")
    Optional<ProductDefinition> getProduct(@PathVariable String productId) {
        return productManagerService.getProductById(productId);
    }


}
