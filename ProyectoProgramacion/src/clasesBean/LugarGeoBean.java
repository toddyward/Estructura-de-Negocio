package clasesBean;

public class LugarGeoBean {
	
	private String CodigoLugarGeo;
	private String DescripcionLugGeo;
	
	private int idLugarGeo;
	private int idLugarGeoPadre;
	
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
	
	public int getIdLugarGeo() {
		return idLugarGeo;
	}
	
	public void setIdLugarGeo(int idLugarGeo) {
		this.idLugarGeo = idLugarGeo;
	}
	public int getIdLugarGeo1() {
		return idLugarGeoPadre;
	}
	
	public void setIdLugarGeo1(int idLugarGeo1) {
		this.idLugarGeoPadre = idLugarGeo1;
	}

}
