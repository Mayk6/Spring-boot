package my.homework.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_READER, ROLE_ADMIN, ROLE_USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
