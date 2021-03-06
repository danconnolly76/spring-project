package danielconnolly.assignment1.domain;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

/**
 * Created by Daniel Connolly U1457227
 * This is model class is for adverts to be stored in a database
 */

@Entity
@Table(name = "users")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer iduser;
    @NotEmpty
    private String fName;
    @NotEmpty
    private String lName;
    @NotEmpty
    private String username;
    @NotEmpty
    @Column(unique = true)
    private String password;

    public User() {

    }

    public Integer getIduser() {
        return iduser;
    }

    public void setIduser(Integer iduser) {
        this.iduser = iduser;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
