
package co.edu.uco.nose.business.business.impl;

import java.util.List;
import java.util.UUID;

import co.edu.uco.nose.business.assembler.entity.impl.UserEntityAssembler;
import co.edu.uco.nose.business.business.UserBusiness;
import co.edu.uco.nose.business.business.validator.idtype.ValidateIdTypeExistsById;
import co.edu.uco.nose.business.business.validator.user.ValidateDataUserConsistencyForRegisterNewInformation;
import co.edu.uco.nose.business.domain.UserDomain;
import co.edu.uco.nose.crosscutting.helpers.UUIDHelper;
import co.edu.uco.nose.data.dao.factory.DAOFactory;

public final class UserBusinessImpl implements UserBusiness {

	private DAOFactory daoFactory;
	
	
	public UserBusinessImpl(final DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override 
	public void registerNewUserInformation(final UserDomain userDomain) {
		
		//1. Validar que la informacion sea consistente a nivel de tipo de dato, longitud, oblatoriedad
		ValidateDataUserConsistencyForRegisterNewInformation.executeValidation(userDomain);
		
		//2. Validar que exista el tipo de identificacion
		ValidateIdTypeExistsById.executeValidation(userDomain.getIdentificationType().getId(), daoFactory);

		//3. Validar que exista la ciudad de residencia

		
		//4. Validar que no exista otro usuario con el mismo tipo y número de documento
		//5. Validar que no exista previamente un usuario con el mismo email
		//6. Validar que no exista previamente un usuario con el mismo número de teléfono celular
		
		//7. Ensamblar objeto como entity
		var userEntity = UserEntityAssembler.getUserEntityAssembler().toEntity(userDomain);
		
		//8. Generar Id
		userEntity.setUserId(generateId());
		
		//9. Registrar la informacion del nuevo usuario
		daoFactory.getUserDAO().create(userEntity);
		
	}

	private UUID generateId() {
		var id = UUIDHelper.getUUIDHelper().generateNewUUID();
		var userEntity = daoFactory.getUserDAO().findById(id);
		
		while (!UUIDHelper.getUUIDHelper().isDefaultUUID(userEntity.getUserId())) {
			id = UUIDHelper.getUUIDHelper().generateNewUUID();
			userEntity = daoFactory.getUserDAO().findById(id);
		}
		
		return id;

	}
		
	
	
	@Override
	public void dropUserInformation(UUID id) {
		daoFactory.getUserDAO().delete(id);
		
	}

	@Override
	public void updateUserInformation(UUID id, UserDomain userDomain) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<UserDomain> findAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDomain> findUserByFilter(UserDomain userFilters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDomain findSpecificUser(UUID id) {
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
