package br.eti.deividferreira.jwt;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.Claims;
import org.eclipse.microprofile.jwt.JsonWebToken;

@Path("/books")
public class BookResource {
    @Inject
    JsonWebToken token;

    @Inject
    @Claim(standard = Claims.groups)
    Set<String> groups;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @RolesAllowed("Users")
    public String getBooks() {
        System.out.println(token.getIssuer());
        groups.forEach(g -> System.out.println(g));
        return "Sitio do Pica-Pau Amarelo";
    }
}