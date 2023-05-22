package com.example.restdemo.entity;

public enum Role {
    SUPER_ADMIN,
    ADMIN,
    TEACHER,
    STUDENT;

    public static Role getRoleByOrdinal(int id) {
        Role[] roles = Role.values();

        if (id >= 0 && id < roles.length)
            return roles[id];

        throw new IllegalArgumentException("Invalid ordinal value: " + id);
    }
}
