package co.edu.uco.nose.business.business.rule.user;

import java.util.UUID;

import co.edu.uco.nose.business.business.rule.Rule;
import co.edu.uco.nose.crosscutting.exception.NoseException;
import co.edu.uco.nose.crosscutting.helpers.UUIDHelper;
import co.edu.uco.nose.data.dao.factory.DAOFactory;

public final class UserMobileNumberDoesNotExistsRule implements Rule{


	@Override
	public void execute (final Object... data) {
	//...Mismas validaciones de las demas reglas 
	//..Que data no sea nulo
	//..Que data tenga al menos 2 parametros
	
	
	var id = (UUID) data[0];
	var daoFactory = (DAOFactory) data[1];
	
	var user = daoFactory.getUserDAO().findById(id);
	
	if(UUIDHelper.getUUIDHelper().isDefaultUUID(user.getUserId())) {
		var userMessage = "";
		var technicalMessage = "";
		throw NoseException.create(userMessage, technicalMessage);
		}
	}
}

