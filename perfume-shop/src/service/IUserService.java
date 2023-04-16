package service;

import model.Role;
import model.User;
import utils.TypeSort;

import java.util.List;

public interface IUserService {
    List<User> getAllUsers();
    User login(String userName, String passWord, Role role);
    boolean existsByUserName(String nameAccount);
    boolean existsByPhone(String phone);
    void add(User newUser);

    boolean existById(long id);
    User findById(long id);

    void update(User newUser);

    void deleteById(long id);

    List<User> findByFullName(String value);

    List<User> findByAddress(String value);

    List<User> sortById(TypeSort typeSort);

    List<User> sortByNameUser(TypeSort typeSort);

    List<User> sortByAddress(TypeSort typeSort);
}
