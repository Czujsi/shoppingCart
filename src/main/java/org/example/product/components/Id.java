package org.example.product.components;

import lombok.EqualsAndHashCode;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
@EqualsAndHashCode
public class Id {
    private static final AtomicLong atomicCounter = new AtomicLong();

    public static String createId() {

        String currentCounter = String.valueOf(atomicCounter.getAndIncrement());
        String uniqueId = UUID.randomUUID().toString();

        return uniqueId + "-" + currentCounter;
    }
}
