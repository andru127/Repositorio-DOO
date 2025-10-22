package co.edu.uco.nose.data.dao.entity.postgresql;

import co.edu.uco.nose.crosscutting.exception.NoseException;
import co.edu.uco.nose.crosscutting.helpers.BooleanHelper;
import co.edu.uco.nose.crosscutting.helpers.ObjectHelper;
import co.edu.uco.nose.crosscutting.helpers.SqlConnectionHelper;
import co.edu.uco.nose.crosscutting.helpers.TextHelper;
import co.edu.uco.nose.crosscutting.helpers.UUIDHelper;
import co.edu.uco.nose.data.dao.entity.SqlConnection;
import co.edu.uco.nose.data.dao.entity.UserDAO;
import co.edu.uco.nose.entity.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class UserPostgresqlDAO extends SqlConnection implements UserDAO {

    public UserPostgresqlDAO(final Connection connection) {
        super(connection);
    }

    @Override
    public void create(final UserEntity entity) {
        SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

        final var sql = new StringBuilder();
        sql.append("INSERT INTO User(id, tipoIdentificacion, numeroIdentificacion, primerNombre, segundoNombre, primerApellido, segundoApellido, ciudadResidencia, correoElectronico, numeroTelefonoMovil, correoElectronicoConfirmado, numeroTelefonoMovilConfirmado) ");
        sql.append("SELECT ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?");

        try (var preparedStatement = this.getConnection().prepareStatement(sql.toString())) {
            preparedStatement.setObject(1, entity.getUserId());
            preparedStatement.setObject(2, entity.getIdentificationType().getIdTypeId());
            preparedStatement.setString(3, entity.getIdentificationNumber());
            preparedStatement.setString(4, entity.getFirstName());
            preparedStatement.setString(5, entity.getMiddleName());
            preparedStatement.setString(6, entity.getLastName());
            preparedStatement.setString(7, entity.getSecondLastName());
            preparedStatement.setObject(8, entity.getResidenceCity().getCityId());
            preparedStatement.setString(9, entity.getEmail());
            preparedStatement.setString(10, entity.getCellPhoneNumber());
            preparedStatement.setBoolean(11, entity.isEmailConfirmed());
            preparedStatement.setBoolean(12, entity.CellPhoneNumberConfirmed());

            preparedStatement.executeUpdate();

        } catch (final SQLException exception) {
            var userMessage = "Se ha presentado un problema tratando de registrar la información del nuevo usuario. Intente de nuevo.";
            var technicalMessage = "SQLException al insertar Usuario: " + exception.getMessage();
            throw NoseException.create(exception, userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = "Se ha presentado un problema inesperado tratando de registrar la información del nuevo usuario.";
            var technicalMessage = "Excepción inesperada al insertar Usuario: " + exception.getMessage();
            throw NoseException.create(exception, userMessage, technicalMessage);
        }
    }

    @Override
    public List<UserEntity> findAll() {
        return findByFilter(new UserEntity());    
    }

    @Override
    public List<UserEntity> findByFilter(final UserEntity filterEntity) {
    	var parametersList = new ArrayList<Object>();
		var sql = createSentenceFindByFilter(filterEntity, parametersList);
		
			try (var preparedStatement = this.getConnection().prepareStatement(sql)) {
				
				for (int index = 0; index < parametersList.size(); index++) {
					preparedStatement.setObject(index + 1, parametersList.get(index));
				}
				
				return executeSentenceFindByFilter(preparedStatement);
				
			}catch (final NoseException exception) {
				throw exception;
			}catch (final SQLException exception) {
				var userMessage = "";
				var technicalMessage = "" + exception.getMessage();
				throw NoseException.create(exception, userMessage, technicalMessage);

			}catch (Exception exception) {
				var userMessage = "";
				var technicalMessage = "";
				throw NoseException.create(exception, userMessage, technicalMessage);

			}  
    }
    
    private String createSentenceFindByFilter(final UserEntity filterEntity, final List<Object> parameterList) {
		final var sql = new StringBuilder();
	
		sql.append("SELECT ");
	    sql.append("  u.usuarioId, ");
	    sql.append("  ti.id AS idTipoIdentificacion, ");
	    sql.append("  ti.nombre AS nombreTipoIdentificacion, ");
	    sql.append("  u.\"numeroIdentificacion\", ");
	    sql.append("  u.\"primerNombre\", ");
	    sql.append("  u.\"segundoNombre\", ");
	    sql.append("  u.\"primerApellido\", ");
	    sql.append("  u.\"segundoApellido\", ");
	    sql.append("  c.id AS idCiudadResidencia, ");
	    sql.append("  c.nombre AS nombreCiudadResidencia, ");
	    sql.append("  d.id AS idDepartamentoCiudadResidencia, ");
	    sql.append("  d.nombre AS nombreDepartamentoCiudadResidencia, ");
	    sql.append("  p.id AS idPaisDepartamentoCiudadResidencia, ");
	    sql.append("  p.nombre AS nombrePaisDepartamentoCiudadResidencia, ");
	    sql.append("  u.\"correoElectronico\", ");
	    sql.append("  u.\"numeroTelefonoMovil\", ");
	    sql.append("  u.\"correoElectronicoConfirmado\", ");
	    sql.append("  u.\"numeroTelefonoMovilConfirmado\" ");
	    
	    sql.append("FROM \"Usuario\" AS u ");
	    sql.append("INNER JOIN \"TipoIdentificacion\" AS ti ");
	    sql.append("  ON u.\"tipoIdentificacion\" = ti.id ");
	    sql.append("INNER JOIN \"Ciudad\" AS c ");
	    sql.append("  ON u.\"ciudadResidencia\" = c.id ");
	    sql.append("INNER JOIN \"Departamento\" AS d ");
	    sql.append("  ON c.\"departamento\" = d.id ");
	    sql.append("INNER JOIN \"Pais\" AS p ");
	    sql.append("  ON d.pais = p.id ");
        
        createWhereClauseFindByFilter(sql, parameterList, filterEntity);
        
        return sql.toString();
    }
    
    private void createWhereClauseFindByFilter(final StringBuilder sql, final List<Object> parameterList, final UserEntity filterEntity) {
		var filterEntityValidated = ObjectHelper.getDefault(filterEntity, new UserEntity());
		final var conditions = new ArrayList<String>();
		
		addCondition(conditions, parameterList,
		!UUIDHelper.getUUIDHelper().isDefaultUUID(filterEntityValidated.getUserId()), "u.usuarioId = ? ",
		filterEntityValidated.getUserId());
		
		addCondition(conditions, parameterList,
		!UUIDHelper.getUUIDHelper().isDefaultUUID(filterEntityValidated.getIdentificationType().getIdTypeId()), "ti.id = ? ",
		filterEntityValidated.getIdentificationType().getIdTypeId());
		
		addCondition(conditions, parameterList,
		!TextHelper.isEmptyWithTrim(filterEntityValidated.getIdentificationNumber()), "u.\"numeroIdentificacion\" = ? ",
		filterEntityValidated.getIdentificationNumber());
		
		addCondition(conditions, parameterList,
		!TextHelper.isEmptyWithTrim(filterEntityValidated.getFirstName()), "u.\"primerNombre\" = ? ",
		filterEntityValidated.getFirstName());
		
		addCondition(conditions, parameterList,
		!TextHelper.isEmptyWithTrim(filterEntityValidated.getMiddleName()), "u.\"segundoNombre\" = ? ",
		filterEntityValidated.getMiddleName());
		
		addCondition(conditions, parameterList,
		!TextHelper.isEmptyWithTrim(filterEntityValidated.getLastName()), "u.\"primerApellido\" = ? ",
		filterEntityValidated.getLastName());
		
		addCondition(conditions, parameterList,
		!TextHelper.isEmptyWithTrim(filterEntityValidated.getSecondLastName()), "u.\"segundoApellido\" = ? ",
		filterEntityValidated.getSecondLastName());
		
		addCondition(conditions, parameterList,
		!UUIDHelper.getUUIDHelper().isDefaultUUID(filterEntityValidated.getResidenceCity().getCityId()), "c.id = ? ",
		filterEntityValidated.getResidenceCity().getCityId());
		
		addCondition(conditions, parameterList,
		!TextHelper.isEmptyWithTrim(filterEntityValidated.getEmail()), "u.\"correoElectronico\" = ? ",
		filterEntityValidated.getEmail());
		
		addCondition(conditions, parameterList,
		!TextHelper.isEmptyWithTrim(filterEntityValidated.getCellPhoneNumber()), "u.\"numeroTelefonoMovil\" = ? ",
		filterEntityValidated.getCellPhoneNumber());
		
		addCondition(conditions, parameterList,
		!filterEntityValidated.isEmailConfirmedIsDefaultValue(), "u.\"correoElectronicoConfirmado\" = ? ",
		filterEntityValidated.isEmailConfirmed());
		
		addCondition(conditions, parameterList, 
		!filterEntityValidated.CellPhoneNumberConfirmedIsDefaultValue(),"u.\"numeroTelefonoMovilConfirmado\" = ", 
		filterEntityValidated.CellPhoneNumberConfirmed());
		
		if (!conditions.isEmpty()) {
			sql.append(" WHERE ");
			sql.append(String.join(" AND ", conditions));
		}
		
    }
    
    private void addCondition(final List<String> conditions, final List<Object> parametersList, final boolean condition,
			final String clause, final Object value) {
		
		if(condition) {
			conditions.add(clause);
			parametersList.add(value);
		}
		
	}
    
private List<UserEntity> executeSentenceFindByFilter(final PreparedStatement preparedStatement) {
		
		var listUser = new ArrayList<UserEntity>();
		
		try (var resultSet = preparedStatement.executeQuery()) {
			
			while (resultSet.next()) {
				var country = new CountryEntity();
				country.setCountryId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idPaisDepartamentoCiudadResidencia")));
				country.setName(resultSet.getString("nombrePaisDepartamentoCiudadResidencia"));
				
				var state = new StateEntity();
				state.setStateId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("departamentoId")));
				state.setName(resultSet.getString("nombre"));
				state.setCountry(country);
				
				var idType = new IdTypeEntity();
				idType.setIdTypeId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idTipoIdentificacion")));
				idType.setName(resultSet.getString("nombreTipoIdentificacion"));
				
				var city = new CityEntity();
	            city.setState(state);
	            city.setCityId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idCiudadResidencia")));
	            city.setName(resultSet.getString("nombreCiudadResidencia")); 
	            
	            var user = new UserEntity();
	            user.setUserId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("id")));
	            user.setIdentificationType(idType);
	            user.setIdentificationNumber(resultSet.getString("numeroIdentificacion"));
	            user.setFirstName(resultSet.getString("primerNombre"));
	            user.setMiddleName(resultSet.getString("segundoNombre"));
	            user.setLastName(resultSet.getString("primerApellido"));
	            user.setSecondLastName(resultSet.getString("segundoApellido"));
	            user.setResidenceCity(city);
	            user.setEmail(resultSet.getString("correoElectronico"));
	            user.setCellPhoneNumber(resultSet.getString("numeroTelefonoMovil"));
	            user.setEmailConfirmed(resultSet.getBoolean("correoElectronicoConfirmado"));
	            user.setCellPhoneNumberConfirmed(resultSet.getBoolean("numeroTelefonoMovilConfirmado"));
				
				listUser.add(user);
			
			}
		}catch (final SQLException exception) {
			var userMessage = "";
			var technicalMessage = "" + exception.getMessage();
			throw NoseException.create(exception, userMessage, technicalMessage);
		}catch (final Exception exception) {
			var userMessage = "";
			var technicalMessage = "" + exception.getMessage();
			throw NoseException.create(exception, userMessage, technicalMessage);

		}
		return listUser;
		
	}
    
    

    @Override
    public UserEntity findById(final UUID id) {
    	return findByFilter(new UserEntity(id)).stream().findFirst().orElse(new UserEntity());	
    }

    @Override
    public void update(final UserEntity entity) {
        SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

        final var sql = new StringBuilder();
        sql.append("UPDATE User ");
        sql.append("SET tipoIdentificacion = ?, ");
        sql.append("    numeroIdentificacion = ?, ");
        sql.append("    primerNombre = ?, ");
        sql.append("    segundoNombre = ?, ");
        sql.append("    primerApellido = ?, ");
        sql.append("    segundoApellido = ?, ");
        sql.append("    ciudadResidencia = ?, ");
        sql.append("    correoElectronico = ?, ");
        sql.append("    numeroTelefonoMovil = ?, ");
        sql.append("    correoElectronicoConfirmado = ?, ");
        sql.append("    numeroTelefonoMovilConfirmado = ? ");
        sql.append("WHERE id = ?;");

        try (final PreparedStatement preparedStatement = this.getConnection().prepareStatement(sql.toString())) {

            preparedStatement.setObject(1, entity.getIdentificationType().getIdTypeId());
            preparedStatement.setObject(2, entity.getIdentificationNumber());
            preparedStatement.setString(3, entity.getFirstName());
            preparedStatement.setString(4, entity.getMiddleName());
            preparedStatement.setString(5, entity.getLastName());
            preparedStatement.setString(6, entity.getSecondLastName());
            preparedStatement.setObject(7, entity.getResidenceCity().getCityId());
            preparedStatement.setString(8, entity.getEmail());
            preparedStatement.setString(9, entity.getCellPhoneNumber());
            preparedStatement.setBoolean(10, entity.isEmailConfirmed());
            preparedStatement.setBoolean(11, entity.CellPhoneNumberConfirmed());
            

            preparedStatement.executeUpdate();

        } catch (final SQLException exception) {
            var userMessage = "Se ha presentado un problema tratando de actualizar la información de un usuario. Intente de nuevo.";
            var technicalMessage = "SQLException al actualizar Usuario: " + exception.getMessage();
            throw NoseException.create(exception, userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = "Se ha presentado un problema inesperado tratando de actualizar la información de un usuario.";
            var technicalMessage = "Excepción inesperada al actualizar Usuario: " + exception.getMessage();
            throw NoseException.create(exception, userMessage, technicalMessage);
        }
    }

    @Override
    public void delete(final UUID id) {
        final var sql = new StringBuilder();
        sql.append("DELETE FROM User ");
        sql.append("WHERE id = ?;");

        try (final PreparedStatement preparedStatement = this.getConnection().prepareStatement(sql.toString())) {
            preparedStatement.setObject(1, id);
            preparedStatement.executeUpdate();

        } catch (final SQLException exception) {
            var userMessage = "Se ha presentado un problema tratando de eliminar un usuario. Intente de nuevo.";
            var technicalMessage = "SQLException al eliminar Usuario: " + exception.getMessage();
            throw NoseException.create(exception, userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = "Se ha presentado un problema inesperado tratando de eliminar un usuario.";
            var technicalMessage = "Excepción inesperada al eliminar Usuario: " + exception.getMessage();
            throw NoseException.create(exception, userMessage, technicalMessage);
        }
    }
}