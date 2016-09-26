package com.leo.test.list.war.model;

import java.util.ArrayList;
import java.util.List;

public enum Role {
    ROLE_USER, ROLE_ADMIN, ROLE_SUMERADMIN;

    public static List<String> hierarchy() {
        List<String> hierarchy = new ArrayList<>();
        hierarchy.add(ROLE_SUMERADMIN + " > " + ROLE_ADMIN);
        hierarchy.add(ROLE_ADMIN + " > " + ROLE_USER);
        return hierarchy;
    }
}
