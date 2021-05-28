package exceptions;

public class LoginException extends RuntimeException {
	private String errorMessage;
	public LoginException(String error) {
		this.errorMessage=error;
	}
	public String getErrorMessage() {
		return errorMessage;
	}

}
