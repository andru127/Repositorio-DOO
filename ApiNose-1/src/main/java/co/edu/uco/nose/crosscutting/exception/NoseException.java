package co.edu.uco.nose.crosscutting.exception;

import co.edu.uco.nose.crosscutting.helpers.ObjectHelper;
import co.edu.uco.nose.crosscutting.helpers.TextHelper;

public final class NoseException extends RuntimeException  {

	private static final long serialVersionUID = 1429081348654732011L;
	private Throwable rootException;
	private String userMessage;
	private String technicalMessage;
	
	
	private NoseException(final Throwable rootException, final String userMessage, final String technicalMessage) {
		setRootException(rootException);
		setUserMessage(userMessage);
		setTechnicalMessage(technicalMessage);
	}
	
	public static NoseException create(final String UserMessage) {
		return new NoseException(new Exception(), UserMessage, UserMessage);
	}
	
	public static NoseException create(final String UserMessage, final String technicalMessage) {
		return new NoseException(new Exception(), UserMessage, technicalMessage);
	}
	
	public static NoseException create(final Throwable rootException, final String UserMessage, final String technicalMessage) {
		return new NoseException(rootException, UserMessage, technicalMessage);
	}
	
	public Throwable getRootException() {
		return rootException;
	}
	private void setRootException(final Throwable rootException) {
		this.rootException = ObjectHelper.getDefault(rootException, new Exception());
	}
	public String getUserMessage() {
		return userMessage;
	}
	private void setUserMessage(final String userMessage) {
		this.userMessage = TextHelper.getDefaultWithTrim(userMessage);
	}
	public String getTechnicalMessage() {
		return technicalMessage;
	}
	private void setTechnicalMessage(final String technicalMessage) {
		this.technicalMessage = TextHelper.getDefaultWithTrim(technicalMessage);
	}
	public static long getSerialversionuid() {
		return serialVersionUID; 
	}
	
	
	
}
