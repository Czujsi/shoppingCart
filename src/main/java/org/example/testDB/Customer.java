package org.example.testDB;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
public class Customer {

    @EmbeddedId
    Id id;

    String name;

    @Data
    @Embeddable
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Id {
        String a;
        String b;
    }
}
