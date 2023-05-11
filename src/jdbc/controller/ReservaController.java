package jdbc.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jdbc.modelo.Reserva;

public class ReservaController {

	public void modificar(Integer id, LocalDate fechaEntrada, LocalDate fechaSalida, String formaPago) {

	}

	public void eliminar(Integer id) {

	}

	public List<Map<String,String>> listar() throws SQLException {
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/hotel?useTimeZone=true&serverTimeZone=UTC",
				"root", "Nikola1080");
		Statement statement = con.createStatement();

		Boolean result = statement.execute("SELECT idreserva, fechaentrada, fechasalida, valor, formapago FROM RESERVAS");

		ResultSet resultSet = statement.getResultSet();

		List<Map<String, String>> resultado = new ArrayList<>();

		while (resultSet.next()) {
			Map<String, String> fila = new HashMap<>();
			fila.put("ID", String.valueOf(resultSet.getInt("idreserva")));
			fila.put("FechaEntrada", String.valueOf(resultSet.getDate("fechaentrada")));
			fila.put("FechaSalida", String.valueOf(resultSet.getDate("fechasalida")));
			fila.put("Valor", String.valueOf(resultSet.getDouble("valor")));
			fila.put("FormaPago", resultSet.getString("formapago"));
			
			resultado.add(fila);
			
		}

		con.close();
		return resultado;

	}
}
