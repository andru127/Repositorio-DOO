package co.edu.uco.nose.data.dao.factory;

import co.edu.uco.nose.crosscutting.exception.NoseException;
import co.edu.uco.nose.crosscutting.helpers.SqlConnectionHelper;
import co.edu.uco.nose.data.dao.entity.IdTypeDAO;
import co.edu.uco.nose.data.dao.entity.CityDAO;
import co.edu.uco.nose.data.dao.entity.CountryDAO;
import co.edu.uco.nose.data.dao.entity.StateDAO;
import co.edu.uco.nose.data.dao.entity.UserDAO;
import co.edu.uco.nose.data.dao.factory.sqlserver.SqlServerDAOFactory;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class DAOFactory {
    protected Connection connection;
    protected static FactoryEnum factory = FactoryEnum.SQLSERVER;

    public static DAOFactory getFactory() {
        switch (factory) {
        	case SQLSERVER:
				return new SqlServerDAOFactory();
		 default:
			var userMessage = "Factoria no iniciada";
			var technicalMessage = "Factoria no valida";
			throw NoseException.create(userMessage, technicalMessage);
		}
    }
    
    
    
    public abstract CityDAO getCityDAO();
    public abstract CountryDAO getCountryDAO();
    public abstract IdTypeDAO getIdTypeDAO();
    public abstract StateDAO getStateDAO();
    public abstract UserDAO getUserDAO();

    protected abstract void openConnection();
    protected final void initTransaction(){
        SqlConnectionHelper.ensureTransactionIsNotStarted(connection);

        try{
            connection.setAutoCommit(false);
        } catch (final SQLException exception){
            var userMessage = "";
            var techincalMessage = "";
            throw NoseException.create(exception, userMessage, techincalMessage);
        } catch (final Exception exception){
            var userMessage = "";
            var techincalMessage = "";
            throw NoseException.create(exception, userMessage, techincalMessage);
        }

    }

    protected final void commitTransaction(){
        SqlConnectionHelper.ensureTransactionIsStarted(connection);

        try{
            connection.(false);
        } catch (final SQLException exception){
            var userMessage = "";
            var techincalMessage = "";
            throw NoseException.create(userMessage, techincalMessage);
        } catch (final Exception exception){
            var userMessage = "";
            var techincalMessage = "";
            throw NoseException.create(userMessage, techincalMessage);
        }
    }

    protected final void rollbackTransaction(){
        SqlConnectionHelper.ensureTransactionIsStarted(connection);
        try{
            connection.rollback();
        } catch (final SQLException exception){
            var userMessage = "";
            var techincalMessage = "";
            throw NoseException.create(userMessage, techincalMessage);
        } catch (final Exception exception){
            var userMessage = "";
            var techincalMessage = "";
            throw NoseException.create(userMessage, techincalMessage);
        }
    }

    protected final  void closeConnection(){
        SqlConnectionHelper.ensureConnectionIsOpen(connection);

        try{
            connection.close();
        } catch (final SQLException exception){
            var userMessage = "";
            var techincalMessage = "";
            throw NoseException.create(exception, userMessage, techincalMessage);
        } catch (final Exception exception){
            var userMessage = "";
            var techincalMessage = "";
            throw NoseException.create(exception,userMessage, techincalMessage);
        }



    }
}