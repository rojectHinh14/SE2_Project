package com.example.SE2_Project.Security;

import org.apache.coyote.Constants;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class SecurityUtils {

    public static String getCurrentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // kiem tra xem da dang nhap chua
        if (authentication != null && authentication.isAuthenticated()) {
            // neu dang nhap roi => return username
            return authentication.getName();
        }
        return "";
    }

//    public static void printCurrentUserInfo() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails) {
//            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
//            System.out.println("Username: " + userDetails.getUsername());
//            System.out.println("Full name: " + userDetails.getUserEntity().getName()); // Lấy tên đầy đủ
//            System.out.println("Authorities: " + authentication.getAuthorities());
//        }
//    }

    public static final String PREFIX_ROLE = "ROLE_";
    public static enum Role {
        ADMIN(1),
        USER(2);

        private final int roleId;

        Role(int roleId) {
            this.roleId = roleId;
        }

        public int getRoleId() {
            return roleId;
        }
    }
    public static Set<String> getRolesCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Set<String> roleCode = new HashSet<>();
        if (authentication != null) {
            Collection<? extends GrantedAuthority> roles = authentication.getAuthorities();
            roleCode = roles.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
        }
        return roleCode;
    }
}
