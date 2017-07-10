package clasesBean;

public class Det_PedidoBean {
	
	private double precioVtaDetPed;
	
	private long cantidadDetPed;
	private long idVenPedido;
	private long idProducto;
	private long idCabPedido;
	
	public long getCantidadDetPed() {
		return cantidadDetPed;
	}
	
	public void setCantidadDetPed(int cantidadDetPed) {
		this.cantidadDetPed = cantidadDetPed;
	}
	
	public double getPrecioVtaDetPed() {
		return precioVtaDetPed;
	}
	
	public void setPrecioVtaDetPed(int precioVtaDetPed) {
		this.precioVtaDetPed = precioVtaDetPed;
	}
	
	public long getIdDetPed() {
		return idVenPedido;
	}
	
	public void setIdDetPed(int idDetPed) {
		this.idVenPedido = idDetPed;
	}
	
	public long getIdProducto() {
		return idProducto;
	}
	
	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
	
	public long getIdCabPedido() {
		return idCabPedido;
	}
	
	public void setIdCabPedido(int idCabPedido) {
		this.idCabPedido = idCabPedido;
	}
	
}
