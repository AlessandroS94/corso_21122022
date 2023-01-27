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

		if (userRepository.findByEmail("abcabc" +
				"abc91@gmail.com") == null) {

			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

			Role roleAdmin = new Role();
			User userAdmin = new User();

			roleAdmin.setRole("ADMIN");
			Set<Role> roleListUser1Test = new HashSet<Role>();
			roleListUser1Test.add(roleAdmin);

			userAdmin.setActive(true);
			userAdmin.setEmail("abcabc" +
					"abc91@gmail.com");
			userAdmin.setName("abc");
			userAdmin.setPassword(passwordEncoder.encode("abc"));
			userAdmin.setSurname("abc" +
					"abc");
			userAdmin.setRoles(roleListUser1Test);
			userAdmin.setUserName("abcabc" +
					"abc91@gmail.com");

			try {

				roleRepository.save(roleAdmin);
				userRepository.save(userAdmin);

			} catch (DataAccessException e) {
				e.printStackTrace();
				System.out.println("\n\n l'utente abcabc" +
						"abc91@gmail.com non è stato creato!");
			}

		}

		if (userRepository.findByEmail("ale_1994@gmail.com") == null) {

			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

			Role roleClient = new Role();
			User userClient = new User();

			roleClient.setRole("CLIENT");
			Set<Role> roleListUser2Test = new HashSet<Role>();
			roleListUser2Test.add(roleClient);

			userClient.setActive(true);
			userClient.setEmail("ale_1994@gmail.com");
			userClient.setFiscalCode("RMNLXA91A14E058I");
			userClient.setName("alessandro");
			userClient.setPassword(passwordEncoder.encode("abc"));
			userClient.setSurname("sallese");
			userClient.setAddressShipment("via ciao ciao");
			userClient.setRoles(roleListUser2Test);
			userClient.setUserName("ale_1994@gmail.com");
			
			try {

			roleRepository.save(roleClient);
			userRepository.save(userClient);
			
			} catch (DataAccessException e) {
				e.printStackTrace();
				System.out.println("\n\n l'utente ale_1994@gmail.com non è stato creato!");
			}

		}

		if (userRepository.findByEmail("a@gg.eu") == null) {

			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

			Role roleDesigner = new Role();
			User userDesigner = new User();

			roleDesigner.setRole("DESIGNER");
			Set<Role> roleListUser3Test = new HashSet<Role>();
			roleListUser3Test.add(roleDesigner);

			userDesigner.setActive(true);
			userDesigner.setEmail("a@gg.eu");
			userDesigner.setName("abc");
			userDesigner.setPassword(passwordEncoder.encode("abc"));
			userDesigner.setSurname("e");
			userDesigner.setRoles(roleListUser3Test);
			userDesigner.setUserName("a.@gg.eu");
			userDesigner.setFiscalCode("RMNLXA91A14E058I");
			userDesigner.setPIva("12345678");
			userDesigner.setCompanyName("Diemme S.R.L.");
			
			try {

			roleRepository.save(roleDesigner);
			userRepository.save(userDesigner);
			
			} catch (DataAccessException e) {
				e.printStackTrace();
				System.out.println("\n\n l'utente a.abc" +
						"abc@der.eu non è stato creato!");
			}

		}

		if (userRepository.findByEmail("abc@abc.it") == null) {

			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

			Role roleProductor = new Role();
			User userProductor = new User();

			roleProductor.setRole("PRODUCTOR");
			Set<Role> roleListUser4Test = new HashSet<Role>();
			roleListUser4Test.add(roleProductor);

			userProductor.setActive(true);
			userProductor.setEmail("abc@abc.it");
			userProductor.setName("abc");
			userProductor.setPassword(passwordEncoder.encode("abc"));
			userProductor.setSurname("abc" +
					"abc");
			userProductor.setRoles(roleListUser4Test);
			userProductor.setUserName("abc@abc.it");
			userProductor.setFiscalCode("RMNLXA91A14E058I");
			userProductor.setPIva("12345678");
			userProductor.setCompanyName("Maglietta Factory S.R.C.");
			
			try {

			roleRepository.save(roleProductor);
			userRepository.save(userProductor);
			
			} catch (DataAccessException e) {
				e.printStackTrace();
				System.out.println("\n\n l'utente abc@abc.it non è stato creato!");
			}

		}

	}

}
