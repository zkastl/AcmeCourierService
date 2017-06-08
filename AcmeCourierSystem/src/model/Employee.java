package model;

import java.io.Serializable;

import javax.persistence.*;
import com.sun.istack.internal.NotNull;

@Entity
@Table(name="employees")
public class Employee implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue (strategy = GenerationType.IDENTITY)
	public long id;
	
	@NotNull
	public String name;
	
	@Enumerated(EnumType.STRING)
	public EmployeeRole role;
	
	@NotNull
	public String userName;
	
	@NotNull
	public String password;

	public Employee(String name, EmployeeRole role, String userName, String password) {
		this.name = name;
		this.role = role;
		this.userName = userName;
		this.password = password;
	}

	/*public Employee() {
		this.name = "Administrator";
		this.role = EmployeeRole.Administrator;
		this.userName = "Admin";
		this.password = "password";
	}*/
	
	public Employee() {}
	
	
}