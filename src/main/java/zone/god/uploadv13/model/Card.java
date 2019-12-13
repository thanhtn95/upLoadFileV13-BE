package zone.god.uploadv13.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Cards")
@Data
public class Card implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private int grade;
    private String description;
    private String image;

    public Card() {
    }
    public Card(String name, int grade, String description){
        this.name = name;
        this.grade = grade;
        this.description = description;
    }
}
