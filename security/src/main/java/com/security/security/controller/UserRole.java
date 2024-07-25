package com.security.security.controller;
import lombok.Getter;
import java.util.HashSet;
import java.util.Set;

@Getter
public enum UserRole {
    SUPER_ADMIN("super_admin"),
    ADMIN("admin"),
    USER("user");


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

