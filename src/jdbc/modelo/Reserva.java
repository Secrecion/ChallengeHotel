package jdbc.modelo;

import java.sql.Date;

public class Reserva {
Integer idReserva;
Date fechaEntrada;
Date fechaSalida;
String valor;
String formaPago;

public Reserva(Integer idReserva, Date fechaEntrada, Date fechaSalida, String valor, String formaPago) {
	this.idReserva = idReserva;
	this.fechaEntrada = fechaEntrada;
	this.fechaSalida = fechaSalida;
	this.valor = valor;
	this.formaPago = formaPago;
}

public Reserva(Date fechaEntrada, Date fechaSalida, String valor, String formaPago) {
	this.fechaEntrada = fechaEntrada;
	this.fechaSalida = fechaSalida;
	this.valor = valor;
	this.formaPago = formaPago;
}

public Integer getIdReserva() {
	return idReserva;
}

public void setIdReserva(Integer idReserva) {
	this.idReserva = idReserva;
}

public Date getFechaEntrada() {
	return fechaEntrada;
}

public void setFechaEntrada(Date fechaEntrada) {
	this.fechaEntrada = fechaEntrada;
}

public Date getFechaSalida() {
	return fechaSalida;
}

public void setFechaSalida(Date fechaSalida) {
	this.fechaSalida = fechaSalida;
}

public String getValor() {
	return valor;
}

public void setValor(String valor) {
	this.valor = valor;
}

public String getFormaPago() {
	return formaPago;
}

public void setFormaPago(String formaPago) {
	this.formaPago = formaPago;
}

}
