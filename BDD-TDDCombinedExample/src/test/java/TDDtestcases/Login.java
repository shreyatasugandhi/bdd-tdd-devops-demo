package TDDtestcases;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import businesslogic.HomePage;
import businesslogic.LoginPage;

public class Login {
	private LoginPage lp;
	
	@BeforeEach
	public void setup() {
		lp = new LoginPage();
	}
	
	
	@Test
	public void checkLogin(){
	   lp.enterUserName("UserName");
	   lp.enterPassword("Password");
	   HomePage hp = lp.submit();
	   assertNotNull(hp);
	}
	
	@AfterEach
	public void cleanup() {
		lp = null;
	}
	

}
