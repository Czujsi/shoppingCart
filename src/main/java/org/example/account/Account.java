package org.example.account;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class Account {
    private final UserLogin userLogin;
    private final UserPassword userPassword;
    @Getter
    private final UserName userName;
    @Getter
    private final UserId userId = UserId.createId();

    public Account(UserLogin login, UserPassword userPassword, UserName userName) {
        this.userLogin = login;
        this.userPassword = userPassword;
        this.userName = userName;
    }

    public static Account of(String userLogin, String userPassword, String userName) {
        return new Account(new UserLogin(userLogin), new UserPassword(userPassword), new UserName(userName));
    }
}
