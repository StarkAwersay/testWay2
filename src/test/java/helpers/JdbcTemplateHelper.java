package helpers;

import org.springframework.jdbc.core.JdbcTemplate;
import rowMappers.PostRowMapper;
import tables.Post;

import javax.sql.DataSource;

public class JdbcTemplateHelper {
    public static void jdbcTemplateUpdate(DataSource dataSource, String request) {
        new JdbcTemplate(dataSource).update(request);
    }

    public static Post jdbcTemplateGetObject(DataSource dataSource, String request) {
        return new JdbcTemplate(dataSource).queryForObject(request, new PostRowMapper());
    }
}
