package clasesBean;

import java.sql.*;

//import java.util.*;

public class Cap_PedidoBean {
	
	private String estadoPed;
	private String numeroFacturaPed;
	private String numeroPed;
	
	private Date fechaPed;
	
	private long idCabPedido;
	private long idVendedor;
	private long idCliente;
	private long idTipoCobro;

	public String getEstadoPed() {
		return estadoPed;
	}
	
	public void setEstadoPed(String estadoPed) {
		this.estadoPed = estadoPed;
	}
	
	public Date getFechaPed() {
		return fechaPed;
	}
	
	public void setFechaPed(Date fechaPed) {
		this.fechaPed = fechaPed;
	}
	
	public String getNumeroFactura() {
		return numeroFacturaPed;
	}
	
	public void setNumeroFactura(String numeroFactura) {
		this.numeroFacturaPed = numeroFactura;
	}
	
	public String getNumeroPed() {
		return numeroPed;
	}
	
	public void setNumeroPed(String numeroPed) {
		this.numeroPed = numeroPed;
	}
	
	public long getIdCabPedido() {
		return idCabPedido;
	}
	
	public void setIdCabPedido(int idCabPedido) {
		this.idCabPedido = idCabPedido;
	}
	
	public long getIdVendedor() {
		return idVendedor;
	}
	
	public void setIdVendedor(int idVendedor) {
		this.idVendedor = idVendedor;
	}
	
	public long getIdCliente() {
		return idCliente;
	}
	
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	
	public long getIdTipoCobro() {
		return idTipoCobro;
	}
	
	public void setIdTipoCobro(int idTipoCobro) {
		this.idTipoCobro = idTipoCobro;
	}
}
