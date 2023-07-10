package helpers;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import row_mappers.PostRowMapper;
import tables.Post;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Objects;

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
        return jdbcTemplate().queryForObject("SELECT *\n"
                + "from wp_posts wp\n", new PostRowMapper());
    }

    public static Post getCreatedPostForUpdateAndDeleteTest(Integer idCreatePost) {
        return jdbcTemplate().queryForObject("SELECT *\n"
                + "from wp_posts wp where id like" + " " + idCreatePost, new PostRowMapper());
    }

    public static Integer getCreatedPostId() {
        final String SQL = "insert ignore into wp_posts  (post_title, post_status) values ('test','publish')";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate().update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL,
                    Statement.RETURN_GENERATED_KEYS);
            return ps;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }
}
