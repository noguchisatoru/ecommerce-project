package com.example.ecommerce.config;

import com.example.ecommerce.model.Category;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.Role;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.CategoryRepository;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.repository.RoleRepository;
import com.example.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // カテゴリが存在するか確認
        Optional<Category> electronics = categoryRepository.findById(1L);
        Optional<Category> clothing = categoryRepository.findById(2L);
        Optional<Category> books = categoryRepository.findById(3L);
        
        // 商品の追加
        if (electronics.isPresent() && productRepository.count() == 0) {
            Product smartphone = new Product();
            smartphone.setName("Smartphone");
            smartphone.setDescription("Latest model with advanced features");
            smartphone.setPrice(new BigDecimal("699.99"));
            smartphone.setStock(50);
            smartphone.setStockQuantity(50);
            smartphone.setImageUrl("https://via.placeholder.com/300x200");
            smartphone.setCategory(electronics.get());
            productRepository.save(smartphone);
            
            if (clothing.isPresent()) {
                Product tshirt = new Product();
                tshirt.setName("T-shirt");
                tshirt.setDescription("Comfortable cotton t-shirt");
                tshirt.setPrice(new BigDecimal("19.99"));
                tshirt.setStock(100);
                tshirt.setStockQuantity(100);
                tshirt.setImageUrl("https://via.placeholder.com/300x200");
                tshirt.setCategory(clothing.get());
                productRepository.save(tshirt);
            }
            
            if (books.isPresent()) {
                Product novel = new Product();
                novel.setName("Novel");
                novel.setDescription("Bestselling fiction novel");
                novel.setPrice(new BigDecimal("12.99"));
                novel.setStock(30);
                novel.setStockQuantity(30);
                novel.setImageUrl("https://via.placeholder.com/300x200");
                novel.setCategory(books.get());
                productRepository.save(novel);
            }
        }

        // ロールテーブルを初期化
        initRoles();
        
        // テストユーザーを作成
        createTestUsers();
    }

    @Transactional
    private void initRoles() {
        // 既存のロールを削除する代わりに、存在しない場合のみ作成する
        if (roleRepository.findFirstByName("ROLE_USER").isEmpty()) {
            Role userRole = new Role();
            userRole.setName("ROLE_USER");
            roleRepository.save(userRole);
            System.out.println("ROLE_USER created");
        } else {
            System.out.println("ROLE_USER already exists");
        }
        
        if (roleRepository.findFirstByName("ROLE_ADMIN").isEmpty()) {
            Role adminRole = new Role();
            adminRole.setName("ROLE_ADMIN");
            roleRepository.save(adminRole);
            System.out.println("ROLE_ADMIN created");
        } else {
            System.out.println("ROLE_ADMIN already exists");
        }
    }
    
    @Transactional
    private void createTestUsers() {
        // テスト用の一般ユーザー
        if (!userRepository.existsByUsername("user")) {
            User user = new User();
            user.setUsername("user");
            user.setEmail("user@example.com");
            user.setPassword(passwordEncoder.encode("password"));
            
            Role userRole = roleRepository.findFirstByName("ROLE_USER").orElseThrow();
            Set<Role> roles = new HashSet<>();
            roles.add(userRole);
            user.setRoles(roles);
            
            userRepository.save(user);
            System.out.println("Test user created: " + user.getUsername());
        } else {
            System.out.println("Test user already exists");
        }
        
        // テスト用の管理者ユーザー
        if (!userRepository.existsByUsername("admin")) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setEmail("admin@example.com");
            admin.setPassword(passwordEncoder.encode("admin"));
            
            Role userRole = roleRepository.findFirstByName("ROLE_USER").orElseThrow();
            Role adminRole = roleRepository.findFirstByName("ROLE_ADMIN").orElseThrow();
            Set<Role> roles = new HashSet<>();
            roles.add(userRole);
            roles.add(adminRole);
            admin.setRoles(roles);
            
            userRepository.save(admin);
            System.out.println("Test admin created: " + admin.getUsername());
        } else {
            System.out.println("Test admin already exists");
        }
    }
} 