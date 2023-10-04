package com.fsd9.ecom.startup;


import com.fsd9.ecom.modules.user.model.EORole;
import com.fsd9.ecom.modules.user.repositories.EORoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DefaultInitializer implements CommandLineRunner {

    @Autowired
    private final EORoleRepository roleRepository;

    public DefaultInitializer(EORoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) {
        createDefaultRoles();
    }

    private void createDefaultRoles() {
        // Check if the default roles already exist


        if (roleRepository.findByName("ROLE_ADMIN") == null) {
            EORole role = new EORole();
            role.setId(1l);
            role.setName("ROLE_ADMIN");
            roleRepository.save(role);
        }

        if (roleRepository.findByName("ROLE_SELLER") == null) {
            EORole role = new EORole();
            role.setId(2l);
            role.setName("ROLE_SELLER");
            roleRepository.save(role);
        }

        if (roleRepository.findByName("ROLE_USER") == null) {
            EORole role = new EORole();
            role.setId(3l);
            role.setName("ROLE_USER");
            roleRepository.save(role);
        }

        if (roleRepository.findByName("ROLE_SUPPORT_USER") == null) {
            EORole role = new EORole();
            role.setId(4l);
            role.setName("ROLE_SUPPORT_USER");
            roleRepository.save(role);
        }
    }
}
