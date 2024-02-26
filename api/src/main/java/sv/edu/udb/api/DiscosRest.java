package sv.edu.udb.api;

import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import sv.edu.udb.model.DiscosDAO;
import sv.edu.udb.model.Discos;
import sv.edu.udb.model.ArtistasDAO;



@Path("discos")

public class DiscosRest {

	DiscosDAO discoDAO = new DiscosDAO();
	ArtistasDAO artistaDAO = new ArtistasDAO();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDiscos() throws SQLException{

		List<Discos> disco = discoDAO.findAll();
		return Response.status(200).entity(disco).build();
	}
	


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response getDiscoById(@PathParam("id") int id) throws
	SQLException{

		Discos disco = discoDAO.findById(id);
		if(disco == null){
			return Response.status(404)
					.header("Access-Control-Allow-Origin", "*")
					.entity("Concepto no encontrado").build();
		}
		return Response.status(200)
				.header("Access-Control-Allow-Origin", "*")
				.entity(disco).build();
	}

	
	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertDisco(
			 @FormParam("nombre") String nombre,
	            @FormParam("artistaId") int artistaId,
	            @FormParam("numeroCanciones") int numeroCanciones,
	            @FormParam("precio") int precio) throws SQLException {

		if(artistaDAO.findById(artistaId)==null){
			return Response.status(400)
					.header("Access-Control-Allow-Origin", "*")
					.entity("Categoria no corresponde a ninguna existencia").build();
		}
		
		Discos disco = new Discos();

		

		disco.setNombre(nombre);
		disco.setArtistaId(artistaId);
		disco.setNumeroCanciones(numeroCanciones);
		disco.setPrecio(precio);
		discoDAO.insert(disco);

		return Response.status(201)
				.header("Access-Control-Allow-Origin", "*")
				.entity(disco)
				.build();
	}

	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response eliminarConcepto(
			@PathParam("id") int id
			) throws SQLException{
		Discos disco = discoDAO.findById(id);
		if(disco == null){
			return Response.status(400)
					.entity("Concepto no corresponde a ninguna existencia")
					.header("Access-Control-Allow-Origin", "*")
					.build();
		}

		discoDAO.delete(id);

		return Response.status(200)
				.header("Access-Control-Allow-Origin", "*")
				.entity(disco)
				.build();
	}


	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response updateConcepto(
			 @PathParam("id") int id,
	            @FormParam("nombre") String nombre,
	            @FormParam("artistaId") int artistaId,
	            @FormParam("numeroCanciones") int numeroCanciones,
	            @FormParam("precio") int precio) throws SQLException{

		Discos disco = discoDAO.findById(id);

		if(disco == null){
			return Response.status(404)
					.header("Access-Control-Allow-Origin", "*")
					.entity("Concepto no corresponde a ninguna existencia")
					.build();
		}

		if(artistaDAO.findById(artistaId)==null){
			return Response.status(400)
					.header("Access-Control-Allow-Origin", "*")
					.entity("Categoria no corresponde a ninguna existencia")
					.build();
		}

		disco.setNombre(nombre);
        disco.setArtistaId(artistaId);
        disco.setNumeroCanciones(numeroCanciones);
        disco.setPrecio(precio);
        discoDAO.update(disco);

		return Response.status(200)
				.header("Access-Control-Allow-Origin", "*")
				.entity(disco)
				.build();
	}

}
