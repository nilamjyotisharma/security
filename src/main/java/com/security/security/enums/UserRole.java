package com.security.security.enums;
import lombok.Getter;
import java.util.HashSet;
import java.util.Set;

@Getter
public enum UserRole {
    SUPER_ADMIN("SUPER_ADMIN"),
    PRINCIPAL("PRINCIPAL"),
    TEACHER("TEACHER"),
    STUDENT("STUDENT"),
    USER("USER");


    private final String role;
    UserRole(String role) {
        this.role = role;
    }

    public static Set<String> getUserRoleType(){
        Set<String> userRoles=new HashSet<>(0);

        for(UserRole role: UserRole.values()) {
            userRoles.add(role.role);
        }
        return userRoles;
    }
}

