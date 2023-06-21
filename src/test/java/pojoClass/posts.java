package pojoClass;

import lombok.Getter;

@Getter
public class posts {

    private String title;


    public posts(String title) {
        this.title = title;
    }


}
