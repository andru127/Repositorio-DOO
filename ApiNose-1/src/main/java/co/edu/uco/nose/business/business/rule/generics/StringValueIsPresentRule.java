package co.edu.uco.nose.business.business.rule.generics;

import co.edu.uco.nose.business.business.rule.Rule;
import co.edu.uco.nose.crosscutting.exception.NoseException;
import co.edu.uco.nose.crosscutting.helpers.ObjectHelper;
import co.edu.uco.nose.crosscutting.helpers.TextHelper;

public final class StringValueIsPresentRule implements Rule {
	
	private static final Rule instance = new StringValueIsPresentRule();
	
	private StringValueIsPresentRule() {
		
	}
	
	public static void executeRule(final Object...data ) {
		instance.execute(data);
	}

	@Override
	public void execute(final Object... data) {
			if (ObjectHelper.isNull(data)) {
				var userMessage = "Se ha presentado un problema tratando de llevar a cabo la operacion deseada ";
				var technicalMessage = "No se recibieron los parametros requeridos para ejecutar la regla StringValueIsPresentRule";
				throw NoseException.create(userMessage, technicalMessage);
			}
			
			if (data.length < 3) {
				var userMessage = "Se ha presentado un problema inesperado tratando de llevar a cabo la operacion deseada ";
				var technicalMessage = "Se requerian 3 parametros para ejecutar la regla StringValueIsPresentRule pero solo se recibieron " ;
				throw NoseException.create(userMessage, technicalMessage);
		}
			var stringdata = (String) data[0];
			var dataName = (String) data[1];
			boolean mustApplyTrim = (Boolean) data[2];
			
			if ((mustApplyTrim)
					? TextHelper.isEmptyWithTrim(stringdata)
							: TextHelper.isEmpty(stringdata)) {
				var userMessage = "El dato [".concat(dataName).concat("] es requerido para llevar la operación ");
				var technicalMessage = "La regla StringValueIsPresentRule ha fallado para el dato [".concat(dataName).concat("] porque se recibio un valor nulo o vacio");
				throw NoseException.create(userMessage, technicalMessage);
			}
	}

	
}
	
