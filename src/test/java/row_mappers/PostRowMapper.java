package row_mappers;

import org.springframework.jdbc.core.RowMapper;
import tables.Post;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PostRowMapper implements RowMapper<Post> {
    @Override
    public Post mapRow(ResultSet rs, int i) throws SQLException {
        Post post = new Post();
        post.setPostTitle(rs.getString("post_title"));
        post.setPastPassword(rs.getString("post_password"));
        post.setPostStatus(rs.getString("post_status"));
        return post;
    }
}
