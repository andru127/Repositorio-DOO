package co.edu.uco.nose.entity;

import java.sql.Connection;
import java.sql.SQLException;

import co.edu.uco.nose.crosscutting.exception.NoseException;
import co.edu.uco.nose.crosscutting.helpers.ObjectHelper;
import co.edu.uco.nose.crosscutting.messagescatalog.MessagesEnum;

public abstract class SqlConnection {
	
	private Connection connection;
	
	protected SqlConnection(final Connection connection) {
		setConnection(connection);
	}

	protected Connection getConnection() {
		return connection;
	}

	private void setConnection(Connection connection) {
		if (ObjectHelper.isNull(connection)) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_CONNECTION_IS_EMPTY.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_CONNECTION_IS_EMPTY.getContent();
			throw NoseException.create(userMessage, technicalMessage);
		}
		
		try {
			if(connection.isClosed()) {
				var userMessage = MessagesEnum.USER_ERROR_SQL_CONNECTION_IS_CLOSED.getContent();
				var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_CONNECTION_IS_CLOSED.getContent();
				throw NoseException.create(userMessage, technicalMessage);
				
			}
		} catch (final SQLException exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_CONNECTION_STATUS.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_CONNECTION_STATUS.getContent();
			throw NoseException.create(exception, userMessage, technicalMessage);
		}
		
		this.connection = connection;
	}
	
	
}

