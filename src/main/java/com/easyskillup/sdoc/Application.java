package com.easyskillup.sdoc;

import com.easyskillup.sdoc.domain.RequestContext;
import com.easyskillup.sdoc.entities.Authority;
import com.easyskillup.sdoc.entities.Role;
import com.easyskillup.sdoc.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableJpaAuditing
@EnableAsync
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


//    @Bean
//    CommandLineRunner commandLineRunner(RoleRepository repository){
//        return args -> {
//            RequestContext.setUserId(0L);
//
//            var userRole = new Role();
//            userRole.setName(Authority.USER.name());
//            userRole.setAuthorities(Authority.USER);
//            repository.save(userRole);
//
//            var adminRole = new Role();
//            adminRole.setName(Authority.ADMIN.name());
//            adminRole.setAuthorities(Authority.ADMIN);
//            repository.save(adminRole);
//
//            RequestContext.start();
//        };
//    }

}
