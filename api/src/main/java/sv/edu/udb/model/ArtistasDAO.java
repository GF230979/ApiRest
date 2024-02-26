package sv.edu.udb.model;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArtistasDAO extends AppConnection {

	/**

	 */
	public void insert(Artistas artista) throws SQLException{
		connect();
		pstmt = conn.prepareStatement("insert into artistas (id_artista, nombre_artista, descripcion) VALUES (?,?,?)");
		pstmt.setInt(1, artista.getId());
		pstmt.setString(2, artista.getNombre());
		pstmt.setString(3, artista.getDescripcion());
		pstmt.execute();
		close();
	}

	/**

	 */
	public void update(Artistas artista) throws SQLException{
		connect();
		pstmt = conn.prepareStatement("update artistas set  nombre_artista = ? , descripcion = ? where id_artista = ?");
		pstmt.setString(1, artista.getNombre());
		pstmt.setString(2, artista.getDescripcion());
		pstmt.setInt(3, artista.getId());
		pstmt.executeUpdate();
		close();
	}

	/**

	 */
	public void delete(int id) throws SQLException{
		connect();
		pstmt = conn.prepareStatement("delete from artistas where id_artista = ?");
		pstmt.setInt(1, id);
		pstmt.execute();
		close();
	}

	/**

	 */
	public ArrayList<Artistas> findAll() throws SQLException {
		connect();
		stmt = conn.createStatement();
		resultSet = stmt.executeQuery("SELECT id_artista, nombre_artista, descripcion  FROM artistas");
		ArrayList<Artistas> artistasList = new ArrayList<>();

		while (resultSet.next()) {
			Artistas artista = new Artistas();
			artista.setId(resultSet.getInt("id_artista"));
			artista.setNombre(resultSet.getString("nombre_artista"));
			artista.setDescripcion(resultSet.getString("descripcion"));

			artistasList.add(artista);
		}

		close();

		return artistasList;
	}

	/**

	 */

	public Artistas findById(int id) throws SQLException{

		Artistas artista = null;

		connect();
		pstmt = conn.prepareStatement("SELECT id_artista, nombre_artista, descripcion  FROM artistas  where id_artista = ?");
		pstmt.setInt(1, id);

		resultSet = pstmt.executeQuery();

		while(resultSet.next()){
			artista = new Artistas();
			artista.setId(resultSet.getInt(1));
			artista.setNombre(resultSet.getString(2));
			artista.setDescripcion(resultSet.getString(3));

		}

		close();
		return artista;
	}




}
