package co.edu.uco.nose.test;

import co.edu.uco.nose.data.dao.factory.postgresql.PostgresqlDAOFactory;

public class TestConnection {
    public static void main(String[] args) {
        try {
            PostgresqlDAOFactory factory = new PostgresqlDAOFactory();
            System.out.println("✅ Conexión establecida correctamente.");
        } catch (Exception e) {
            System.err.println("❌ Error al conectar con la base de datos:");
            e.printStackTrace();
        }
    }
}
