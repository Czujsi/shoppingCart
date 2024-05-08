package org.example.account;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

@ToString
@EqualsAndHashCode
public class UserName {
    private final String userName;

    public UserName(String userName) {
        String name = StringUtils.deleteWhitespace(userName);
        this.userName = name.toLowerCase();
    }
}
