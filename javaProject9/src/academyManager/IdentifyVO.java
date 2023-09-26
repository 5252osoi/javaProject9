package academyManager;

public class IdentifyVO {
	private String id;
	private String password;
	
	String getId() {
		return id;
	}
	void setId(String id) {
		this.id = id;
	}
	String getPassword() {
		return password;
	}
	void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "IdentifyVO [id=" + id + ", password=" + password + "]";
	}
	
}
