package danielconnolly.assignment1.domain;

import javax.validation.constraints.NotEmpty;

public class LoginUser {

    @NotEmpty
    private String accountname;

    @NotEmpty
    private String password;

    public String getAccountname() {
        return accountname;
    }

    public void setAccountname(String accountname) {
        this.accountname = accountname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



}
