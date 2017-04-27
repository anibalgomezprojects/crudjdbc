package com.airhacks;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQL implements CRUD {

	private Statement stmt = null;
	private ResultSet rs = null;
	
	private PreparedStatement ps = null;
	protected Connection conn = null;
	
	public void load() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch(Exception e) {
			System.out.println("MYSQL: " + e.getMessage());
		}
	}
	
	public void connect() {
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/examen", "root", "");
		} catch(Exception e) {
			System.out.println("MYSQL: " + e.getMessage());
		}
		if(conn != null) {
			System.out.println("MYSQL: LA CONEXION FUE EXITOSA");
		}
	}

	@Override
	public void create(String nombre, String apellidoPaterno, String apellidoMaterno) {
		try {
			ps = conn.prepareStatement("INSERT INTO alumnos (nombre, ap_pat, ap_mat) VALUES (?, ?, ?)");
			ps.setString(1, nombre);
			ps.setString(2, apellidoPaterno);
			ps.setString(3, apellidoMaterno);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public ResultSet read() throws SQLException {
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM alumnos");
		} catch(SQLException e) {
			throw new SQLException(e);
		}
		return rs;
	}

	@Override
	public void update(int id, String nombre, String apellidoPaterno, String apellidoMaterno) {
		System.out.println("Actualizando /" + id + " Nombre: " + nombre + " ap_pat: " + apellidoPaterno + " ap_mat: " + apellidoPaterno);
		try {
			ps = conn.prepareStatement("UPDATE alumnos SET nombre = ?, ap_pat = ?, ap_mat = ? WHERE id = ?");
			//ps = conn.prepareStatement("UPDATE alumnos SET nombre = ? WHERE id = 1");
			ps.setString(1, nombre);
			ps.setString(2, apellidoPaterno);
			ps.setString(3, apellidoMaterno);
			ps.setInt(4, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void delete(String nombre) {
		try {
			ps = conn.prepareStatement("DELETE FROM alumnos WHERE nombre = ?");
			ps.setString(1, nombre);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public ResultSet readById(int id) throws SQLException {
		ps = conn.prepareStatement("SELECT * FROM alumnos WHERE id = ?");
		ps.setInt(1, id);
		rs = ps.executeQuery();
		return rs;
	}
	
	
}
