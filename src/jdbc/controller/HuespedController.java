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
import jdbc.modelo.Huesped;
import views.Busqueda;

public class HuespedController {

	public Integer guardarHuesped(Huesped huesped) throws SQLException {

		Connection con = new ConnectionFactory().recuperarConexion();
		Statement statement = con.createStatement();
		statement.execute(
				"INSERT INTO HUESPEDES (nombre, apellido, fechaNacimiento, nacionalidad, telefono, idreserva) VALUES ('"
						+ huesped.getNombre() + "', '" + huesped.getApellido() + "', '" + huesped.getFechaNacimiento()
						+ "', '" + huesped.getNacionalidad() + "', '" + huesped.getTelefono() + "', '"
						+ huesped.getIdReserva() + "');",
				Statement.RETURN_GENERATED_KEYS);

		ResultSet resultSet = statement.getGeneratedKeys();
		Integer idDevuelto = 0;
		while (resultSet.next()) {
			System.out.println(String.format("El Huesped fue insertado : %d", resultSet.getInt(1)));
			idDevuelto = resultSet.getInt(1);
		}
		con.close();
		return idDevuelto;
	}

	public void modificar(Integer id, String nombre, String Aaellido, LocalDate fechaNacimiento, String nacionalidad,
			String telefono, String idReserva) {

	}

	public void eliminar(Integer id) {

	}

	public List<Map<String, String>> listar() throws SQLException {
		Connection con = new ConnectionFactory().recuperarConexion();

		Statement statement = con.createStatement();

		Boolean result = statement.execute(
				"SELECT id, nombre, apellido, fechaNacimiento, nacionalidad, telefono, idreserva FROM HUESPEDES where id");

		ResultSet resultSet = statement.getResultSet();

		List<Map<String, String>> resultado = new ArrayList<>();

		while (resultSet.next()) {
			Map<String, String> fila = new HashMap<>();
			fila.put("ID", String.valueOf(resultSet.getInt("id")));
			fila.put("Nombre", resultSet.getString("nombre"));
			fila.put("Apellido", resultSet.getString("apellido"));
			fila.put("FechaNacimiento", String.valueOf(resultSet.getDate("fechaNacimiento")));
			fila.put("Nacionalidad", resultSet.getString("nacionalidad"));
			fila.put("Telefono", resultSet.getString("telefono"));
			fila.put("IdReserva", String.valueOf(resultSet.getInt("idreserva")));

			resultado.add(fila);

		}

		con.close();
		return resultado;

	}

	public List<Map<String, String>> listarCoincidencia(String idapellido) throws SQLException {

		Connection con = new ConnectionFactory().recuperarConexion();

		Statement statement = con.createStatement();

		List<Map<String, String>> resultado = new ArrayList<>();

		

			// Integer idreserve=Integer.valueOf(idapellido);

			Boolean result = statement.execute(
					"SELECT id, nombre, apellido, fechaNacimiento, nacionalidad, telefono, idreserva FROM HUESPEDES where apellido='"
							+ idapellido + "';");

			ResultSet resultSet = statement.getResultSet();

			while (resultSet.next()) {
				Map<String, String> fila = new HashMap<>();
				fila.put("ID", String.valueOf(resultSet.getInt("id")));
				fila.put("Nombre", resultSet.getString("nombre"));
				fila.put("Apellido", resultSet.getString("apellido"));
				fila.put("FechaNacimiento", String.valueOf(resultSet.getDate("fechaNacimiento")));
				fila.put("Nacionalidad", resultSet.getString("nacionalidad"));
				fila.put("Telefono", resultSet.getString("telefono"));
				fila.put("IdReserva", String.valueOf(resultSet.getInt("idreserva")));

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
