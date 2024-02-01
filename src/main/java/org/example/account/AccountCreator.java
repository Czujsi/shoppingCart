package org.example.account;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class AccountCreator {
    private final UserLogin userLogin;
    private final UserPassword userPassword;
    private final UserName userName;
    @Getter
    @EqualsAndHashCode.Include
    private final UserId userId = UserId.createId();

    public AccountCreator(UserLogin login, UserPassword userPassword, UserName userName){
        this.userLogin = login;
        this.userPassword = userPassword;
        this.userName = userName;
    }

    public static AccountCreator of(String userLogin, String userPassword, String userName) {
        return new AccountCreator(new UserLogin(userLogin), new UserPassword(userPassword), new UserName(userName));
    }
}
