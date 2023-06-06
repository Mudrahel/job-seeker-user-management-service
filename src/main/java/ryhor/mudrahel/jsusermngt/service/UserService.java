package ryhor.mudrahel.jsusermngt.service;

import ryhor.mudrahel.jsusermngt.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User saveUser(User user);

    User getUserById(Long id);

    User updateUser(User user);

    void deleteUser(Long id);
}
