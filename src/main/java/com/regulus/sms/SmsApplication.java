package com.regulus.sms;

import com.regulus.sms.models.entities.Role;
import com.regulus.sms.models.entities.User;
import com.regulus.sms.repositories.RoleRepository;
import com.regulus.sms.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SmsApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public static void main(String[] args) {
        SpringApplication.run(SmsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Role role = new Role();
        role.setRoleName("ADMIN");
        Role role1 = new Role();
        role1.setRoleName("USER");
        roleRepository.save(role);
        roleRepository.save(role1);
        User admin = userRepository.findByRoles(role);
        if (admin == null){
            User user = new User();
            user.setEmail("admin@gmail.com");
            user.setFirstName("regulus");
            user.setLastName("alou");
            user.getRoles().add(role);
            user.setPassword(new BCryptPasswordEncoder().encode("admin"));
            userRepository.save(user);
        }
    }
}
