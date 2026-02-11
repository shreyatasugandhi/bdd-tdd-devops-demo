package businesslogic;

public class LoginPage {
	
	String username;
	String password;
	 
	//store username
	public void enterUserName(String username){
	this.username = username;
	}
	 
	//store password
	public void enterPassword(String password){
	this.password = password;
	}
	 
	//match username and passowrd in db and return home page
	
//	// before re-factoring
//	public HomePage submit(){
//		if(existsInDB(username)){
//			String dbPassword = getPasswordFromDB(username);
//			if(dbPassword.equals(password)) {
//				return new HomePage();
//			}
//		}
//	}

	private boolean existsInDB(String username) {
		return true;
		
	}
	private String getPasswordFromDB(String username) {
		// TODO Auto-generated method stub
		return password;
	}

	// After Re-factoring
	public HomePage submit(){
		if(existsInDB(username)){
			String dbPassword = getPasswordFromDB(username);
			if(dbPassword.equals(password)){
				return new HomePage();
			}else{
				System.out.println("Please provide correct password");
				return null;
			}
		}else{
			System.out.println("Please provide correct username");
		}
		return null;
		
	}
	
	
}
