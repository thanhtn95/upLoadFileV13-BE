package zone.god.uploadv13.model;

import lombok.Data;

@Data
public class Card_updateForm {
    private long id;
    private String name;
    private int grade;
    private String description;
    private String image;
}
