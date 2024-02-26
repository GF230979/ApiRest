package sv.edu.udb.api;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
@Path("hello")
public class HelloJersey {

	 @GET
	 @Produces(MediaType.APPLICATION_JSON)
	 public String getMessage(){
	 return "Hello World !! - Jersey 2";
	 }

	 @GET
	 @Path("mensajes")
	 @Produces(MediaType.APPLICATION_JSON)
	 public Response getMensajes(){

	 List<Mensaje> mensajes = new ArrayList();

	 for(int i=0; i<=10;i++){
	 Mensaje m = new Mensaje();
	 m.setNumero(i);
	 m.setMensaje("Mensaje # " + i);
	 mensajes.add(m);
	 }

	 return Response.status(200).entity(mensajes).build();
	 }

	 /**
	 * Esta es una clase de ejemplo, que vamos a instanciar
	 * dentro del for y mostraremos en el webservice
	 */
	 class Mensaje{
	 private int numero;
	 private String mensaje;


	 /**
	 * @return the numero
	 */
	 public int getNumero() {
	 return numero;
	 }
	 /**
	 * @param numero the numero to set
	 */
	 public void setNumero(int numero) {
	 this.numero = numero;
	 }
	 /**
	 * @return the mensaje
	 */
	 public String getMensaje() {
	 return mensaje;
	 }
	 /**
	  * @param mensaje the mensaje to set
	  */
	  public void setMensaje(String mensaje) {
	  this.mensaje = mensaje;
	  }
	  }
	 }