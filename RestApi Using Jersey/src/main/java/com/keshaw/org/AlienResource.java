package com.keshaw.org;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("alien")
public class AlienResource {

	AlienReporsitory repo = new AlienReporsitory();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("GetAliens")
	public List<Alien> getAliens() throws SQLException {
		return repo.getAliens();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("GetAliensById/{id}")
	public Alien getAlienById(@PathParam("id") int id) throws SQLException {
		return repo.getAlienById(id);
		
	}
	
	@POST
	@Path("AddAlien")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String addAlienToRepo(Alien alien) throws SQLException {
		System.out.println("addAlienToRepo method called :::");
		String status = repo.addAlien(alien);
		return status;
	}
	
	@PUT
	@Path("updateAlien")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String updateAlienToRepo(Alien alien) throws SQLException {
		System.out.println("updateAlienToRepo method called :::");
		
		int idFromUser = alien.getId();
		Alien alienRepo = repo.getAlienById(idFromUser);
		int idFromRepo = alienRepo.getId();
		
		String status = "";
		if (idFromRepo == 0) {
			status = repo.addAlien(alien);
		} else {
			status = repo.updateAlien(alien);
		}
		return status;
	}
	
	@DELETE
	@Path("deleteAlien/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String deleteAlienFromRepo(@PathParam("id") int id) throws SQLException{
		String status = repo.deleteAlien(id);
		return status;
	}
	
	
	
	
	
	
	
}
