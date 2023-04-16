package service;

import model.Role;
import model.User;
import utils.FileUtils;
import utils.TypeSort;
import java.util.ArrayList;
import java.util.List;

public class UserService implements IUserService {
    public final String pathUser = "D:/lucduong1720/Perfume-Shop/perfume-shop/data/users.csv";
    private static UserService instance;

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    @Override
    public List<User> getAllUsers() {
        return FileUtils.readData(pathUser, User.class);
    }

    @Override
    public User login(String userName, String passWord, Role role) {
        List<User> users = getAllUsers();
        for (User user : users) {
            if (user.getNameAccount().equals(userName)
                    && user.getPassword().equals(passWord)
                    && user.getRole() == role) {
                return user;
            }
        }
        return null;
    }

    @Override
    public boolean existsByUserName(String nameAccount) {
        List<User> users = getAllUsers();
        for (User user : users) {
            if (user.getNameAccount().equals(nameAccount))
                return true;
        }
        return false;
    }

    @Override
    public boolean existsByPhone(String phone) {
        List<User> users = getAllUsers();
        for (User user : users) {
            if (user.getPhone().equals(phone))
                return true;
        }
        return false;
    }

    @Override
    public void add(User newUser) {
        List<User> users = getAllUsers();
        users.add(newUser);
        FileUtils.writeDataToFile(pathUser, users);
    }

    @Override
    public boolean existById(long id) {
        return findById(id) != null;
    }

    @Override
    public User findById(long id) {
        List<User> users = getAllUsers();
        for (User user : users) {
            if (user.getId() == id)
                return user;
        }
        return null;
    }

    @Override
    public void update(User newUser) {
        List<User> users = getAllUsers();
        for (User user : users) {
            if (user.getId() == newUser.getId()) {
                String password = newUser.getPassword();
                String nameUser = newUser.getNameUser();
                String phone = newUser.getPhone();
                String address = newUser.getAddress();
                Role role = newUser.getRole();
                user.setPassword(password);
                user.setNameUser(nameUser);
                user.setPhone(phone);
                user.setAddress(address);
                user.setRole(role);
                FileUtils.writeDataToFile(pathUser, users);
                break;
            }
        }
    }

    @Override
    public void deleteById(long id) {
        List<User> users = getAllUsers();
        for (int i = 0; i < users.size(); i++) {
            if ((users.get(i)).getId() == id) {
                users.remove(users.get(i));
            }
        }
        FileUtils.writeDataToFile(pathUser, users);
    }

    @Override
    public List<User> findByFullName(String value) {
        List<User> users = getAllUsers();
        List<User> usersFind = new ArrayList<>();
        for (User item : users) {
            if ((item.getNameUser().toUpperCase()).contains(value.toUpperCase())) {
                usersFind.add(item);
            }
        }
        if (usersFind.isEmpty()) {
            return null;
        }
        return usersFind;
    }

    @Override
    public List<User> findByAddress(String value) {
        List<User> users = getAllUsers();
        List<User> usersFind = new ArrayList<>();
        for (User item : users) {
            if ((item.getAddress().toUpperCase()).contains(value.toUpperCase())) {
                usersFind.add(item);
            }
        }
        if (usersFind.isEmpty()) {
            return null;
        }
        return usersFind;
    }

    @Override
    public List<User> sortById(TypeSort typeSort) {
        List<User> users = getAllUsers();
        if (typeSort == TypeSort.ASC) {
            users.sort((o1, o2) -> {
                double result = o1.getId() - o2.getId();
                if (result == 0) return 0;
                return result > 0 ? 1 : -1;
            });
        }
        if (typeSort == TypeSort.DESC) {
            users.sort((o1, o2) -> {
                double result = o1.getId() - o2.getId();
                if (result == 0) return 0;
                return result > 0 ? -1 : 1;
            });
        }
        return users;
    }

    @Override
    public List<User> sortByNameUser(TypeSort typeSort) {
        List<User> users = getAllUsers();
        if (typeSort == TypeSort.ASC) {
            users.sort((o1, o2) -> {
                int result = o1.getNameUser().compareTo(o2.getNameUser());
                if (result == 0) return 0;
                return result > 0 ? 1 : -1;
            });
        }
        if (typeSort == TypeSort.DESC) {
            users.sort((o1, o2) -> {
                int result = o1.getNameUser().compareTo(o2.getNameUser());
                if (result == 0) return 0;
                return result > 0 ? -1 : 1;
            });
        }
        return users;
    }

    @Override
    public List<User> sortByAddress(TypeSort typeSort) {
        List<User> users = getAllUsers();
        if (typeSort == TypeSort.ASC) {
            users.sort((o1, o2) -> {
                int result = o1.getAddress().compareTo(o2.getAddress());
                if (result == 0) return 0;
                return result > 0 ? 1 : -1;
            });
        }
        if (typeSort == TypeSort.DESC) {
            users.sort((o1, o2) -> {
                int result = o1.getAddress().compareTo(o2.getAddress());
                if (result == 0) return 0;
                return result > 0 ? -1 : 1;
            });
        }
        return users;
    }
}