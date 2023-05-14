package jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jdbc.modelo.Reserva;

public class ReservaDAO {

	final private Connection con;

	public ReservaDAO(Connection con) {
		this.con = con;
	}

	public Integer guardar(Reserva reserva) throws SQLException {

		PreparedStatement statement = con.prepareStatement(
				"INSERT INTO RESERVAS " + "(fechaentrada, fechasalida, valor, formapago) " + "VALUES (?,?,?,?)",
				Statement.RETURN_GENERATED_KEYS);

		statement.setDate(1, reserva.getFechaEntrada());
		statement.setDate(2, reserva.getFechaSalida());
		statement.setString(3, reserva.getValor());
		statement.setString(4, reserva.getFormaPago());

		statement.execute();

		ResultSet resultSet = statement.getGeneratedKeys();
		Integer idDevuelto = 0;
		while (resultSet.next()) {
			System.out.println(String.format("fue insertado : %d", resultSet.getInt(1)));
			idDevuelto = resultSet.getInt(1);
		}
	
		return idDevuelto;
	}

	public int modificar(Reserva reserva) throws SQLException {

		PreparedStatement statement = con.prepareStatement("UPDATE RESERVAS SET "
				+ "fechaentrada= ?, fechasalida= ?, valor= ?, formapago= ? WHERE IDRESERVAS= ?;");

		statement.setDate(1, reserva.getFechaEntrada());
		statement.setDate(2, reserva.getFechaSalida());
		statement.setString(3, reserva.getValor());
		statement.setString(4, reserva.getFormaPago());
		statement.setInt(5, reserva.getIdReserva());

		statement.execute();

		int updateCount = statement.getUpdateCount();

		return updateCount;
	}

	public Integer eliminar(Integer id) throws SQLException {

		PreparedStatement statement = con.prepareStatement("DELETE FROM RESERVAS WHERE IDRESERVAS= ? ");

		statement.setInt(1, id);

		statement.execute();
		int updateCount = statement.getUpdateCount();

		return updateCount;
	}

	public List<Reserva> listarCoincidencia(String id) throws SQLException {

		PreparedStatement statement = con
				.prepareStatement("SELECT idreservas, fechaentrada, fechasalida, valor, formapago FROM RESERVAS "
						+ "WHERE idreservas= ? ;");

		statement.setInt(1, Integer.valueOf(id));

		statement.execute();

		List<Reserva> resultado = new ArrayList<>();

		ResultSet resultSet = statement.getResultSet();

		while (resultSet.next()) {
			Reserva fila = new Reserva(
					resultSet.getInt("idreservas"),
					resultSet.getDate("fechaentrada"),
					resultSet.getDate("fechasalida"),
					resultSet.getString("valor"),
					resultSet.getString("formapago") );

			resultado.add(fila);

		}

		
		return resultado;

	}

//	public List<Map<String, String>> listar() throws SQLException {
//		Connection con = new ConnectionFactory().recuperarConexion();
//		Statement statement = con.createStatement();
//
//		Boolean result = statement
//				.execute("SELECT idreservas, fechaentrada, fechasalida, valor, formapago FROM RESERVAS");
//
//		ResultSet resultSet = statement.getResultSet();
//
//		List<Map<String, String>> resultado = new ArrayList<>();
//
//		while (resultSet.next()) {
//			Map<String, String> fila = new HashMap<>();
//			fila.put("ID", String.valueOf(resultSet.getInt("idreservas")));
//			fila.put("FechaEntrada", String.valueOf(resultSet.getDate("fechaentrada")));
//			fila.put("FechaSalida", String.valueOf(resultSet.getDate("fechasalida")));
//			fila.put("Valor", String.valueOf(resultSet.getDouble("valor")));
//			fila.put("FormaPago", resultSet.getString("formapago"));
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
