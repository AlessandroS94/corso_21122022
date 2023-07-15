package com.diemme;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.diemme.domain.mysql.Role;
import com.diemme.domain.mysql.User;
import com.diemme.repository.mysql.RoleRepository;
import com.diemme.repository.mysql.UserRepository;

@Component
	public class CreateUsers implements CommandLineRunner {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {

		if (userRepository.findByEmail("alex_994@ymail.com") == null) {

			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

			Role roleAdmin = new Role();
			User userAdmin = new User();

			roleAdmin.setRole("ADMIN");
			Set<Role> roleListUser1Test = new HashSet<Role>();
			roleListUser1Test.add(roleAdmin);

			userAdmin.setActive(true);
			userAdmin.setEmail("alex_994@ymail.com");
			userAdmin.setName("Alessandro");
			userAdmin.setPassword(passwordEncoder.encode("abc"));
			userAdmin.setSurname("Sallese");
			userAdmin.setRoles(roleListUser1Test);
			userAdmin.setUserName("alex_994@ymail.com");

			try {

				roleRepository.save(roleAdmin);
				userRepository.save(userAdmin);

			} catch (DataAccessException e) {
				e.printStackTrace();
				System.out.println("\n\n l'utente alex_994@ymail.com" +
						"abc91@gmail.com non è stato creato!");
			}

		}

		if (userRepository.findByEmail("alessandrosallese@yahoo.com") == null) {

			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

			Role roleClient = new Role();
			User userClient = new User();

			roleClient.setRole("CLIENT");
			Set<Role> roleListUser2Test = new HashSet<Role>();
			roleListUser2Test.add(roleClient);

			userClient.setActive(true);
			userClient.setEmail("alessandrosallese@yahoo.com");
			userClient.setFiscalCode("RMNLXA91A14E058I");
			userClient.setName("Marco");
			userClient.setPassword(passwordEncoder.encode("abc"));
			userClient.setSurname("Sallese");
			userClient.setAddressShipment("via ciao ciao");
			userClient.setRoles(roleListUser2Test);
			userClient.setUserName("alessandrosallese@yahoo.com");
			
			try {

			roleRepository.save(roleClient);
			userRepository.save(userClient);
			
			} catch (DataAccessException e) {
				e.printStackTrace();
				System.out.println("\n\n l'utente alessandrosallese@yahoo.com non è stato creato!");
			}

		}

		if (userRepository.findByEmail("alessandro_sallese@yahoo.com") == null) {

			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

			Role roleDesigner = new Role();
			User userDesigner = new User();

			roleDesigner.setRole("DESIGNER");
			Set<Role> roleListUser3Test = new HashSet<Role>();
			roleListUser3Test.add(roleDesigner);

			userDesigner.setActive(true);
			userDesigner.setEmail("alessandro_sallese@yahoo.com");
			userDesigner.setName("Paolo");
			userDesigner.setPassword(passwordEncoder.encode("abc"));
			userDesigner.setSurname("Sallese");
			userDesigner.setRoles(roleListUser3Test);
			userDesigner.setUserName("alessandro_sallese@yahoo.com");
			userDesigner.setFiscalCode("RMNLXA91A14E058I");
			userDesigner.setPIva("12345678");
			userDesigner.setCompanyName("Diemme S.R.L.");
			
			try {

			roleRepository.save(roleDesigner);
			userRepository.save(userDesigner);
			
			} catch (DataAccessException e) {
				e.printStackTrace();
				System.out.println("\n\n l'utente alessandro_sallese@yahoo.com" +
						"abc@der.eu non è stato creato!");
			}

		}

		if (userRepository.findByEmail("publisher_ciclismogare@yahoo.com") == null) {

			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

			Role roleProductor = new Role();
			User userProductor = new User();

			roleProductor.setRole("PRODUCTOR");
			Set<Role> roleListUser4Test = new HashSet<Role>();
			roleListUser4Test.add(roleProductor);

			userProductor.setActive(true);
			userProductor.setEmail("publisher_ciclismogare@yahoo.com");
			userProductor.setName("publisher_ciclismogare");
			userProductor.setPassword(passwordEncoder.encode("abc"));
			userProductor.setSurname("Gare");
			userProductor.setRoles(roleListUser4Test);
			userProductor.setUserName("publisher_ciclismogare@yahoo.com");
			userProductor.setFiscalCode("RMNLXA91A14E058I");
			userProductor.setPIva("12345678");
			userProductor.setCompanyName("Maglietta Factory S.R.C.");
			
			try {

			roleRepository.save(roleProductor);
			userRepository.save(userProductor);
			
			} catch (DataAccessException e) {
				e.printStackTrace();
				System.out.println("\n\n l'utente publisher_ciclismogare non è stato creato!");
			}

		}

	}

}
