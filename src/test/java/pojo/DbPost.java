package pojo;

import lombok.Getter;

@Getter
public class DbPost {
    /**
     * Тайтл поста.
     */
    private String title;
    /**
     * Пароль поста.
     */
    private String password;
    /**
     * Статус поста.
     */
    private String status;

    public DbPost(String title, String password, String status) {
        this.title = title;
        this.password = password;
        this.status = status;
    }
}
