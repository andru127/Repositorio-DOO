package co.edu.uco.nose.test;

import java.util.UUID; 

import co.edu.uco.nose.business.business.facade.impl.UserFacadeImpl;
import co.edu.uco.nose.crosscutting.exception.NoseException;
import co.edu.uco.nose.dto.CityDTO;
import co.edu.uco.nose.dto.IdTypeDTO;
import co.edu.uco.nose.dto.UserDTO;

public class TestUserRegistration {
	
	public static void main(String[] args) {
		
		try {
			var user = new UserDTO();
			
			user.setIdentificationType(new IdTypeDTO(UUID.fromString("33333333-3333-3333-3333-333333333333")));
	        user.setIdentificationNumber("1040874400");
	        user.setFirstName("Raul");
	        user.setMiddleName("Andres");
	        user.setLastName("Arango");
	        user.setSecondLastName("Restrepo");
	        user.setResidenceCity(new CityDTO(UUID.fromString("c3333333-3333-3333-3333-333333333333")));
	        user.setEmail("andres.arango@example.com");
	        user.setCellPhoneNumber("3122159290");
	        user.setEmailConfirmed(false);
	        user.setCellPhoneNumberConfirmed(false);
			
			var facade = new UserFacadeImpl();
			facade.registerNewUserInformation(user);
			
			System.out.println("Gane el semestre");
		}catch(NoseException e) {
			System.out.println(e.getUserMessage());
			System.out.println(e.getTechnicalMessage());
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
			
		}
	
}
