package de.appsfactory.userportal.user.port.out;


import de.appsfactory.userportal.user.User;

import java.util.List;

public interface LoadUserPort {

    User loadUser(User.UserId userId);

    List<User> getUsers();
}
