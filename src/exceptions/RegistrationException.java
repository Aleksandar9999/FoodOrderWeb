package exceptions;

public class RegistrationException extends RuntimeException {
	private String errorMessage;
	public RegistrationException(String err) {
		this.errorMessage=err;
	}

}
