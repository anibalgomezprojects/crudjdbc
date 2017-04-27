package com.airhacks;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface CRUD {

	public void create(String nombre, String apellidoPaterno, String apellidoMaterno);
	public ResultSet read() throws SQLException;
	public void update(int id, String nombre, String apellidoPaterno, String apellidoMaterno);
	public void delete(String nombre);
	public ResultSet readById(int id) throws SQLException;
	
}
