package tests;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import jdbc.controller.ReservaController;

public class TestReservaController {

	public static void main(String[] args) throws SQLException {
	List<Map<String, String>> chequeo = new ReservaController().listar();
	System.out.println(chequeo);
	
	}

}
