package clasesBean;

public class Det_PedidoBean {
	
	private int idDetPedido; //PK
	
	private double precioVtaDetPed;
	private int cantidadDetPed; 
	
	private int idCabPedido; //FK
	private int idProducto; //FK

	
	public int getCantidadDetPed() {
		return cantidadDetPed;
	}
	
	public void setCantidadDetPed(int cantidadDetPed) {
		this.cantidadDetPed = cantidadDetPed;
	}
	
	public double getPrecioVtaDetPed() {
		return precioVtaDetPed;
	}
	
	public void setPrecioVtaDetPed(double precioVtaDetPed) {
		this.precioVtaDetPed = precioVtaDetPed;
	}
	
	public int getIdDetPed() {
		return idCabPedido;
	}
	
	public void setIdDetPed(int idDetPed) {
		this.idCabPedido = idDetPed;
	}
	
	public int getIdProducto() {
		return idProducto;
	}
	
	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
	
	public int getIdCabPedido() {
		return idCabPedido;
	}
	
	public void setIdCabPedido(int idCabPedido) {
		this.idCabPedido = idCabPedido;
	}

	public int getIdDetPedido() {
		return idDetPedido;
	}

	public void setIdDetPedido(int idDetPedido) {
		this.idDetPedido = idDetPedido;
	}
	
}
