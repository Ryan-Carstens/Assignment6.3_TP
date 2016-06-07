package za.ac.cput.assignment6.domain;

import java.io.Serializable;

/**
 * Created by sanXion on 2016/05/09.
 */
public class FullNameDetails implements Serializable {
    private String firstName;
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public FullNameDetails(Builder builder){
        firstName = builder.firstName;
        lastName = builder.lastName;
    }

    public static class Builder{
        private String firstName;
        private String lastName;

        public Builder firstName(String value){
            this.firstName = value;
            return this;
        }

        public Builder lastName(String value){
            this.lastName = value;
            return this;
        }

        public Builder copy(FullNameDetails value)
        {
            this.firstName = value.firstName;
            this.lastName = value.lastName;

            return this;
        }

        public FullNameDetails build(){return new FullNameDetails(this);}
    }
}
