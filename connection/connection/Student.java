package connection;


public class Student {
	private int id;
	private String firstname;
	private String lastname;
	private String streetaddress;
	private String postcode;
	private String postoffice;

	public Student() {
		id = -1;
		firstname ="";
		lastname = "";
		streetaddress = "";
		postcode = "";
		postoffice = "";
	}

	public Student(int id, String firstname, String lastname, String streetaddress, String postcode, String postoffice) {
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.streetaddress = streetaddress;
		this.postcode = postcode;
		this.postoffice = postoffice; 
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return firstname;
	}

	public void setName(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getStreetAddress() {
		return streetaddress;
	}

	public void setStreetAddress(String stressAddress) {
		this.streetaddress = stressAddress;
	}
	public String getPostOffice() {
		return postoffice;
	}
	
	public String getPostCode() {
		return postcode;
	}


	@Override
	public String toString() {
		return "Student: " + id + ", " + firstname + " " + lastname + ", street: " + streetaddress;
	}

	public static void add(Student student) {
		// TODO Auto-generated method stub
		
	}
}
// End
