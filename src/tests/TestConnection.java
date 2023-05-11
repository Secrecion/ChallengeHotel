package tests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnection {
	
public static void main(String[] args) throws SQLException {
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost/hotel?useTimeZone=true&serverTimeZone=UTC",
			"root","Nikola1080");
	System.out.println("Se logro la conexion");
	con.close();
}
}
