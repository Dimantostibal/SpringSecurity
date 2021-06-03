package crud.dao;

import crud.model.Role;
import crud.model.User;


import java.util.List;

public interface UserDao {
    User getByName(String name);
    List<User> getAllUsers();
    User getUser(Long id);
    void add(User user);
    void update(User user);
    void delete (Long id);
    Role findRoleByName(String role);

}
