package pojo;

import lombok.Getter;

@Getter
public class BdPost {
    private String title;
    private String password;
    private String status;

    public BdPost(String title, String password, String status) {
        this.title = title;
        this.password = password;
        this.status = status;
    }
}
