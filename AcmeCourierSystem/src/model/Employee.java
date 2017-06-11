package model;

import java.io.Serializable;
import java.util.Random;
import javax.persistence.*;
import com.sun.istack.internal.NotNull;

@Entity
@Table(name="Employees")
public class Employee implements Serializable {

	private static final long serialVersionUID = 1L;

	@Transient
	private static Random rnd = new Random();;

	@Id
	@Column(name="ID")
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	public long id;

	@NotNull
	public String name;

	@Enumerated(EnumType.STRING)
	public EmployeeRole role;

	@NotNull
	@Column(unique = true)
	public String userName;

	@NotNull
	public String password;

	@NotNull
	private boolean isArchived = false;

	public Employee(String name, EmployeeRole role, String userName, String password) {
		this.name = name;
		this.role = role;
		this.userName = userName;
		this.password = password;
	}

	public Employee() {
		this.name = "";
		this.role = EmployeeRole.OrderTaker;
		this.userName = "";
		this.password = "password";
	}

	public Employee(String userName) {
		this.name = "Administrator";
		this.role = EmployeeRole.Administrator;
		this.userName = "Admin";
		this.password = "password";
	}

	public void ArchiveEmployee() {
		isArchived = true;
	}

	public boolean getIsArchived() {
		return isArchived;
	}

}