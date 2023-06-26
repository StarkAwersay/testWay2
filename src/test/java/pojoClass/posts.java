package pojoClass;

import lombok.Getter;


@Getter
public class Posts {

    private String title;
    private String password;
    private String status;

    public Posts(String title, String password, String status) {
        this.title = title;
        this.password = password;
        this.status = status;
    }


}
