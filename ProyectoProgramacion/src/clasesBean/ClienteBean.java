package clasesBean;

public class ClienteBean extends PersonaBean {
	
	private String correoCli;
	private String direccionCli;
	private String telefonoCli;
	
	private long idCliente;
	private long idLugarGeo;
	
	public String getCorreoElecCli() {
		return correoCli;
	}
	
	public void setCorreoElecCli(String correoElecCli) {
		this.correoCli = correoElecCli;
	}
	
	public String getDireccionCli() {
		return direccionCli;
	}
	
	public void setDireccionCli(String direccionCli) {
		this.direccionCli = direccionCli;
	}
	
	public String getTelefonoCli() {
		return telefonoCli;
	}
	
	public void setTelefonoCli(String telefonoCli) {
		this.telefonoCli = telefonoCli;
	}
	
	public long getIdCliente() {
		return idCliente;
	}
	
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	
	public long getIdLugarGeo() {
		return idLugarGeo;
	}
	
	public void setIdLugarGeo(int idLugarGeo) {
		this.idLugarGeo = idLugarGeo;
	}

}
