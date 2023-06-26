package rowMappers;

import org.springframework.jdbc.core.RowMapper;
import tables.Post;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PostRowMapper implements RowMapper<Post> {
    @Override
    public Post mapRow(ResultSet rs, int i) throws SQLException {
        Post post = new Post();
        post.setPost_title(rs.getString("post_title"));
        post.setPast_password(rs.getString("post_password"));
        post.setPost_status(rs.getString("post_status"));
        return post;
    }
}
