package org.example.product;

import org.assertj.core.api.Assertions;
import org.example.currency_exchange_money.Currency;
import org.example.currency_exchange_money.Money;
import org.example.product.components.DateForProduct;
import org.example.product.components.Price;
import org.example.product.components.ProductName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

class ProductManagerImplTest {
    @Test
    void checkingIfAddProductMethodWorksProperly() {
        ProductRepositoryImpl productInMemoryRepository = new ProductRepositoryImpl();
        ProductManagerImpl productManager = new ProductManagerImpl(productInMemoryRepository);

        productManager.addProduct(new ProductDefinition(new ProductName("Butter"), new Price(Money.of(BigDecimal.valueOf(2.50), Currency.PLN)), new DateForProduct(LocalDate.now())));

        Assertions.assertThat(productInMemoryRepository.exists("butter")).isTrue();
    }

    @Test
    void checkingIfWhenAddingTwoProductsTwoProductsAreInRepository() {
        ProductRepositoryImpl productInMemoryRepository = new ProductRepositoryImpl();
        ProductManagerImpl productManager = new ProductManagerImpl(productInMemoryRepository);

        productManager.addProduct(new ProductDefinition(new ProductName("Butter"), new Price(Money.of(BigDecimal.valueOf(2.50), Currency.PLN)), new DateForProduct(LocalDate.now())));
        productManager.addProduct(new ProductDefinition(new ProductName("Milk"), new Price(Money.of(BigDecimal.valueOf(2.50), Currency.PLN)), new DateForProduct(LocalDate.now())));

        Assertions.assertThat(productInMemoryRepository.exists("butter")).isTrue();
        Assertions.assertThat(productInMemoryRepository.exists("milk")).isTrue();
    }

    @Test
    void checkingIfRemoveProductMethodWorksProperly() {
        ProductRepositoryImpl productInMemoryRepository = new ProductRepositoryImpl();
        ProductManagerImpl productManager = new ProductManagerImpl(productInMemoryRepository);
        productManager.addProduct(new ProductDefinition(new ProductName("Butter"), new Price(Money.of(BigDecimal.valueOf(2.50), Currency.PLN)), new DateForProduct(LocalDate.now())));
        productManager.addProduct(new ProductDefinition(new ProductName("Milk"), new Price(Money.of(BigDecimal.valueOf(2.50), Currency.PLN)), new DateForProduct(LocalDate.now())));

        productManager.removeProduct("MILK");

        Assertions.assertThat(productInMemoryRepository.exists("butter")).isTrue();
        Assertions.assertThat(productInMemoryRepository.exists("milk")).isFalse();
    }

    @Test
    void checkingIfWhenRemovingProductThatIsNotInRepositoryExceptionWillBeThrown() {
        ProductRepositoryImpl productInMemoryRepository = new ProductRepositoryImpl();
        ProductManagerImpl productManager = new ProductManagerImpl(productInMemoryRepository);

        Assertions.assertThatThrownBy(() -> productManager.removeProduct("MILK")).hasMessage("You cannot remove product that does not exist");

    }

    @Test
    void checkingIfEditMethodWorksProperlyWitchChangingPrice() {
        ProductRepositoryImpl productInMemoryRepository = new ProductRepositoryImpl();
        ProductManagerImpl productManager = new ProductManagerImpl(productInMemoryRepository);
        productManager.addProduct(new ProductDefinition(new ProductName("Butter"), new Price(Money.of(BigDecimal.valueOf(2.50), Currency.PLN)), new DateForProduct(LocalDate.now())));

        productManager.editProduct("Butter", new ProductDefinition(new ProductName("Butter"), new Price(Money.of(BigDecimal.valueOf(2.60), Currency.PLN)), new DateForProduct(LocalDate.now())));

        Assertions.assertThat(productInMemoryRepository.get("butter").getPrice().getAmount()).isEqualByComparingTo(BigDecimal.valueOf(2.60));
    }

    @Test
    void checkingIfEditMethodWorksProperlyWithChangingName() {
        ProductRepositoryImpl productInMemoryRepository = new ProductRepositoryImpl();
        ProductManagerImpl productManager = new ProductManagerImpl(productInMemoryRepository);
        productManager.addProduct(new ProductDefinition(new ProductName("Butter"), new Price(Money.of(BigDecimal.valueOf(2.50), Currency.PLN)), new DateForProduct(LocalDate.now())));

        productManager.editProduct("Butter", new ProductDefinition(new ProductName("Butter"), new Price(Money.of(BigDecimal.valueOf(2.60), Currency.PLN)), new DateForProduct(LocalDate.now())));

        Assertions.assertThat(productInMemoryRepository.get("butter").getPrice().getAmount()).isEqualByComparingTo(BigDecimal.valueOf(2.60));
    }
}