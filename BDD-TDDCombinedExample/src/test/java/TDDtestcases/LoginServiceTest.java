package TDDtestcases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.app.LoginResponse;
import com.app.LoginService;

import static org.junit.jupiter.api.Assertions.*;

class LoginServiceTest {

    private LoginService loginService;

    @BeforeEach
    void setUp() {
        loginService = new LoginService();
    }

    @Test
    @DisplayName("Valid credentials should return success")
    void shouldLoginSuccessfullyWithValidCredentials() {

        LoginResponse response = loginService.login("username", "password");

        assertTrue(response.isSuccess());
        assertEquals("Login successful", response.getMessage());
    }

    @ParameterizedTest(name = "Invalid login with username={0} password={1}")
    @CsvSource({
            "name1, pass1",
            "'', ''",
            "'', pass1",
            "name2, ''",
            "12443, @@%$%"
    })
    @DisplayName("Invalid credentials should return error message")
    void shouldReturnErrorForInvalidCredentials(String username, String password) {

        LoginResponse response = loginService.login(username, password);

        assertFalse(response.isSuccess());
        assertEquals("Login and/or password are wrong.", response.getMessage());
    }

    @Test
    @DisplayName("Null credentials should be handled safely")
    void shouldHandleNullInputs() {

        LoginResponse response = loginService.login(null, null);

        assertFalse(response.isSuccess());
        assertEquals("Username or password cannot be null", response.getMessage());
    }
}
