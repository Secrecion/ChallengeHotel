
package jdbc.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jdbc.factory.ConnectionFactory;
import jdbc.modelo.Reserva;

public class ReservaController {

	public Integer guardarReserva(Reserva reserva) throws SQLException {

		Connection con = new ConnectionFactory().recuperarConexion();
		Statement statement = con.createStatement();
		statement.execute("INSERT INTO RESERVAS (fechaentrada, fechasalida, valor, formapago) VALUES ('"
				+ reserva.getFechaEntrada() + "', '" + reserva.getFechaSalida() + "', '" + reserva.getValor() + "', '"
				+ reserva.getFormaPago() + "');", Statement.RETURN_GENERATED_KEYS);

		ResultSet resultSet = statement.getGeneratedKeys();
		Integer idDevuelto = 0;
		while (resultSet.next()) {
			System.out.println(String.format("fue insertado : %d", resultSet.getInt(1)));
			idDevuelto = resultSet.getInt(1);
		}
		con.close();
		return idDevuelto;
	}

	public int modificar(Reserva reserva) throws SQLException {
		Connection con = new ConnectionFactory().recuperarConexion();
		Statement statement = con.createStatement();
		System.out.println("inicia conexion");
		statement.execute("UPDATE RESERVAS SET fechaentrada='" + reserva.getFechaEntrada() + "', fechasalida='"
				+ reserva.getFechaSalida() + "', valor='" + reserva.getValor() + "', formapago='" + reserva.getFormaPago()
				+ "' WHERE IDRESERVAS=" + reserva.getIdReserva() + " ;");

		int updateCount = statement.getUpdateCount();
		con.close();
		return updateCount;
	}

	public Integer eliminar(Integer id) throws SQLException {
		Connection con = new ConnectionFactory().recuperarConexion();
		Statement statement = con.createStatement();

		statement.execute("DELETE FROM RESERVAS WHERE IDRESERVAS=" + id + ";");
		int updateCount = statement.getUpdateCount();
		con.close();
		return updateCount;
	}

	public List<Map<String, String>> listar() throws SQLException {
		Connection con = new ConnectionFactory().recuperarConexion();
		Statement statement = con.createStatement();

		Boolean result = statement
				.execute("SELECT idreservas, fechaentrada, fechasalida, valor, formapago FROM RESERVAS");

		ResultSet resultSet = statement.getResultSet();

		List<Map<String, String>> resultado = new ArrayList<>();

		while (resultSet.next()) {
			Map<String, String> fila = new HashMap<>();
			fila.put("ID", String.valueOf(resultSet.getInt("idreservas")));
			fila.put("FechaEntrada", String.valueOf(resultSet.getDate("fechaentrada")));
			fila.put("FechaSalida", String.valueOf(resultSet.getDate("fechasalida")));
			fila.put("Valor", String.valueOf(resultSet.getDouble("valor")));
			fila.put("FormaPago", resultSet.getString("formapago"));

			resultado.add(fila);

		}

		con.close();
		return resultado;

	}

	public List<Map<String, String>> listarCoincidencia(String id) throws SQLException {
		Connection con = new ConnectionFactory().recuperarConexion();
		Statement statement = con.createStatement();

		List<Map<String, String>> resultado = new ArrayList<>();

		Boolean result = statement.execute(
				"SELECT idreservas, fechaentrada, fechasalida, valor, formapago FROM RESERVAS where idreservas=" + id
						+ ";");

		ResultSet resultSet = statement.getResultSet();

		while (resultSet.next()) {
			Map<String, String> fila = new HashMap<>();
			fila.put("ID", String.valueOf(resultSet.getInt("idreservas")));
			fila.put("FechaEntrada", String.valueOf(resultSet.getDate("fechaentrada")));
			fila.put("FechaSalida", String.valueOf(resultSet.getDate("fechasalida")));
			fila.put("Valor", String.valueOf(resultSet.getDouble("valor")));
			fila.put("FormaPago", resultSet.getString("formapago"));

			resultado.add(fila);

		}

		con.close();
		return resultado;

	}

	public static boolean isNumeric(String s) {
		if (s == null || s.equals("")) {
			return false;
		}

		return s.chars().allMatch(Character::isDigit);
	}
}
