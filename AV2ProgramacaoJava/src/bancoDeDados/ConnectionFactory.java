package bancoDeDados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	
	public static Connection getConnection() throws SQLException {
		String url = "jdbc:mysql://localhost/registroEscolar";
		String user = "root";
		String password = "";//Colocar a sua senha do banco de dados
		
		return DriverManager.getConnection(url, user, password);
	}

}