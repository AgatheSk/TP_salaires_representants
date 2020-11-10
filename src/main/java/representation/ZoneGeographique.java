package representation;

public class ZoneGeographique {
	
	private final int numero;
	
	private final String nom;
	
	private float indemniteRepas;

	public ZoneGeographique(int numero, String nom) {
		this.numero = numero;
		this.nom = nom;
	}

	@Override
	public String toString() {
		return "ZoneGeographique{" + "numero=" + numero + ", nom=" + nom + ", indemniteRepas=" + indemniteRepas + '}';
	}
		
	
	public int getNumero() {
		return numero;
	}

	public String getNom() {
		return nom;
	}

	public float getIndemniteRepas() {
		return indemniteRepas;
	}

	public void setIndemniteRepas(float indemniteRepas) {
		this.indemniteRepas = indemniteRepas;
	}
	
	
	
}
