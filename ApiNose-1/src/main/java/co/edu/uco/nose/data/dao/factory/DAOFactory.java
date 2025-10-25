package co.edu.uco.nose.data.dao.factory;

import co.edu.uco.nose.crosscutting.exception.NoseException;
import co.edu.uco.nose.crosscutting.helpers.SqlConnectionHelper;
import co.edu.uco.nose.crosscutting.messagescatalog.MessagesEnum;
import co.edu.uco.nose.data.dao.entity.IdTypeDAO;
import co.edu.uco.nose.data.dao.entity.CityDAO;
import co.edu.uco.nose.data.dao.entity.CountryDAO;
import co.edu.uco.nose.data.dao.entity.StateDAO;
import co.edu.uco.nose.data.dao.entity.UserDAO;
import co.edu.uco.nose.data.dao.factory.postgresql.PostgresqlDAOFactory;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class DAOFactory {
    protected Connection connection;
    protected static FactoryEnum factory = FactoryEnum.POSTGRESQL;

    public static DAOFactory getFactory() {
    	
    	if (FactoryEnum.POSTGRESQL.equals(factory)) {
    		return new PostgresqlDAOFactory();
		}else {
			var userMessage = MessagesEnum.USER_ERROR_SQL_CONNECTION_SQL_EXCEPTION_VALIDATING_TRANSACTION_IS_NOT_STARTED.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_CONNECTION_SQL_EXCEPTION_VALIDATING_TRANSACTION_IS_NOT_STARTED.getContent();
			throw NoseException.create(userMessage, technicalMessage);
		}
    }
    
    
    
    public abstract CityDAO getCityDAO();
    
    public abstract CountryDAO getCountryDAO();
    
    public abstract IdTypeDAO getIdTypeDAO();
    
    public abstract StateDAO getStateDAO();
    
    public abstract UserDAO getUserDAO();
    
    protected abstract void openConnection();
    
    public final void initTransaction(){
        SqlConnectionHelper.ensureTransactionIsNotStarted(connection);

        try{
            connection.setAutoCommit(false);
        } catch (final SQLException exception){
            var userMessage = MessagesEnum.TECHNICAL_ERROR_SQL_CONNECTION_SQL_EXCEPTION_VALIDATING_TRANSACTION_IS_NOT_STARTED.getContent();
            var techincalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_CONNECTION_SQL_EXCEPTION_VALIDATING_TRANSACTION_IS_NOT_STARTED.getContent();
            throw NoseException.create(exception, userMessage, techincalMessage);
        } catch (final Exception exception){
            var userMessage = MessagesEnum.USER_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_TRANSACTION_IS_NOT_STARTED.getContent();
            var techincalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_TRANSACTION_IS_NOT_STARTED.getContent();
            throw NoseException.create(exception, userMessage, techincalMessage);
        }

    }

    public final void commitTransaction(){
        
    	SqlConnectionHelper.ensureTransactionIsStarted(connection);

        try{
            connection.setAutoCommit(true);
        } catch (final SQLException exception){
            var userMessage = MessagesEnum.USER_ERROR_SQL_CONNECTION_SQL_EXCEPTION_VALIDATING_TRANSACTION_IS_STARTED.getContent();
            var techincalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_CONNECTION_SQL_EXCEPTION_VALIDATING_TRANSACTION_IS_STARTED.getContent();
            throw NoseException.create(exception, userMessage, techincalMessage);
        } catch (final Exception exception){
            var userMessage = MessagesEnum.USER_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_TRANSACTION_IS_STARTED.getContent();
            var techincalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_TRANSACTION_IS_STARTED.getContent();
            throw NoseException.create(exception, userMessage, techincalMessage);
        }
    }

    public final void rollbackTransaction(){
        SqlConnectionHelper.ensureTransactionIsStarted(connection);
        try{
            connection.rollback();
        } catch (final SQLException exception){
            var userMessage = MessagesEnum.USER_ERROR_SQL_CONNECTION_SQL_EXCEPTION_VALIDATING_TRANSACTION_IS_STARTED.getContent();
            var techincalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_CONNECTION_SQL_EXCEPTION_VALIDATING_TRANSACTION_IS_STARTED.getContent();
            throw NoseException.create(exception, userMessage, techincalMessage);
        } catch (final Exception exception){
            var userMessage = MessagesEnum.USER_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_TRANSACTION_IS_STARTED.getContent();
            var techincalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_TRANSACTION_IS_STARTED.getContent();
            throw NoseException.create(exception, userMessage, techincalMessage);
        }
    }

    public final  void closeConnection(){
        SqlConnectionHelper.ensureConnectionIsOpen(connection);

        try{
            connection.close();
        } catch (final SQLException exception){
            var userMessage = MessagesEnum.USER_ERROR_SQL_CONNECTION_SQL_EXCEPTION_VALIDATING_TRANSACTION_IS_OPEN.getContent();
            var techincalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_CONNECTION_SQL_EXCEPTION_VALIDATING_TRANSACTION_IS_OPEN.getContent();
            throw NoseException.create(exception, userMessage, techincalMessage);
        } catch (final Exception exception){
            var userMessage = MessagesEnum.USER_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_TRANSACTION_IS_OPEN.getContent();
            var techincalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_TRANSACTION_IS_OPEN.getContent();
            throw NoseException.create(exception,userMessage, techincalMessage);
        }



    }
}