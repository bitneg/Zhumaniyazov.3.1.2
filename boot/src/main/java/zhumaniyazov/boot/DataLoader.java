package zhumaniyazov.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import zhumaniyazov.boot.model.Role;
import zhumaniyazov.boot.model.User;
import zhumaniyazov.boot.service.RoleService;
import zhumaniyazov.boot.service.UserService;

import java.util.*;

@Component
public class DataLoader implements CommandLineRunner {



    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {


            Role userRole = roleService.findByName("ROLE_USER");
            if (userRole == null) {
                userRole = new Role("ROLE_USER");
                roleService.save(userRole);
            }


    
            List<Role> roles = new ArrayList<>();
            roles.add(userRole);

            }
        }
