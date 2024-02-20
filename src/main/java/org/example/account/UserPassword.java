package org.example.account;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class UserPassword {
    private final String userPassword;

    public UserPassword(String userPassword){
        this.userPassword = userPassword;
    }
}
