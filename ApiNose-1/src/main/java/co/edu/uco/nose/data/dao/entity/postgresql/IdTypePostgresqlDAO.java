package co.edu.uco.nose.data.dao.entity.postgresql;

import java.util.List;
import java.util.UUID;

import co.edu.uco.nose.data.dao.entity.IdTypeDAO;
import co.edu.uco.nose.data.dao.entity.SqlConnection;
import co.edu.uco.nose.entity.IdTypeEntity;

public class IdTypePostgresqlDAO extends SqlConnection implements IdTypeDAO {

	public IdTypePostgresqlDAO(final java.sql.Connection connection) {
		super(connection);
	}
	
	@Override
	public List<IdTypeEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IdTypeEntity> findByFilter(IdTypeEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IdTypeEntity findById(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

}
