package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserUtil {
    public static final List<User> USERS = Arrays.asList(
            new User(0, "RootUser", "admin@dubrovin.org", "123456", Role.ROLE_ADMIN),
            new User(1, "FirstUser", "FirstUser@dubrovin.org", "12236", Role.ROLE_USER, Role.ROLE_ADMIN),
            new User(2, "SecondUser", "SecondUser@dubrovin.org", "1222456", Role.ROLE_USER),
            new User(3, "ThirdUser", "ThirdUser@dubrovin.org", "123ddd456", Role.ROLE_USER),
            new User(4, "FourthUser", "ThourthUer@dubrovin.org", "122234256", Role.ROLE_USER)
    );

}
