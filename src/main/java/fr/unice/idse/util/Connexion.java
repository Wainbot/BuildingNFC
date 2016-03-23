package fr.unice.idse.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connexion {
	private String dbPath = "";
	private String user = "";
	private String password = "";
	private Connection connect = null;
	private Statement statement = null;
	
	
	/**
	 * Constructeur permettant d'initialiser les donnée de connexion 
	 * @param dbPath a database url
	 * @param user the database user on whose behalf the connection is being made
	 * @param password the user's password
	 * @author Damien Clemenceau
	 */
	public Connexion(String dbPath, String user, String password) {
		this.dbPath = dbPath;
		this.user = user;
		this.password = password;
	}
	
	/**
	 * This method create a connection with the db
	 * @author Damien Clemenceau
	 */
	public void connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connect = DriverManager.getConnection("jdbc:mysql://" + dbPath, user, password);
			statement = connect.createStatement();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method close the connection with the database
	 * @author Damien Clemenceau
	 */
	public void close() {
		try {
			connect.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method allow to query the database and return the data
	 * @param query Execute a query in the database 
	 * @return true if the query succesfully pass
	 * @author Damien Clemenceau
	 */
	public boolean exec(String query) {
		boolean run = false;
		try {
			run = statement.execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return run;
	}
	
	/**
	 * This method allow to query the database
	 * @param query A sql query string (SELECT)
	 * @return a ResultSet with the data 
	 * @author Damien Clemenceau
	 */
	public ResultSet query(String query) {
		ResultSet result = null;
		try {
			result = statement.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * This method allow to insert a data in the database
	 * @param query A sql query string (INSERT)
	 * @return The id of the inserted data
	 * @author Damien Clemenceau
	 */
	public int insert(String query) {
		int key = -1;
		try {
			statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
			ResultSet res = statement.getGeneratedKeys();
			res.next();
			key = res.getInt(1);
			res.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return key;
	}
	
	/**
	 * This method allow to update data in the database
	 * @param query A sql query string (UPDATE)
	 * @author Damien Clemenceau
	 */
	public void update(String query) {
		try {
			statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Getter of the statement (Open connection before for init it)
	 * @return A statement
	 * @author Damien Clemenceau
	 */
	public Statement getStatement() {
		return statement;
	}
	
	/**
	 * Getter of the Connection (Open connection before for init it)
	 * @return The Connection
	 * @author Damien Clemenceau
	 */
	public Connection getConnection() {
		return connect;
	}
}
