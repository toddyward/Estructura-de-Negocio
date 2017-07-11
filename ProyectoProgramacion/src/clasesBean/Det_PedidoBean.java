package clasesBean;

public class Det_PedidoBean {
	
	private long idDetPedido; //PK
	
	private double precioVtaDetPed;
	private long cantidadDetPed; 
	
	private long idCabPedido; //FK
	private long idProducto; //FK

	
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
		return idCabPedido;
	}
	
	public void setIdDetPed(int idDetPed) {
		this.idCabPedido = idDetPed;
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
