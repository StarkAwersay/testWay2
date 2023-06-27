package helpers;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

abstract public class SqlConnection {
    public static javax.sql.DataSource mysqlDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/wordpress");
        dataSource.setUsername("wordpress");
        dataSource.setPassword("wordpress");
        return dataSource;
    }
}
