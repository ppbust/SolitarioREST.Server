package upm.miw.solitaire.controllers.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import upm.miw.solitaire.controllers.ejb.LoginEjb;
import upm.miw.solitaire.models.entities.User;

import com.sun.jersey.core.util.Base64;

@Path("/user")
public class LoginResource {
    @Context
    private HttpHeaders hh; // inyección de objetos

    private String user() {
        String data = hh.getRequestHeader("authorization").get(0);
        byte[] lap = Base64.decode(data.substring(6));
        return new String(lap).split(":")[0];
    }

    private String password() {
        String data = hh.getRequestHeader("authorization").get(0);
        byte[] lap = Base64.decode(data.substring(6));
        return new String(lap).split(":")[1];
    }

    @Path("/{nick}")
    @GET
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public User userGet(@PathParam("nick")
    String nick) {
        LoginEjb eaE = new LoginEjb();
        if (!user().equals(nick))
            return null;
        else {
            User usr = eaE.login(nick, this.password());
            return usr;
        }
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response userPost(User user) {
        LoginEjb eaE = new LoginEjb();

        if (!eaE.register(user)) {
            return Response.status(400).build();
        } else {
            Logger.getLogger(LoginResource.class).info("registrar correcto: " + user.getNick());
            return Response.status(201).build();
        }
    }
}
