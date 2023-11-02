package in.ineuron.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class JdbcUtil {

	private JdbcUtil() {
		// To avoid object creation made the constructor private
	}

	static {
		// Load and Register Driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver Loaded Suceessfully");
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
	}

	public static Connection getJdbcConnection() throws SQLException, IOException {

		/*
		 * HikariConfig config = new HikariConfig(
		 * "D:\\\\Servlets\\\\JdbcCrudApp\\\\src\\\\main\\\\java\\\\in\\\\ineuron\\\\properties\\\\application.properties"
		 * ); HikariDataSource dataSource = new HikariDataSource(config); return
		 * dataSource.getConnection();
		 */
		return getJdbcConnectionLogicalConnection();

	}

	private static Connection getJdbcConnectionLogicalConnection() throws IOException, SQLException {

		// Load the Data from property file
		// Using Jdbc connection

		String fileLoc = "D:\\Servlets\\MVCCrudAppUsingJSP&JSTL\\src\\main\\java\\in\\ineuron\\properties\\application.properties";
		FileInputStream file = new FileInputStream(fileLoc);
		Properties properties = new Properties();
		properties.load(file);

		String url = properties.getProperty("jdbcUrl");
		String username = properties.getProperty("username");
		String password = properties.getProperty("password");

		Connection connection = DriverManager.getConnection(url, username, password);

		return connection;

	}

}
