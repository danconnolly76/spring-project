package danielconnolly.assignment1.domain;


import javax.persistence.*;
import javax.validation.constraints.*;


@Entity
@Table(name = "ads")
public class Advert {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotEmpty
    @Size(min=1, max=30)
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty
    private String description;

    @NotEmpty
    private String comment;


    public Advert() {
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getComment()
    {
        return comment;
    }

    public void setComment(String comment)
    {
        this.comment = comment;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}
