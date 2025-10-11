package co.edu.uco.nose.data.dao.entity.postgresql;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import co.edu.uco.nose.crosscutting.exception.NoseException;
import co.edu.uco.nose.crosscutting.helpers.SqlConnectionHelper;
import co.edu.uco.nose.data.dao.entity.SqlConnection;
import co.edu.uco.nose.data.dao.entity.UserDAO;
import co.edu.uco.nose.entity.UserEntity;

public class UserPostgresqlDAO extends SqlConnection implements UserDAO{

	public UserPostgresqlDAO(final java.sql.Connection connection) {
		super(connection);
	}
	
	@Override
	public void create(UserEntity entity) {
		
		SqlConnectionHelper.ensureTransactionIsStarted(getConnection());
		
		final var sql = new StringBuilder();
		sql.append("Insert into");
		sql.append("Select");
		
		try (var prepareStatement = this.getConnection().prepareStatement(sql.toString())) {
			
			PreparedStatement.setObject(1, entity.getId());
			PreparedStatement.setString(2, entity.getIdType().getId());
			PreparedStatement.setString(3, entity.getIdNumber());
			PreparedStatement.setString(4, entity.getFirstName());
			PreparedStatement.setString(5, entity.getSecondName());
			PreparedStatement.setString(6, entity.getFirstSurname());
			PreparedStatement.setString(7, entity.getSecondSurname());
			PreparedStatement.setDate(8, entity.getHomeCity().getId());
			PreparedStatement.setDate(9, entity.getEmail());
			PreparedStatement.setDate(10, entity.getMobileNumber());
			PreparedStatement.setDate(11, entity.getisEmailConfirmed());
			PreparedStatement.setDate(12, entity.getisMobileNumberConfirmed());
			
			prepareStatement.executeUpdate();
			
		} catch (final SQLException exception) {
			var userMessage = "Se ha presentado un problema tratando de registrar la información del nuevo usuario.";
			var technicalMessage = "Se ha presentado un problema de tipo SQLException "
					+ "mientras se intentaba registrar la información del nuevo usuario. "
					+ "Por favor verifique la traza completa del error.";
			throw NoseException.create(exception, technicalMessage, userMessage );
		}catch (final Exception exception) {
			var userMessage = "Se ha presentado un problema inesperado tratando de registrar la información del nuevo usuario.";
			var technicalMessage = "Se ha presentado un problema de tipo Exception "
					+ "mientras se intentaba registrar la información del nuevo usuario. "
					+ "Por favor verifique la traza completa del error.";
			throw NoseException.create(exception, technicalMessage, userMessage );
		}
		
	}

	@Override
	public List<UserEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserEntity> findByFilter(UserEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserEntity findById(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(UserEntity entity) {
		SqlConnectionHelper.ensureTransactionIsStarted(getConnection());
		
		final var sql = new StringBuilder();
		sql.append("Update");
		sql.append("Set");
		
		try (var prepareStatement = this.getConnection().prepareStatement(sql.toString())) {
			
			PreparedStatement.setObject(1, entity.getId());
			PreparedStatement.setString(2, entity.getIdType().getId());
			PreparedStatement.setString(3, entity.getIdNumber());
			PreparedStatement.setString(4, entity.getFirstName());
			PreparedStatement.setString(5, entity.getSecondName());
			PreparedStatement.setString(6, entity.getFirstSurname());
			PreparedStatement.setString(7, entity.getSecondSurname());
			PreparedStatement.setDate(8, entity.getHomeCity().getId());
			PreparedStatement.setDate(9, entity.getEmail());
			PreparedStatement.setDate(10, entity.getMobileNumber());
			PreparedStatement.setDate(11, entity.getisEmailConfirmed());
			PreparedStatement.setDate(12, entity.getisMobileNumberConfirmed());
			
			prepareStatement.executeUpdate();
			
		} catch (final SQLException exception) {
			var userMessage = "Se ha presentado un problema tratando de actualizar la información del usuario.";
			var technicalMessage = "Se ha presentado un problema de tipo SQLException "
					+ "mientras se intentaba actualizar la información del usuario. "
					+ "Por favor verifique la traza completa del error.";
			throw NoseException.create(exception, technicalMessage, userMessage );
	}

	@Override
	public void delete(UUID id) {
		// TODO Auto-generated method stub
		
	}

}
