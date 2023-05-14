package jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jdbc.modelo.Huesped;

public class HuespedDAO {
	
	final private Connection con;

	public HuespedDAO(Connection con) {
		this.con = con;
	}
	
	public Integer guardarHuesped(Huesped huesped) throws SQLException {

		PreparedStatement statement = con.prepareStatement(
				"INSERT INTO HUESPEDES " + "(nombre, apellido, fechaNacimiento, nacionalidad, telefono, idreserva) "
						+ "VALUES ( ?, ?, ?, ?, ?, ?)",
				Statement.RETURN_GENERATED_KEYS);

		statement.setString(1, huesped.getNombre());
		statement.setString(2, huesped.getApellido());
		statement.setDate(3, huesped.getFechaNacimiento());
		statement.setString(4, huesped.getNacionalidad());
		statement.setString(5, huesped.getTelefono());
		statement.setInt(6, huesped.getIdReserva());

		statement.execute();

		ResultSet resultSet = statement.getGeneratedKeys();
		Integer idDevuelto = 0;
		while (resultSet.next()) {
			System.out.println(String.format("El Huesped fue insertado : %d", resultSet.getInt(1)));
			idDevuelto = resultSet.getInt(1);
		}
		
		return idDevuelto;
	}

	public Integer modificar(Huesped huesped) throws SQLException {
		
		PreparedStatement statement = con.prepareStatement("UPDATE HUESPEDES SET "
				+ "nombre =?, apellido=?, fechanacimiento=?, nacionalidad=?, telefono=? " + "WHERE ID= ?;");

		statement.setString(1, huesped.getNombre());
		statement.setString(2, huesped.getApellido());
		statement.setDate(3, huesped.getFechaNacimiento());
		statement.setString(4, huesped.getNacionalidad());
		statement.setString(5, huesped.getTelefono());
		statement.setInt(6, huesped.getId());

		statement.execute();

		int updateCount = statement.getUpdateCount();
	
		return updateCount;

	}

	public Integer eliminar(Integer id) throws SQLException {
		
		PreparedStatement statement = con.prepareStatement("DELETE FROM HUESPEDES WHERE ID=? ;");

		statement.setInt(1, id);

		statement.execute();
		int updateCount = statement.getUpdateCount();
		
		return updateCount;
	}

	public List<Huesped> listarCoincidencia(String idapellido) throws SQLException {

		PreparedStatement statement = con
				.prepareStatement("SELECT id, nombre, apellido, fechaNacimiento, nacionalidad, telefono, idreserva "
						+ "FROM HUESPEDES where apellido= ?;");

		statement.setString(1, idapellido);

		statement.execute();

		List<Huesped> resultado = new ArrayList<>();

		ResultSet resultSet = statement.getResultSet();

		while (resultSet.next()) {
			Huesped fila = new Huesped(
					resultSet.getInt("id"),
					resultSet.getString("nombre"),
					resultSet.getString("apellido"),
					resultSet.getDate("fechaNacimiento"),
					resultSet.getString("nacionalidad"),
					resultSet.getString("telefono"),
					resultSet.getInt("idreserva"));
			
			resultado.add(fila);
		}

		
		return resultado;

	}
//	public List<Map<String, String>> listar() throws SQLException {
//		Connection con = new ConnectionFactory().recuperarConexion();
//
//		Statement statement = con.createStatement();
//
//		Boolean result = statement.execute(
//				"SELECT id, nombre, apellido, fechaNacimiento, nacionalidad, telefono, idreserva FROM HUESPEDES where id");
//
//		ResultSet resultSet = statement.getResultSet();
//
//		List<Map<String, String>> resultado = new ArrayList<>();
//
//		while (resultSet.next()) {
//			Map<String, String> fila = new HashMap<>();
//			fila.put("ID", String.valueOf(resultSet.getInt("id")));
//			fila.put("Nombre", resultSet.getString("nombre"));
//			fila.put("Apellido", resultSet.getString("apellido"));
//			fila.put("FechaNacimiento", String.valueOf(resultSet.getDate("fechaNacimiento")));
//			fila.put("Nacionalidad", resultSet.getString("nacionalidad"));
//			fila.put("Telefono", resultSet.getString("telefono"));
//			fila.put("IdReserva", String.valueOf(resultSet.getInt("idreserva")));
//
//			resultado.add(fila);
//
//		}
//
//		con.close();
//		return resultado;
//
//	}


}
