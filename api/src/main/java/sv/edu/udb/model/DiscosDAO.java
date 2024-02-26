package sv.edu.udb.model;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import sv.edu.udb.model.Discos;

import java.sql.SQLException;

public class DiscosDAO extends AppConnection {

	ArtistasDAO artistaDAO = new ArtistasDAO();
	/**

	 * @param artista
	 * @throws SQLException
	 */
	public void insert(Discos disco) throws SQLException{
		connect();
		pstmt = conn.prepareStatement("insert into discos (nombre_disco, id_artista, numero_canciones, precio) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

		pstmt.setString(1, disco.getNombre());
		pstmt.setInt(2, disco.getArtistaId());
		pstmt.setInt(3, disco.getNumeroCanciones());
		pstmt.setInt(4, disco.getPrecio());
		pstmt.execute();

		//obteniendo el ultimo id generado
		ResultSet keys= pstmt.getGeneratedKeys();
		keys.next();
		int id = keys.getInt(1);

		disco.setId(id);
		close();
	}


	/**
	 * Update all fields from concepto table using its id
	 * @param concepto
	 * @throws SQLException
	 */
	public void update(Discos disco) throws SQLException{
		connect();
		pstmt = conn.prepareStatement("update discos set nombre_disco = ? , id_artista= ? , numero_canciones = ?, precio=? where id_disco = ?");
		pstmt.setString(1, disco.getNombre());
		pstmt.setInt(2, disco.getArtistaId());
		pstmt.setInt(3, disco.getNumeroCanciones());
		pstmt.setInt(4, disco.getPrecio());
		pstmt.setInt(5, disco.getId());
		pstmt.executeUpdate();
		close();
	}

	/**
	 * Deletes a Concepto by id
	 * @param id
	 */
	public void delete(int id) throws SQLException{
	    connect();
	    pstmt = conn.prepareStatement("delete from discos where id_disco = ?");
	    pstmt.setInt(1, id);
	    pstmt.execute();
	    close();
	}



	
	
	public List<Discos> findDiscosByArtistaId(int idArtista) throws SQLException {
        List<Discos> discos = new ArrayList<>();
        
        connect();
        pstmt = conn.prepareStatement("SELECT * FROM discos WHERE id_artista = ?");
        pstmt.setInt(1, idArtista);
        resultSet = pstmt.executeQuery();
        
        while (resultSet.next()) {
            Discos disco = new Discos();
            disco.setId(resultSet.getInt("id_disco"));
            disco.setNombre(resultSet.getString("nombre_disco"));
            disco.setArtistaId(resultSet.getInt("id_artista"));
            disco.setNumeroCanciones(resultSet.getInt("numero_canciones"));
            disco.setPrecio(resultSet.getInt("precio"));
            
                      
            discos.add(disco);
        }
        
        close();
        
        return discos;
    }

	/**
	 
	 */
	public ArrayList<Discos> findAll() throws SQLException {
	    connect();
	    stmt = conn.createStatement();
	    resultSet = stmt.executeQuery("SELECT d.id_disco, d.nombre_disco, d.id_artista, d.numero_canciones, d.precio, a.nombre_artista, a.descripcion " +
	                                    "FROM discos d " +
	                                    "INNER JOIN artistas a ON d.id_artista = a.id_artista");
	    ArrayList<Discos> discos = new ArrayList<>();

	    while (resultSet.next()) {
	        Discos disco = new Discos();
	        disco.setId(resultSet.getInt("id_disco"));
	        disco.setNombre(resultSet.getString("nombre_disco"));
	        disco.setArtistaId(resultSet.getInt("id_artista"));
	        disco.setNumeroCanciones(resultSet.getInt("numero_canciones"));
	        disco.setPrecio(resultSet.getInt("precio"));

	        // Recuperar información del artista asociado y establecerlo en el disco
	        Artistas artista = new Artistas();
	        artista.setId(resultSet.getInt("id_artista"));
	        artista.setNombre(resultSet.getString("nombre_artista"));
	        artista.setDescripcion(resultSet.getString("descripcion"));
	        disco.setArtista(artista);

	        discos.add(disco);
	    }

	    close();

	    return discos;
	}

	/**
	 * Find a concepto and returns it using the concepto id
	 * @param id
	 * @return
	 * @throws SQLException
	 */


	public Discos findById(int id) throws SQLException{

		Discos disco = null;

		connect();
		pstmt = conn.prepareStatement("select id_disco, nombre_disco, id_artista, numero_canciones, precio from discos where id_disco = ?");
		pstmt.setInt(1, id);

		resultSet = pstmt.executeQuery();

		while(resultSet.next()){
			disco = new Discos();
			disco.setId(resultSet.getInt(1));
			disco.setNombre(resultSet.getString(2));
			disco.setArtistaId(resultSet.getInt(3));
			disco.setNumeroCanciones(resultSet.getInt(4));
			disco.setPrecio(resultSet.getInt(5));
		}

		close();
		return disco;

	}
}


