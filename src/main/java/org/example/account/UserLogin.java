package org.example.account;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

@ToString
@EqualsAndHashCode
public class UserLogin {
    private final String userLogin;

    public UserLogin(String userLogin) {
        String login = StringUtils.deleteWhitespace(userLogin);
        this.userLogin = login;
    }
}
