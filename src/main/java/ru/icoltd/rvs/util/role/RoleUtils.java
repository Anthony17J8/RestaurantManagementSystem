package ru.icoltd.rvs.util.role;

import ru.icoltd.rvs.entity.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RoleUtils {

    private RoleUtils() {
    }

    public static List<String> getRoleNames(Set<Role> roles) {
        return roles.stream()
                .map(Role::getName)
                .sorted()
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
