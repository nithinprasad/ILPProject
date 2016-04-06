package vendor.bean;

public class User {
	/**
	 * @author aditi
	 * @classname User
	 * @creation date 17 july,2015
	 */
private String userId;
private String password;
private String accountType;
private String status;




	public User(String userId, String password, String accountType,
		 String status) {
	super();
	this.userId = userId;
	this.password = password;
	this.accountType = accountType;
	
	this.status = status;
}
	// TODO Auto-generated constructor stub

public User() {
		// TODO Auto-generated constructor stub
	}

public User(String userName, String password2, String accountType2) {
	// TODO Auto-generated constructor stub
}

public String getUserId() {
	return userId;
}
public void setUserId(String userId) {
	this.userId = userId;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getAccountType() {
	return accountType;
}
public void setAccountType(String accountType) {
	this.accountType = accountType;
}


public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

}