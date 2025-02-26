package com.example.ecommerce.service;

import com.example.ecommerce.model.Role;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.RoleRepository;
import com.example.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(String username, String email, String password) {
        // ユーザー名とメールアドレスの重複チェック
        if (userRepository.existsByUsername(username)) {
            throw new RuntimeException("Username is already taken");
        }

        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("Email is already in use");
        }

        // パスワードのハッシュ化
        String encodedPassword = passwordEncoder.encode(password);

        // 新しいユーザーの作成
        User user = new User(username, email, encodedPassword);

        // デフォルトロールの設定（ROLE_USER）
        Optional<Role> userRole = roleRepository.findFirstByName("ROLE_USER");
        Set<Role> roles = new HashSet<>();
        
        userRole.ifPresent(roles::add);
        user.setRoles(roles);

        // ユーザーの保存
        return userRepository.save(user);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
