package tests;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import jdbc.controller.HuespedController;

public class TestHuespedController {

	public static void main(String[] args) throws SQLException {
	List<Map<String, String>> chequeo = new HuespedController().listar();
	System.out.println(chequeo);
	
	}

}
