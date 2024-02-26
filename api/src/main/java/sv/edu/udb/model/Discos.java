package sv.edu.udb.model;
import sv.edu.udb.model.Artistas;

public class Discos {
	
	private int id;
	private String nombre;
	private int artistaId;
	private int numeroCanciones;
	private int precio;
	
	private Artistas artista;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getArtistaId() {
		return artistaId;
	}

	public void setArtistaId(int artistaId) {
		this.artistaId = artistaId;
	}

	public int getNumeroCanciones() {
		return numeroCanciones;
	}

	public void setNumeroCanciones(int numeroCanciones) {
		this.numeroCanciones = numeroCanciones;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}
	
	public Artistas getArtista() {
		return artista;
	}

	public void setArtista(Artistas artista) {
		this.artista = artista;
	}

	
	
}
