package tables;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Post {
    /**
     * Тайтл поста.
     */
    private String postTitle;
    /**
     * Пароль поста.
     */
    private String pastPassword;
    /**
     * Статус поста.
     */
    private String postStatus;
    /**
     * Id поста.
     */
    private Integer id;
}
