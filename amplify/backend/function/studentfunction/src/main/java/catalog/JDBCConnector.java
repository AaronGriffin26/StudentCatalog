package catalog;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnector {
	public static Connection getRemoteConnection(Context context) {
		LambdaLogger logger = context.getLogger();
		if(System.getenv("RDS_HOSTNAME") != null) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				String dbName = System.getenv("RDS_DB_NAME");
				String userName = System.getenv("RDS_USERNAME");
				String password = System.getenv("RDS_PASSWORD");
				String hostname = System.getenv("RDS_HOSTNAME");
				String port = System.getenv("RDS_PORT");
				String jdbcUrl = "jdbc:mysql://" + hostname + ":" + port + "/" + dbName;
				logger.log("Getting remote connection with connection string from environment variables.");
				logger.log("jdbc:mysql://" + hostname + ":" + port + "/" + dbName + "?user=" + userName + "&password=" + password);
				throw new ClassNotFoundException("Connection not working at the moment");
				//				Connection con = DriverManager.getConnection(jdbcUrl, userName, password);
				//				logger.log("Remote connection successful.");
				//				return con;
			}
			catch(ClassNotFoundException /*| SQLException*/ e) {
				logger.log(e.toString());
			}
			logger.log("Failed to load connection!");
			return null;
		}
		logger.log("RDS_HOSTNAME undefined!");
		return null;
	}

	public static Connection getRemoteConnectionTest(Context context) {
		LambdaLogger logger = context.getLogger();
		if(System.getenv("RDS_HOSTNAME") != null) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				String dbName = System.getenv("RDS_DB_NAME");
				String userName = System.getenv("RDS_USERNAME");
				String password = System.getenv("RDS_PASSWORD");
				String hostname = System.getenv("RDS_HOSTNAME");
				String port = System.getenv("RDS_PORT");
				String jdbcUrl = "jdbc:mysql://" + hostname + ":" + port + "/" + dbName;
				logger.log("Getting remote connection with connection string from environment variables.");
				logger.log("jdbc:mysql://" + hostname + ":" + port + "/" + dbName + "?user=" + userName + "&password=" + password);
				Connection con = DriverManager.getConnection(jdbcUrl, userName, password);
				logger.log("Remote connection successful.");
				return con;
			}
			catch(ClassNotFoundException | SQLException e) {
				logger.log(e.toString());
			}
			logger.log("Failed to load connection!");
			return null;
		}
		logger.log("RDS_HOSTNAME undefined!");
		return null;
	}
}
