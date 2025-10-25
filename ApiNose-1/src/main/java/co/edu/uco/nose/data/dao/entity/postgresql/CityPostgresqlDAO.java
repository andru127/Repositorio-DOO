package co.edu.uco.nose.data.dao.entity.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.nose.crosscutting.exception.NoseException;
import co.edu.uco.nose.crosscutting.helpers.ObjectHelper;
import co.edu.uco.nose.crosscutting.helpers.TextHelper;
import co.edu.uco.nose.crosscutting.helpers.UUIDHelper;
import co.edu.uco.nose.data.dao.entity.CityDAO;
import co.edu.uco.nose.entity.SqlConnection;
import co.edu.uco.nose.entity.StateEntity;
import co.edu.uco.nose.entity.CityEntity;


public class CityPostgresqlDAO extends SqlConnection implements CityDAO {

	public CityPostgresqlDAO(final Connection connection) {
		super(connection);
	}
	
	@Override
	public List<CityEntity> findAll() {
		return findByFilter(new CityEntity());
	}

	@Override
	public List<CityEntity> findByFilter(CityEntity filterEntity) {
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
    
    private String createSentenceFindByFilter(final CityEntity filterEntity, final List<Object> parameterList) {
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
    
    private void createWhereClauseFindByFilter(final StringBuilder sql, final List<Object> parameterList, final CityEntity filterEntity) {
		var filterEntityValidated = ObjectHelper.getDefault(filterEntity, new CityEntity());
		final var conditions = new ArrayList<String>();
		
		addCondition(conditions, parameterList,
		!UUIDHelper.getUUIDHelper().isDefaultUUID(filterEntityValidated.getCityId()), "c.id = ? ",
		filterEntityValidated.getCityId());
		
		addCondition(conditions, parameterList,
		!UUIDHelper.getUUIDHelper().isDefaultUUID(filterEntityValidated.getState().getStateId()), "d.id = ? ",
		filterEntityValidated.getState().getStateId());
		
		addCondition(conditions, parameterList,
		!TextHelper.isEmptyWithTrim(filterEntityValidated.getName()), "c.nombre = ? ",
		filterEntityValidated.getName());
		
		
		
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
    
private List<CityEntity> executeSentenceFindByFilter(final PreparedStatement preparedStatement) {
		
		var listCity = new ArrayList<CityEntity>();
		
		try (var resultSet = preparedStatement.executeQuery()) {
			
			while (resultSet.next()) {
				

				var state = new StateEntity();
				state.setStateId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("departamentoId")));
				state.setName(resultSet.getString("nombre"));
				
				var city = new CityEntity();
	            city.setState(state);
	            city.setCityId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idCiudadResidencia")));
	            city.setName(resultSet.getString("nombreCiudadResidencia")); 
	            
	            listCity.add(city);
			
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
		return listCity;
		
	}
	

	@Override
	public CityEntity findById(UUID id) {
		return findByFilter(new CityEntity()).stream().findFirst().orElse(new CityEntity());
	}

}
