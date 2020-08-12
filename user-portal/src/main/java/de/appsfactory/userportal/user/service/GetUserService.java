package de.appsfactory.userportal.user.service;

import de.appsfactory.userportal.user.User;
import de.appsfactory.userportal.user.port.in.GetUserQuery;
import de.appsfactory.userportal.user.port.out.LoadUserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
class GetUserService implements GetUserQuery {

    private final LoadUserPort loadUserPort;

    @Override
    public User getUser(User.UserId userId) {
        return loadUserPort.loadUser(userId);
    }

    @Override
    public List<User> getUsers() {
        return loadUserPort.getUsers();
    }
}
