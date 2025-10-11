package co.edu.uco.nose.data.dao.factory.sqlserver;

import java.sql.DriverManager;
import java.sql.SQLException;

import co.edu.uco.nose.crosscutting.exception.NoseException;
import co.edu.uco.nose.data.dao.entity.CityDAO;
import co.edu.uco.nose.data.dao.entity.CountryDAO;
import co.edu.uco.nose.data.dao.entity.IdTypeDAO;
import co.edu.uco.nose.data.dao.entity.StateDAO;
import co.edu.uco.nose.data.dao.entity.UserDAO;
import co.edu.uco.nose.data.dao.entity.postgresql.CityPostgresqlDAO;
import co.edu.uco.nose.data.dao.entity.postgresql.CountryPostgresqlDAO;
import co.edu.uco.nose.data.dao.entity.postgresql.IdTypePostgresqlDAO;
import co.edu.uco.nose.data.dao.entity.postgresql.StatePostgresqlDAO;
import co.edu.uco.nose.data.dao.entity.postgresql.UserPostgresqlDAO;
import co.edu.uco.nose.data.dao.factory.DAOFactory;

public class SqlServerDAOFactory extends DAOFactory {

	public SqlServerDAOFactory() {
		 
	}
	
	@Override
	protected void openConnection() {
		try {
		this.connection = DriverManager.getConnection("");
		} catch(final SQLException exception) {
			var userMessage = "";
			var technicalMessage = "";
			throw NoseException.create(userMessage, technicalMessage);
		}catch(final Exception exception) {
			var userMessage = "";
			var technicalMessage = "";
			throw NoseException.create(userMessage, technicalMessage);
		}
		
	}
	
	@Override
	public CityDAO getCityDAO() {
		return new CityPostgresqlDAO(connection);
	}

	@Override
	public CountryDAO getCountryDAO() {
		return new CountryPostgresqlDAO(connection);
	}

	@Override
	public IdTypeDAO getIdTypeDAO() {
		return new IdTypePostgresqlDAO(connection);
	}

	@Override
	public StateDAO getStateDAO() {
		return new StatePostgresqlDAO(connection); 
	}

	@Override
	public UserDAO getUserDAO() {
		return new UserPostgresqlDAO(connection);
	}

	

}
