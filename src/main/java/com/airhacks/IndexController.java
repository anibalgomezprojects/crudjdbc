package com.airhacks;

import java.sql.ResultSet;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("rest")
public class IndexController<T> extends MySQL {

	public IndexController() {
		System.out.println("********* Cargando MySQL **********");
		load();
		connect();
	}
	
	@GET
	public JsonArray readGet() {
		JsonObjectBuilder row = Json.createObjectBuilder();
		JsonArrayBuilder col = Json.createArrayBuilder();
		try {
			ResultSet rs = read();
			while(rs.next()) {
				System.out.println(rs.getString("nombre") + "\n");
				row.add("id", rs.getString("id"));
				row.add("nombre", rs.getString("nombre"));
				row.add("apellidoPaterno", rs.getString("ap_pat"));
				row.add("apellidoMaterno", rs.getString("ap_mat"));
				col.add(row);
			}
		} catch (Exception e) {
			System.out.println("IndexController" + e);
		}
		return col.build();
	}
	
	@GET
	@Path("{id}")
	public JsonObject readByIdGet(@PathParam("id") int id) {
		System.out.println("id: " + id);
		JsonObjectBuilder row = Json.createObjectBuilder();
		try {
			ResultSet rs = readById(id);
			while(rs.next()) {
				System.out.println(rs.getString("nombre") + "\n");
				row.add("id", rs.getString("id"));
				row.add("nombre", rs.getString("nombre"));
				row.add("apellidoPaterno", rs.getString("ap_pat"));
				row.add("apellidoMaterno", rs.getString("ap_mat"));
			}
		} catch (Exception e) {
			System.out.println("IndexController" + e);
		}
		return row.build();
	}
	
	@Consumes("application/json")
	@Produces("application/json")
	@POST
	public String createPost(JsonObject a) {
		create(a.getString("nombre"), a.getString("apellidoPaterno"), a.getString("apellidoMaterno"));
		return "OK";
	}
	
	@Consumes("application/json")
	@Produces("application/json")
	@POST
	@Path("{id}")
	public JsonObject updatePost(@PathParam("id") int id, JsonObject a) {
		System.out.println("GET /" + id);
		System.out.println(a.toString());
		System.out.println("Nombre: " + a.getString("nombre"));
		update(Integer.parseInt(a.getString("id")), a.getString("nombre"), a.getString("apellidoPaterno"), a.getString("apellidoMaterno"));
		return a;
		
	}
	
	@DELETE
	@Path("{nombre}")
	public String deletePost(@PathParam("nombre") String nombre) {
		delete(nombre);
		return "OK";
	}
	
}
