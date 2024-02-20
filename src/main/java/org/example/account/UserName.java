package org.example.account;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class UserName {
    private final String userName;

    public UserName(String userName){
        this.userName = userName.toLowerCase().replaceAll("\\s+", "");
    }
}
