package co.edu.uco.nose.business.business.rule.idtype;

import java.util.UUID;

import co.edu.uco.nose.business.business.rule.Rule;
import co.edu.uco.nose.crosscutting.exception.NoseException;
import co.edu.uco.nose.crosscutting.helpers.UUIDHelper;
import co.edu.uco.nose.data.dao.factory.DAOFactory;

public final class IdTypeExistsByIdRule implements Rule {
	
	private static final Rule instance = new IdTypeExistsByIdRule();

    private IdTypeExistsByIdRule() {

    }
    public static void executeRule(final Object... data){
        instance.execute(data);
    }

	@Override
	public void execute (final Object... data) {
		//...Mismas validaciones de las demas reglas 
		//..Que data no sea nulo
		//..Que data tenga al menos 2 parametros
		
		var id = (UUID) data[0];
		var daoFactory = (DAOFactory) data[1];
		
		var idType = daoFactory.getIdTypeDAO().findById(id);
		
		if(UUIDHelper.getUUIDHelper().isDefaultUUID(idType.getIdTypeId())) {
			var userMessage = "El tipo de identificacion deseado no existe";
			var technicalMessage = "El tipo de idnetificacion con id ".concat(id.toString()).concat(" no existe ");
			throw NoseException.create(userMessage, technicalMessage);
		}
		
	}

}
