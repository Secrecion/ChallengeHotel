package tests;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import jdbc.controller.ReservaController;
import jdbc.modelo.Reserva;

public class TestReservaController {

	public static void main(String[] args) throws SQLException {
	List<Reserva> chequeo = new ReservaController().listarCoincidencia("1");
	System.out.println(chequeo);
	
	}

}
