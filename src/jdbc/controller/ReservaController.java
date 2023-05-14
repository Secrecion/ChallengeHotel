
package jdbc.controller;

import java.sql.SQLException;
import java.util.List;

import jdbc.dao.ReservaDAO;
import jdbc.factory.ConnectionFactory;
import jdbc.modelo.Reserva;

public class ReservaController {

		private ReservaDAO reservaDAO;

		public ReservaController() {
			this.reservaDAO = new ReservaDAO(new ConnectionFactory().recuperarConexion());
		}

	public Integer guardarReserva(Reserva reserva) throws SQLException {

		return reservaDAO.guardar(reserva);
	}

	public int modificar(Reserva reserva) throws SQLException {
	
		return reservaDAO.modificar(reserva);
	}
	

	public Integer eliminar(Integer id) throws SQLException {
		
		return reservaDAO.eliminar(id);
	}
	

	public List<Reserva> listarCoincidencia(String id) throws SQLException {
		
		return reservaDAO.listarCoincidencia(id);
	}
}
