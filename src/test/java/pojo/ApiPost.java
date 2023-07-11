package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class ApiPost {
    /**
     *status.
     */
    private String status;
    /**
     *title.
     */
    private Title title;
}
