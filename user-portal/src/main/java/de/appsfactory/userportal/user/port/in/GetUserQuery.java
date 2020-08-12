package de.appsfactory.userportal.user.port.in;

import de.appsfactory.userportal.user.User;

import java.util.List;

public interface GetUserQuery {

    User getUser(User.UserId userId);

    List<User> getUsers();

}
