package org.example.config;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.example.config.deserializers.MoneyJacksonDeserializer;
import org.example.config.deserializers.PriceJacksonDeserializer;
import org.example.config.serializers.MoneyJacksonSerializer;
import org.example.config.serializers.ProductHistoryJacksonSerializer;
import org.example.config.serializers.ProductIdJacksonSerializer;
import org.example.config.serializers.ProductNameJacksonSerializer;
import org.example.currency_exchange_money.Money;
import org.example.product.components.Name;
import org.example.product.components.Price;
import org.example.product.components.ProductId;
import org.example.product.history.ProductHistory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectMapperConfiguration {
    @Bean
    public Module productModule() {
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(ProductId.class, new ProductIdJacksonSerializer());
        simpleModule.addSerializer(Name.class, new ProductNameJacksonSerializer());
        simpleModule.addSerializer(Money.class, new MoneyJacksonSerializer());
        simpleModule.addSerializer(ProductHistory.class, new ProductHistoryJacksonSerializer());
        return simpleModule;
    }

    @Bean
    public Module deserializerModule() {
        SimpleModule simpleModule = new SimpleModule();
//        simpleModule.addDeserializer(Price.class, new PriceJacksonDeserializerForTypeScript());
        simpleModule.addDeserializer(Price.class, new PriceJacksonDeserializer());
        simpleModule.addDeserializer(Money.class, new MoneyJacksonDeserializer());
        return simpleModule;
    }
}
