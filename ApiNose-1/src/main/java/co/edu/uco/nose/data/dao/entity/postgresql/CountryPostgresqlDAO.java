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
import co.edu.uco.nose.data.dao.entity.CountryDAO;
import co.edu.uco.nose.data.dao.entity.SqlConnection;
import co.edu.uco.nose.entity.CityEntity;
import co.edu.uco.nose.entity.CountryEntity;
import co.edu.uco.nose.entity.IdTypeEntity;
import co.edu.uco.nose.entity.StateEntity;
import co.edu.uco.nose.entity.UserEntity;

public class CountryPostgresqlDAO extends SqlConnection implements CountryDAO{

	public CountryPostgresqlDAO(final Connection connection) {
		super(connection);
	}
	
	@Override
	public List<CountryEntity> findAll() {
		return findByFilter(new CountryEntity());
	}

	@Override
	public List<CountryEntity> findByFilter(final CountryEntity filterEntity) {
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
    
    private String createSentenceFindByFilter(final CountryEntity filterEntity, final List<Object> parameterList) {
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
    
    private void createWhereClauseFindByFilter(final StringBuilder sql, final List<Object> parameterList, final CountryEntity filterEntity) {
		var filterEntityValidated = ObjectHelper.getDefault(filterEntity, new CountryEntity());
		final var conditions = new ArrayList<String>();
		
		addCondition(conditions, parameterList,
		!UUIDHelper.getUUIDHelper().isDefaultUUID(filterEntityValidated.getCountryId()), "p.id = ? ",
		filterEntityValidated.getCountryId());
		
		addCondition(conditions, parameterList,
		!TextHelper.isEmptyWithTrim(filterEntityValidated.getName()), "p.nombre = ? ",
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
    
private List<CountryEntity> executeSentenceFindByFilter(final PreparedStatement preparedStatement) {
		
		var listCountry = new ArrayList<CountryEntity>();
		
		try (var resultSet = preparedStatement.executeQuery()) {
			
			while (resultSet.next()) {
				var country = new CountryEntity();
				country.setCountryId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idPaisDepartamentoCiudadResidencia")));
				country.setName(resultSet.getString("nombrePaisDepartamentoCiudadResidencia"));
				
				listCountry.add(country);
			
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
		return listCountry;
		
	}
	

	@Override
	public CountryEntity findById(UUID id) {
		return findByFilter(new CountryEntity()).stream().findFirst().orElse(new CountryEntity()); 
	}

}
