package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class ApiPost {
    /**
     * Статус поста.
     */
    private String status;
    /**
     *  Тайтл поста.
     */
    private Title title;
}
