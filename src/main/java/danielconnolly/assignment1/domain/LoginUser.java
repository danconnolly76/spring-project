package danielconnolly.assignment1.domain;

import javax.validation.constraints.NotEmpty;

/**
 * Created by Daniel Connolly U1457227
 * This is model class is for users to be stored in a database
 */

public class LoginUser {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



}

