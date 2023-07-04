package helpers;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import row_mappers.PostRowMapper;
import tables.Post;

public class JdbcTemplateHelper {
    public static javax.sql.DataSource mysqlDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/wordpress");
        dataSource.setUsername("wordpress");
        dataSource.setPassword("wordpress");
        return dataSource;
    }

    public static JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(mysqlDataSource());
    }

    public static Post getCreatedPostForCreateTest() {
        return jdbcTemplate().queryForObject("SELECT *\n" +
                "from wp_posts wp\n", new PostRowMapper());
    }

    public static Post getCreatedPostForUpdateAndDeleteTest(Integer idCreatePost) {
        return jdbcTemplate().queryForObject("SELECT *\n" +
                "from wp_posts wp where id like" + " " + idCreatePost, new PostRowMapper());
    }
}
