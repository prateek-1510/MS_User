package users.services;

import users.model.User;

import java.util.List;

public interface UserDetailsService {
    public List<User> fetchUsers();
}
