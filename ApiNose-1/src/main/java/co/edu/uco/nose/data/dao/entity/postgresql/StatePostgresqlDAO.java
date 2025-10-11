package co.edu.uco.nose.data.dao.entity.postgresql;

import java.util.List;
import java.util.UUID;

import co.edu.uco.nose.data.dao.entity.SqlConnection;
import co.edu.uco.nose.data.dao.entity.StateDAO;
import co.edu.uco.nose.entity.StateEntity;

public class StatePostgresqlDAO extends SqlConnection implements StateDAO{

	public StatePostgresqlDAO(final java.sql.Connection connection) {
		super(connection);
	}
	
	@Override
	public List<StateEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StateEntity> findByFilter(StateEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StateEntity findById(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

}
