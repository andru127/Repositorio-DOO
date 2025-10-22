package co.edu.uco.nose.business.business.facade.impl;

import java.util.List;
import java.util.UUID;

import co.edu.uco.nose.business.assembler.dto.impl.UserDTOAssembler;
import co.edu.uco.nose.business.business.facade.UserFacade;
import co.edu.uco.nose.business.business.impl.UserBusinessImpl;
import co.edu.uco.nose.business.domain.UserDomain;
import co.edu.uco.nose.crosscutting.exception.NoseException;
import co.edu.uco.nose.data.dao.factory.DAOFactory;
import co.edu.uco.nose.dto.UserDTO;

public final class UserFacadeImpl implements UserFacade {
	
	@Override
	public void registerNewUserInformation(final UserDTO userDTO) {
		var daoFactory = DAOFactory.getFactory();
		var business = new UserBusinessImpl(daoFactory);
		
		try {
			daoFactory.initTransaction();  //SOLO SE USA EN LAS OPERACIONES DE UPDATE, DE RESTO SOLO SE ABRE Y SE CIERRA
			
			var domain = UserDTOAssembler.getUserDTOAssembler().toDomain(userDTO);
			business.registerNewUserInformation(domain);
			
			daoFactory.commitTransaction(); //TAMPOCO IRÍA EL COMMIT EN LAS QUE NO SON UPDATE
			
			
		} catch (final NoseException exception) {
			daoFactory.rollbackTransaction();
			throw exception;
		} catch (final Exception exception ) {
			daoFactory.rollbackTransaction();
			
			var userMessage = "";
			var technicalMessage = "";
			
			throw NoseException.create(exception, userMessage, technicalMessage);
			
		} finally {
			daoFactory.closeConnection();
		}
		
	}

	@Override
	public void dropUserInformation(UUID id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUserInformation(UUID id, UserDTO userDTO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserDomain findSpecificUser(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDTO> findAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDTO> findUserByFilter(UserDTO userFilters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void confirmMobileNumber(UUID id, int confirmationCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void confirmEmail(UUID id, int confirmationCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendMobileNumberConfirmation(UUID id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendEmailConfirmation(UUID id) {
		// TODO Auto-generated method stub
		
	}

}
