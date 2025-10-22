package co.edu.uco.nose.data.dao.entity.postgresql;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.nose.crosscutting.exception.NoseException;
import co.edu.uco.nose.crosscutting.helpers.ObjectHelper;
import co.edu.uco.nose.crosscutting.helpers.TextHelper;
import co.edu.uco.nose.crosscutting.helpers.UUIDHelper;
import co.edu.uco.nose.data.dao.entity.SqlConnection;
import co.edu.uco.nose.data.dao.entity.StateDAO;
import co.edu.uco.nose.entity.CountryEntity;
import co.edu.uco.nose.entity.StateEntity;

public class StatePostgresqlDAO extends SqlConnection implements StateDAO{

	public StatePostgresqlDAO(final java.sql.Connection connection) {
		super(connection);
	}
	
	@Override
	public List<StateEntity> findAll() {
		return findByFilter(new StateEntity());
	}
	

	@Override
	public List<StateEntity> findByFilter(final StateEntity filterEntity) {
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
	
	private String createSentenceFindByFilter(final StateEntity filterEntity, final List<Object> parameterList) {
		final var sql = new StringBuilder();
		
		sql.append("SELECT ");
        sql.append("u.id, ");
        sql.append("ti.id AS idTipoIdentificacion, ");
        sql.append("ti.nombre AS nombreTipoIdentificacion, ");
        sql.append("u.numeroIdentificacion, ");
        sql.append("u.primerNombre, ");
        sql.append("u.segundoNombre, ");
        sql.append("u.primerApellido, ");
        sql.append("u.segundoApellido, ");
        sql.append("c.id AS idCiudadResidencia, ");
        sql.append("c.nombre AS nombreCiudadResidencia, ");
        sql.append("d.id AS idDepartamentoCiudadResidencia, ");
        sql.append("d.nombre AS nombreDepartamentoCiudadResidencia, ");
        sql.append("p.id AS idPaisDepartamentoCiudadResidencia, ");
        sql.append("p.nombre AS nombrePaisDepartamentoCiudadResidencia, ");
        sql.append("u.correoElectronico, ");
        sql.append("u.numeroTelefonoMovil, ");
        sql.append("u.correoElectronicoConfirmado, ");
        sql.append("u.numeroTelefonoMovilConfirmado ");

        sql.append("FROM Usario AS u ");
        sql.append("INNER JOIN TipoIdentificacion AS ti ON u.tipoIdentificacion = ti.id ");
        sql.append("INNER JOIN Ciudad AS c ON u.ciudadResidencia = c.id ");
        sql.append("INNER JOIN Departamento AS d ON c.departamento = d.id ");
        sql.append("INNER JOIN Pais AS p ON d.pais = p.id ");
        
        createWhereClauseFindByFilter(sql, parameterList, filterEntity);
        
        return sql.toString();
	}

	
	private void createWhereClauseFindByFilter(final StringBuilder sql, final List<Object> parameterList, final StateEntity filterEntity) {
		var filterEntityValidated = ObjectHelper.getDefault(filterEntity, new StateEntity());
		final var conditions = new ArrayList<String>();
		
		addCondition(conditions, parameterList, 
		!UUIDHelper.getUUIDHelper().isDefaultUUID(filterEntityValidated.getStateId()), "d.departamentoId = ?", 
		filterEntityValidated.getStateId()); 
		
		
		addCondition(conditions, parameterList, 
		!TextHelper.isEmptyWithTrim(filterEntityValidated.getName()), "d.nombre = ?", 
		filterEntityValidated.getName());
		
		
		addCondition(conditions, parameterList, 
		!UUIDHelper.getUUIDHelper().isDefaultUUID(filterEntityValidated.getCountry().getCountryId()), "d.pais = ?", 
		filterEntityValidated.getCountry().getCountryId());
	
		if (!conditions.isEmpty()) {
			sql.append(" WHERE ");
			sql.append(String.join(" AND ", conditions));
		}
	
	}
	
	private void addCondition(final List<String> conditions, final List<Object> parameterList, final boolean condition,
			final String clause, final Object value) {
		if (condition) {
			conditions.add(clause);
			parameterList.add(value);
		}
	}
	
	private List<StateEntity> executeSentenceFindByFilter(final PreparedStatement preparedStatement) {
		
		var listState = new ArrayList<StateEntity>();
		
		try (var resultSet = preparedStatement.executeQuery()) {
			
			while (resultSet.next()) {
				var country = new CountryEntity();
				country.setCountryId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idPaisDepartamentoCiudadResidencia")));
				country.setName(resultSet.getString("nombrePaisDepartamentoCiudadResidencia"));
				
				var state = new StateEntity();
				state.setStateId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("departamentoId")));
				state.setName(resultSet.getString("nombre"));
				state.setCountry(country);
				
				listState.add(state);
			
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
		return listState;
		
	}
	
	@Override
	public StateEntity findById(UUID id) {
		return findByFilter(new StateEntity(id)).stream().findFirst().orElse(new StateEntity());
	}

}
