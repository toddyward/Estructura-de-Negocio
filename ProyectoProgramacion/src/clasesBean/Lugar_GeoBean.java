package clasesBean;

public class Lugar_GeoBean {
	
	private String CodigoLugarGeo;
	private String DescripcionLugGeo;
	
	private long idLugarGeo;
	private long idLugarGeoPadre;
	
	public String getCodigoLugarGeo() {
		return CodigoLugarGeo;
	}
	
	public void setCodigoLugarGeo(String codigoLugarGeo) {
		CodigoLugarGeo = codigoLugarGeo;
	}
	
	public String getDescripcionLugGeo() {
		return DescripcionLugGeo;
	}
	
	public void setDescripcionLugGeo(String descripcionLugGeo) {
		DescripcionLugGeo = descripcionLugGeo;
	}
	
	public long getIdLugarGeo() {
		return idLugarGeo;
	}
	
	public void setIdLugarGeo(int idLugarGeo) {
		this.idLugarGeo = idLugarGeo;
	}
	
	public long getIdLugarGeo1() {
		return idLugarGeoPadre;
	}
	
	public void setIdLugarGeo1(int idLugarGeo1) {
		this.idLugarGeoPadre = idLugarGeo1;
	}

}
