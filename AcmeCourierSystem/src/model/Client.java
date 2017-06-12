package model;

import java.io.Serializable;
import javax.persistence.*;
import com.sun.istack.internal.NotNull;

/**
 * an entity that is served by the courier company
 */
@Entity(name="Clients")
public class Client implements Serializable {
	
	/**
	 * The name of the client
	 */
	@NotNull
	public String name;
	/**
	 * The phone number of the client
	 */
	@NotNull
	public String phoneNumber;
	/**
	 * The unique ID number assigned to the client
	 */
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long clientID;
	
	/**
	 * The email address of the client
	 */
	@NotNull
	public String emailAddress;
	/**
	 * Special instructions couriers are to follow when picking up or dropping
	 * off a package.
	 */	
	public String dropoffInstructions;
	
	/**
	 * deliveries associated with the client
	 */
	
	public Delivery[] deliveryHistory;
	
	/**
	 * The intersection the client is located at where packages are picked up
	 * from and delivered to
	 */
	
	public Intersection address;
	
	@NotNull
	private boolean isArchived = false;
	
	private static final long serialVersionUID = 1L;
	
	public Client() {
		address = new Intersection("", "");
		name = "";
		phoneNumber = "";
		emailAddress = "";
		dropoffInstructions = "";
	}
	
	public Intersection getAddress() {
		return new Intersection("", "");
	}

	public void ArchiveClient() {
		isArchived = true;
	}
	
	public boolean getIsArchived() {
		return isArchived;
	}

}