package co.edu.uco.nose.business.business.validator.user;

import co.edu.uco.nose.business.business.rule.generics.StringLengthValueIsValidRule;
import co.edu.uco.nose.business.business.rule.generics.StringValueIsPresentRule;
import co.edu.uco.nose.business.business.validator.Validator;
import co.edu.uco.nose.business.domain.UserDomain;
import co.edu.uco.nose.crosscutting.helpers.TextHelper;

public final class ValidateDataUserConsistencyForRegisterNewInformation implements Validator{
	
	private static final Validator instance = new ValidateDataUserConsistencyForRegisterNewInformation();
	
	private ValidateDataUserConsistencyForRegisterNewInformation() {
		
	}
	
	public static void executeValidation(final UserDomain data ) {
		instance.validate(data);
	}

	@Override 
	public void validate(final Object... data) {
		
		//validaciones del objecto data 
		var userDomainData = (UserDomain) data[0];
		
		// Valid empty data
		validateEmptyData(userDomainData);
		
		// Valid data length
		validateDataLength(userDomainData);
		
		// Valid data format
		// Valid data valid range
	}

	private void validateEmptyData(final UserDomain data) {
		
		StringValueIsPresentRule.executeRule(data.getIdentificationNumber(), "numero de identificacion", true);
	    StringValueIsPresentRule.executeRule(data.getFirstName(), "primer nombre", true);
	    StringValueIsPresentRule.executeRule(data.getLastName(), "primer apellido", true);
	    // continue with other attributes validations
	}
	
private void validateDataLength(final UserDomain data) {
		
		StringLengthValueIsValidRule.executeRule(data.getIdentificationNumber(), "numero de identificacion", 1, 50, true);
	    StringLengthValueIsValidRule.executeRule(data.getFirstName(), "primer nombre", 1, 100);
	    
	    if (!TextHelper.isEmptyWithTrim(data.getMiddleName())) {
	    	StringLengthValueIsValidRule.executeRule(data.getMiddleName(), "segundo nombre", 1, 100);
	    }
	    
	    StringLengthValueIsValidRule.executeRule(data.getLastName(), "primer apellido", 1, 100);
	    
	    if (!TextHelper.isEmptyWithTrim(data.getSecondLastName())) {
	    	StringLengthValueIsValidRule.executeRule(data.getSecondLastName(), "segundo apellido", 1, 100);
	    }
	    	// continue with other attributes validations
	}

}
