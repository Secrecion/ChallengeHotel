package jdbc.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	public Connection recuperarConexion() throws SQLException {
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/hotel?useTimeZone=true&serverTimeZone=UTC",
				"root","Nikola1080");
		
		return con;
	}

}
