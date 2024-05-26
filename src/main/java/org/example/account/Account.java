package org.example.account;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@RequiredArgsConstructor
public class Account {
    private final UserLogin userLogin;
    private final UserPassword userPassword;
    @Getter
    private final UserName userName;
    @Getter
    @EqualsAndHashCode.Include
    private final UserId userId = UserId.createId();
    public static Account of(String userLogin, String userPassword, String userName) {
        return new Account(new UserLogin(userLogin), new UserPassword(userPassword), new UserName(userName));
    }
}
