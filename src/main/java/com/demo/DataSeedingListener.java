package com.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.demo.entity.Role;
import com.demo.entity.Status;
import com.demo.entity.Todo;
import com.demo.entity.User;
import com.demo.repository.RoleRepository;
import com.demo.repository.StatusRepository;
import com.demo.repository.TodoRepository;
import com.demo.repository.UserRepository;

@SpringBootApplication
public class DataSeedingListener {
	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(DataSeedingListener.class, args);
		applicationContext.start();
	}

	@Component
	class ContextStartedListener implements ApplicationListener<ContextStartedEvent> {
		@Autowired
		private UserRepository userRepository;

		@Autowired
		private RoleRepository roleRepository;
		@Autowired
		private StatusRepository statusRepository;
		@Autowired
		private TodoRepository todoResRepository;
      @Autowired 
      private PasswordEncoder passwordEncoder;
//      @Override
		public void onApplicationEvent(ContextStartedEvent event) {
			//Role Admin
			if (roleRepository.findByName("ROLE_ADMIN") == null) {
				roleRepository.save(new Role("ROLE_ADMIN"));
			}
			//Role Account
			if (roleRepository.findByName("ROLE_MEMBER") == null) {
				roleRepository.save(new Role("ROLE_MEMBER"));
			}
			User user1=userRepository.findByEmail("admin@gmail.com");
			// Admin account
	        if (userRepository.findByEmail("admin@gmail.com") == null) {
	            User admin = new User();
	            admin.setEmail("admin@gmail.com");
	            admin.setPassword(passwordEncoder.encode("123456"));
//	            admin.setPassword("123456");
//	            HashSet<Role> roles = new HashSet<>();
	            List<Role> roles=new ArrayList<Role>();
	            roles.add(roleRepository.findByName("ROLE_ADMIN"));
	            roles.add(roleRepository.findByName("ROLE_MEMBER"));
	            admin.setRoles(roles);
	            userRepository.save(admin);
	        }
	        // Member account
	        if (userRepository.findByEmail("member@gmail.com") == null) {
	            User user = new User();
	            user.setEmail("member@gmail.com");
	            user.setPassword(passwordEncoder.encode("123456"));
	            //user.setPassword("123456");
	            List<Role> roles = new ArrayList<Role>();
	            roles.add(roleRepository.findByName("ROLE_MEMBER"));
	            user.setRoles(roles);
	            userRepository.save(user);
	        }
	        // Status
	        statusRepository.save(new Status("Created"));
	        statusRepository.save(new Status("Finished"));
	        statusRepository.save(new Status("Cancel"));
	        // TODO
	        Date date=new Date();
	        User user =new User();
	        Status status =new Status();
	        user=userRepository.findByEmail("admin@gmail.com");
	        status=statusRepository.findBynameStatus("Created");
	        todoResRepository.save(new Todo(date,"Learn java",0,user,status));
	        user=userRepository.findByEmail("member@gmail.com");
	        status=statusRepository.findBynameStatus("Finished");
	        todoResRepository.save(new Todo(date,"Learn c#",0,user,status));
	        
		}
	}
}
//public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {
//	@Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private RoleRepository roleRepository;
//
//    @Autowired 
//    private PasswordEncoder passwordEncoder;
////    @Override
//	public void onApplicationEvent(ContextRefreshedEvent event) {
//		// TODO Auto-generated method stub
//        if (roleRepository.findByName("ROLE_ADMIN") == null) {
//            roleRepository.save(new Role("ROLE_ADMIN"));
//        }
//
//        if (roleRepository.findByName("ROLE_MEMBER") == null) {
//            roleRepository.save(new Role("ROLE_MEMBER"));
//        }
//	}
//   
//
//}
