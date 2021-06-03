package crud.service;

import crud.model.Role;
import crud.model.User;
import java.util.List;


public interface UserService {
    List<User> getAllUsers();
    User getUser(Long id);
    void add(User user);
    void update(User user);
    void delete (Long id);
    Role findRoleByName(String role);
    User getByName(String name);
}
