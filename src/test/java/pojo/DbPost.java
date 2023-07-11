package pojo;

import lombok.Getter;

@Getter
public class DbPost {
    /**
     * title.
     */
    private String title;
    /**
     * password.
     */
    private String password;
    /**
     * status.
     */
    private String status;

    public DbPost(String title, String password, String status) {
        this.title = title;
        this.password = password;
        this.status = status;
    }
}
