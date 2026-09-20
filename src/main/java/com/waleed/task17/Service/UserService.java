package com.waleed.task17.Service;

import com.waleed.task17.Entity.User;
import com.waleed.task17.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public int addUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            return 1;
        }
        userRepository.save(user);
        return 0;
    }

    public int updateUser(Long id, User updatedUser) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return 1;
        }
        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());
        user.setPassword(updatedUser.getPassword());
        user.setAge(updatedUser.getAge());
        user.setRole(updatedUser.getRole());
        userRepository.save(user);
        return 0;
    }

    public boolean deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            return false;
        }
        userRepository.deleteById(id);
        return true;
    }
}