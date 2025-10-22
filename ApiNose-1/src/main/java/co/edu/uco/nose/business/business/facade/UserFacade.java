package co.edu.uco.nose.business.business.facade;

import java.util.List;
import java.util.UUID;

import co.edu.uco.nose.business.domain.UserDomain;
import co.edu.uco.nose.dto.UserDTO;

public interface UserFacade {

	public void registerNewUserInformation(UserDTO userDTO);
	
	void dropUserInformation(UUID id);
	
	void updateUserInformation(UUID id, UserDTO userDTO);
	
	List<UserDTO> findAllUsers();
	
	List<UserDTO> findUserByFilter(UserDTO userFilters);
	
	UserDomain findSpecificUser(UUID id); 
	 
	void confirmMobileNumber(UUID id, int confirmationCode);
	
	void confirmEmail(UUID id, int confirmationCode);
	
	void sendMobileNumberConfirmation(UUID id);
	
	void sendEmailConfirmation(UUID id);
}

