package org.example.account;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class UserLogin {
    private final String userLogin;

    public UserLogin(String userLogin){
        this.userLogin = userLogin.replaceAll("\\s+", "");
    }
}
