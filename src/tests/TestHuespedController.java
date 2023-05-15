package tests;

import java.sql.SQLException;
import java.util.List;

import jdbc.controller.HuespedController;
import jdbc.modelo.Huesped;

public class TestHuespedController {

	public static void main(String[] args) throws SQLException {
	List<Huesped> chequeo = new HuespedController().listarCoincidencia("rivas");
	System.out.println(chequeo);
	
	}

}
