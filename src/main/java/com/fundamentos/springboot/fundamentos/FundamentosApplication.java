package com.fundamentos.springboot.fundamentos;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import com.fundamentos.springboot.fundamentos.bean.MyBean;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentos.springboot.fundamentos.bean.MybeanwithProperties;
import com.fundamentos.springboot.fundamentos.component.ComponentDependecency;
import com.fundamentos.springboot.fundamentos.entity.User;
import com.fundamentos.springboot.fundamentos.pojo.UserPojo;
import com.fundamentos.springboot.fundamentos.repository.UserRepository;
import com.fundamentos.springboot.fundamentos.services.UserService;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {
	
	Log LOGGER = LogFactory.getLog(FundamentosApplication.class);
	
	private ComponentDependecency componentDependecency;
	private MyBean myBean;
	private MyBeanWithDependency myBeanWithDependency;
	private MybeanwithProperties mybeanwithProperties;
	private UserPojo userPojo;
	
	private UserRepository userRepository;
	private UserService userService;
	
	//@Autowired  
	public FundamentosApplication(@Qualifier("componentTwoImpl") ComponentDependecency componentDependecency, MyBean myBean,  MyBeanWithDependency myBeanWithDependency, MybeanwithProperties mybeanwithProperties, UserPojo userPojo, UserRepository userRepository, UserService userService) {
		this.componentDependecency = componentDependecency;
		this.myBean = myBean;
		this.myBeanWithDependency = myBeanWithDependency;
		this.mybeanwithProperties = mybeanwithProperties;
		this.userPojo = userPojo;
		this.userRepository = userRepository;
		this.userService = userService;
	}

	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//ejemploAnteriores();
		saveUsersInDataBase();
		getInformationJpqlFromuser();
		saveWithErrorTransactional();
	}
	
	private void saveWithErrorTransactional() {
		User test1 = new User("TestTransactional1", "TestTransactional1@mail.com", LocalDate.of(1998, 2, 7) );
		User test2 = new User("TestTransactional2", "TestTransactional2@mail.com", LocalDate.of(1998, 1, 1) );
		User test3 = new User("TestTransactional3", "TestTransactional1@mail.com", LocalDate.of(1998, 3, 3) );
		User test4 = new User("TestTransactional4", "TestTransactional4@mail.com", LocalDate.of(1998, 4, 5) );
		
		List<User> users = Arrays.asList(test1, test2, test3, test4);
		
		try {
			userService.saveTransactional(users);
		} catch (Exception e) {
			LOGGER.error("Esta es una exception dentro del metodo transaccional " + e.getMessage());
		}
		
		userService.getAllUsers().stream()
			.forEach( user -> LOGGER.info("Este es el usuario dentro del metodo transactional " +  user));
	}
	
	private void getInformationJpqlFromuser() {
		/*LOGGER.info("Usuario con el metodo findByUserEmail " + 
				userRepository.findByUserEmail("joel@mail.com")
				.orElseThrow(()-> new RuntimeException("No se encontro el usuario")));
		
		userRepository.findAndSort("user", Sort.by("id").ascending())
			.stream()
			.forEach(user -> LOGGER.info("Usuario con metodo sort " + user));
		
		userRepository.findByName("Joel")
			.stream()
			.forEach(user -> LOGGER.info("Usuario con query method " + user));
		
		LOGGER.info("Usuario con query method findByEmailAndName" + userRepository.findByEmailAndName("daniela@mail.com", "Daniela")
			.orElseThrow(() -> new RuntimeException("Usuario no encontrado")));
		
		userRepository.findByNameLike("%J%").stream()
			.forEach(user -> LOGGER.info("Usuario findByNameLike " + user));
		
		userRepository.findByNameOrEmail("user9", null).stream()
		.forEach(user -> LOGGER.info("Usuario findByNameOrEmail " + user));*/
		
		userRepository.findByBirthDateBetween(LocalDate.of(1998, 2, 1), LocalDate.of(1998, 4, 2))
			.stream()
			.forEach(user -> LOGGER.info("Usuario con intervalo de fechas " + user));;
		
		userRepository.findByNameContainingOrderByIdDesc("user")
			.stream()
			.forEach(user -> LOGGER.info("Usuario encontrado con like y ordernado " + user));
		
		LOGGER.info("El usuario a partir del named parameter es: " + userRepository.getAllByBirthDateAndEmail(LocalDate.of(1998, 4, 11), "daniela@mail.com")
			.orElseThrow(() -> new RuntimeException("No se encontro el usuario a partir del named parameter")) );
		
		
	}
	
	private void saveUsersInDataBase(){
		User user1 = new User("Joel", "joel@mail.com", LocalDate.of(1998, 2, 07));
		User user2 = new User("John", "john@mail.com", LocalDate.of(1998, 5, 21));
		User user3 = new User("Daniela", "daniela@mail.com", LocalDate.of(1998, 4, 11));
		User user4 = new User("user4", "user4@mail.com", LocalDate.of(1998, 5, 2));
		User user5 = new User("user5", "user5@mail.com", LocalDate.of(1998, 1, 22));
		User user6 = new User("user6", "user6@mail.com", LocalDate.of(1998, 2, 24));
		User user7 = new User("user7", "user7@mail.com", LocalDate.of(1998, 3, 10));
		User user8 = new User("user8", "user8@mail.com", LocalDate.of(1998, 8, 5));
		User user9 = new User("user9", "user9@mail.com", LocalDate.of(1998, 12, 30));
		List<User> list = Arrays.asList(user1, user2, user3, user4, user5, user6, user7, user8, user9);
		list.stream().forEach(userRepository::save);
		
		
	}
	
	
	private void ejemploAnteriores() {
		componentDependecency.saludar();
		myBean.print();
		myBeanWithDependency.printWithDependency();
		System.out.println(mybeanwithProperties.function());
		System.out.println(userPojo.getEmail() + "-" + userPojo.getPassword());
		try {
			//error
			int value = 10/0;
			LOGGER.debug("Mi valor :" + value);
		} catch (Exception e) {
			LOGGER.error("Esto es un erro al dividir por cero: " +  e.getMessage());
		}
	}

}
