package clasesBean;

public class ProductoBean {
	
	private String codigoPro;
	private String descripcionPro;
	
	private long comprometidoPro;
	private long saldoPro;
	private long idProducto;
	private long idTipoProducto;
	
	private double precioUnitPro;
	
	public String getCodigoPro() {
		return codigoPro;
	}
	
	public void setCodigoPro(String codigoPro) {
		this.codigoPro = codigoPro;
	}
	
	public long getComprometidoPro() {
		return comprometidoPro;
	}
	
	public void setComprometidoPro(int comprometidoPro) {
		this.comprometidoPro = comprometidoPro;
	}
	
	public String getDescripcionPro() {
		return descripcionPro;
	}
	
	public void setDescripcionPro(String descripcionPro) {
		this.descripcionPro = descripcionPro;
	}
	
	public double getPrecioUnitPro() {
		return precioUnitPro;
	}
	
	public void setPrecioUnitPro(double precioUnitPro) {
		this.precioUnitPro = precioUnitPro;
	}
	
	public long getSaldoPro() {
		return saldoPro;
	}
	
	public void setSaldoPro(int saldoPro) {
		this.saldoPro = saldoPro;
	}
	
	public long getIdProducto() {
		return idProducto;
	}
	
	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
	
	public long getIdTipoProducto() {
		return idTipoProducto;
	}
	
	public void setIdTipoProducto(int idTipoProducto) {
		this.idTipoProducto = idTipoProducto;
	}
	
}
