package org.example.product;

import org.example.currency_exchange_money.Money;
import org.example.product.components.CreationDate;
import org.example.product.components.ProductId;
import org.example.product.components.Price;
import org.example.product.components.Name;
import org.example.product.manager.ProductManager;
import org.example.product.manager.ProductManagerImpl;
import org.example.product.repository.ProductRepository;
import org.example.product.repository.ProductRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.ThrowableAssert.*;
import static org.example.currency_exchange_money.Currency.*;

class ProductManagerImplTest {

    public static final ProductDefinition EXAMPLE_PRODUCT_BUTTER = new ProductDefinition(new Name("Butter"), new Price(Money.of(BigDecimal.valueOf(2.50), PLN)), new CreationDate(LocalDate.now()));
    public static final ProductDefinition EXAMPLE_PRODUCT_MILK = new ProductDefinition(new Name("Milk"), new Price(Money.of(BigDecimal.valueOf(2.50), PLN)), new CreationDate(LocalDate.now()));
    public static final ProductId NON_EXISTING_ID = new ProductId("d42e160b-0f74-4226-a45d-f0c348042a11");

    ProductManager productManager;
    ProductRepository<ProductId, ProductDefinition> productRepository;

    @BeforeEach
    void setUp(){
        productRepository = new ProductRepositoryImpl();
        productManager = new ProductManagerImpl(productRepository);
    }

    @Test
    void checkingIfAddProductMethodWorksProperly() {
        productManager.addProduct(EXAMPLE_PRODUCT_BUTTER);

        assertThat(productRepository.exists(EXAMPLE_PRODUCT_BUTTER.getProductId())).isTrue();
    }

    @Test
    void checkingIfWhenAddingTwoProductsTwoProductsAreInRepository() {
        productManager.addProduct(EXAMPLE_PRODUCT_BUTTER);
        productManager.addProduct(EXAMPLE_PRODUCT_MILK);

        assertThat(productRepository.exists(EXAMPLE_PRODUCT_BUTTER.getProductId())).isTrue();
        assertThat(productRepository.exists(EXAMPLE_PRODUCT_MILK.getProductId())).isTrue();
    }

    @Test
    void checkingIfRemoveProductMethodWorksProperly() {
        productManager.addProduct(EXAMPLE_PRODUCT_BUTTER);
        productManager.addProduct(EXAMPLE_PRODUCT_MILK);

        productManager.removeProduct(EXAMPLE_PRODUCT_MILK.getProductId());

        assertThat(productRepository.exists(EXAMPLE_PRODUCT_BUTTER.getProductId())).isTrue();
        assertThat(productRepository.exists(EXAMPLE_PRODUCT_MILK.getProductId())).isFalse();
    }

    @Test
    void checkingIfWhenRemovingProductThatIsNotInRepositoryExceptionWillBeThrown() {

        ThrowingCallable action = () -> productManager.removeProduct(NON_EXISTING_ID);

        assertThatThrownBy(action).hasMessage("You cannot remove product that does not exist");
    }

    @Test
    void checkingIfEditMethodWorksProperlyWithChangingPrice() {
        productManager.addProduct(EXAMPLE_PRODUCT_BUTTER);
        Money newPrice = Money.of((EXAMPLE_PRODUCT_BUTTER.getPrice().getAmount().add(BigDecimal.ONE)), PLN);

        productManager.updateProductPrice(EXAMPLE_PRODUCT_BUTTER.getProductId(), newPrice);

        assertThat(productManager.getProductById(EXAMPLE_PRODUCT_BUTTER.getProductId()))
                .hasValueSatisfying(p -> assertThat(p.getPrice()).isEqualTo(newPrice));
    }

    @Test
    void checkingIfEditMethodWorksProperlyWithChangingName() {
        productManager.addProduct(EXAMPLE_PRODUCT_BUTTER);
        String newName = "Better";

        productManager.updateProductName(EXAMPLE_PRODUCT_BUTTER.getProductId(), newName);

        assertThat(productRepository.findById(EXAMPLE_PRODUCT_BUTTER.getProductId()))
                .hasValueSatisfying(p -> assertThat(p.getName()).isEqualTo(new Name(newName)));
    }
}