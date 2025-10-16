package co.edu.uco.nose.data.dao.entity.postgresql;

import co.edu.uco.nose.crosscutting.exception.NoseException;
import co.edu.uco.nose.crosscutting.helpers.SqlConnectionHelper;
import co.edu.uco.nose.crosscutting.helpers.UUIDHelper;
import co.edu.uco.nose.data.dao.entity.SqlConnection;
import co.edu.uco.nose.data.dao.entity.UserDAO;
import co.edu.uco.nose.entity.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        sql.append("primerApellido, segundoApellido, ciudadResidencia, correoElectronico, ");
        sql.append("numeroTelefonoMovil, correoElectronicoConfirmado, numeroTelefonoMovilConfirmado) ");
        sql.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");

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
        return new ArrayList<>();    
    }

    @Override
    public List<UserEntity> findByFilter(final UserEntity filterEntity) {
    	return new ArrayList<>(); 
    }

    @Override
    public UserEntity findById(final UUID id) {
        final var sql = new StringBuilder();
        
        var user = new UserEntity();
        
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

        sql.append("FROM Usuario AS u ");
        sql.append("INNER JOIN TipoIdentificacion AS ti ON u.tipoIdentificacion = ti.id ");
        sql.append("INNER JOIN Ciudad AS c ON u.ciudadResidencia = c.id ");
        sql.append("INNER JOIN Departamento AS d ON c.departamento = d.id ");
        sql.append("INNER JOIN Pais AS p ON d.pais = p.id ");
        sql.append("WHERE u.id = ?;");

       
        
        try (final PreparedStatement preparedStatement = this.getConnection().prepareStatement(sql.toString())) {
            preparedStatement.setObject(1, id);

            try (var resultSet = preparedStatement.executeQuery()) {
                
            	if (resultSet.next()) {
            		var idType = new IdTypeEntity();
                	idType.setIdTypeId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idTipoIdentificacion")));
                    idType.setName(resultSet.getString("nombreTipoIdentificacion"));
                    
            		var country = new CountryEntity();
                	country.setCountryId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idPaisDepartamentoCiudadResidencia")));
                    country.setName(resultSet.getString("nombrePaisDepartamentoCiudadResidencia"));
                    
                    var state = new StateEntity();
                    state.setCountry(country);
                    state.setStateId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idDepartamentoCiudadResidencia")));
                    state.setName(resultSet.getString("nombreDepartamentoCiudadResidencia"));
                    
                    var city = new CityEntity();
                    city.setState(state);
                    city.setCityId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("idCiudadResidencia")));
                    city.setName(resultSet.getString("nombreCiudadResidencia"));
                    
                    user.setUserId(UUIDHelper.getUUIDHelper().getFromString(resultSet.getString("id")));
                    user.setIdentificationType(idType);
                    user.setFirstName(resultSet.getString("primerNombre"));
                    user.setMiddleName(resultSet.getString("segundoNombre"));
                    user.setLastName(resultSet.getString("primerApellido"));
                    user.setSecondLastName(resultSet.getString("segundoApellido"));
                    user.setResidenceCity(city);
                    user.setEmail(resultSet.getString("correoElectronico"));
                    user.setCellPhoneNumber(resultSet.getString("numeroTelefonoMovil"));
                    user.setEmailConfirmed(resultSet.getBoolean("confirmacionCorreoElectronico"));
                    user.setCellPhoneNumberConfirmed(resultSet.getBoolean("confirmacionNumeroTelefonoMovil"));
                    
                }
            }

            

        } catch (final SQLException exception) {
            var userMessage = "Se ha presentado un problema tratando de consultar la información de un usuario. Intente de nuevo.";
            var technicalMessage = "SQLException al consultar Usuario: " + exception.getMessage();
            throw NoseException.create(exception, userMessage, technicalMessage);
        } catch (final Exception exception) {
            var userMessage = "Se ha presentado un problema inesperado tratando de consultar la información de un usuario.";
            var technicalMessage = "Excepción inesperada al consultar Usuario: " + exception.getMessage();
            throw NoseException.create(exception, userMessage, technicalMessage);
        }
        
        return user;
    }

    @Override
    public void update(final UserEntity entity) {
        SqlConnectionHelper.ensureTransactionIsStarted(getConnection());

        final var sql = new StringBuilder();
        sql.append("UPDATE Usuario ");
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
        sql.append("DELETE FROM Usuario ");
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