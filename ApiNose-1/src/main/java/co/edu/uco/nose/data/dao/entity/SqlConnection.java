package co.edu.uco.nose.data.dao.entity;
import co.edu.uco.nose.crosscutting.helpers.SqlConnectionHelper;

import java.sql.Connection;

public abstract class SqlConnection {

    private Connection connection;

    protected SqlConnection(final Connection connection) {
        setConnection(connection);
    }

    public Connection getConnection() {
        return connection;
    }

    private void setConnection(final Connection connection) {
        SqlConnectionHelper.ensureConnectionIsOpen(connection);
        this.connection = connection;
    }


}