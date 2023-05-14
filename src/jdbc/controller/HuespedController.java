package jdbc.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jdbc.dao.HuespedDAO;
import jdbc.factory.ConnectionFactory;
import jdbc.modelo.Huesped;

public class HuespedController {
	
	private HuespedDAO huespedDAO;
	
	public HuespedController() {
		this.huespedDAO= new HuespedDAO(new ConnectionFactory().recuperarConexion());
	}

	public Integer guardarHuesped(Huesped huesped) throws SQLException {

		return huespedDAO.guardarHuesped(huesped);
	}

	public Integer modificar(Huesped huesped) throws SQLException {
		
		return huespedDAO.modificar(huesped);

	}

	public Integer eliminar(Integer id) throws SQLException {
		
		return huespedDAO.eliminar(id);
	}

	public List<Huesped> listarCoincidencia(String idapellido) throws SQLException {

		return huespedDAO.listarCoincidencia(idapellido);

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
