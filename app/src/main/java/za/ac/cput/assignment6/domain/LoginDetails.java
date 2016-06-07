package za.ac.cput.assignment6.domain;

import java.io.Serializable;

/**
 * Created by sanXion on 2016/05/09.
 */
public class LoginDetails implements Serializable{
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public LoginDetails(Builder builder){
        email = builder.email;
        password = builder.password;
    }

    public static class Builder{
        private String email;
        private String password;

        public Builder email(String value){
            this.email = value;
            return this;
        }

        public Builder password(String value){
            this.password = value;
            return this;
        }

        public Builder copy(LoginDetails value)
        {
            this.email = value.email;
            this.password = value.password;

            return this;
        }

        public LoginDetails build(){return new LoginDetails(this);}
    }
}
