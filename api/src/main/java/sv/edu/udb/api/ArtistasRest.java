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
import sv.edu.udb.model.Artistas;
import sv.edu.udb.model.ArtistasDAO;
import sv.edu.udb.model.Discos;

import sv.edu.udb.model.DiscosDAO;



@Path("artistas")
public class ArtistasRest {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getArtistas() {
        try {
            ArtistasDAO artistasDAO = new ArtistasDAO();
            List<Artistas> artistas = artistasDAO.findAll();
            return Response.status(Response.Status.OK).entity(artistas).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al obtener artistas").build();
        }
    }
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByArtistasId(@PathParam("id") int id) throws
   SQLException{
    ArtistasDAO categoriasDAO = new ArtistasDAO();
    Artistas artista = categoriasDAO.findById(id);

    if(artista == null){
    return Response.status(404).build();
    }

    return Response.status(200).entity(artista).build();
    }

    
    
    @GET
    @Path("{id}/discos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDiscosByArtista(@PathParam("id") int id) throws SQLException {
        ArtistasDAO artistasDAO = new ArtistasDAO();
        Artistas artista = artistasDAO.findById(id);
        if (artista == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Artista no encontrado").build();
        }

        DiscosDAO discosDAO = new DiscosDAO();
        List<Discos> discos = discosDAO.findDiscosByArtistaId(id);
        
        for (Discos disco : discos) {
            disco.setArtista(artista);
        }

        return Response.status(Response.Status.OK).entity(discos).build();
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertArtista(
            @FormParam("nombre") String nombre,
            @FormParam("descripcion") String descripcion) throws SQLException {

        Artistas artista = new Artistas();
        artista.setNombre(nombre);
        artista.setDescripcion(descripcion);

        ArtistasDAO artistasDAO = new ArtistasDAO();
        artistasDAO.insert(artista);

        return Response.status(Response.Status.CREATED).entity(artista).build();
    }

    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateArtista(
            @PathParam("id") int id,
            @FormParam("nombre") String nombre,
            @FormParam("descripcion") String descripcion) throws SQLException {

        ArtistasDAO artistasDAO = new ArtistasDAO();
        Artistas artista = artistasDAO.findById(id);
        if (artista == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Artista no encontrado").build();
        }

        artista.setNombre(nombre);
        artista.setDescripcion(descripcion);
        artistasDAO.update(artista);

        return Response.status(Response.Status.OK).entity(artista).build();
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteArtista(@PathParam("id") int id) throws SQLException {
        ArtistasDAO artistasDAO = new ArtistasDAO();
        Artistas artista = artistasDAO.findById(id);
        if (artista == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Artista no encontrado").build();
        }

        artistasDAO.delete(id);

        return Response.status(Response.Status.OK).entity(artista).build();
    }
}
