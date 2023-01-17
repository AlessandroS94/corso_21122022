package it.corso.spring.soap.springboot.soap.web.services.example.student;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class Student {
	@Getter
	@Setter
	private Long id;
	@Getter
	@Setter
	private String name;
	@Getter
	@Setter
	private String passportNumber;

	public Student() {
		super();
	}

	public Student(Long id, String name, String passportNumber) {
		super();
		this.id = id;
		this.name = name;
		this.passportNumber = passportNumber;
	}

	public Student(String name, String passportNumber) {
		super();
		this.name = name;
		this.passportNumber = passportNumber;
	}

}
