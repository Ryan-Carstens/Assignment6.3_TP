package za.ac.cput.assignment6.domain;

import java.io.Serializable;

/**
 * Created by sanXion on 2016/05/09.
 */
public class ContactDetails implements Serializable {
    private String homeNumber;
    private String emailAddress;

    public String getHomeNumber() {
        return homeNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public ContactDetails(Builder builder){
        homeNumber = builder.homeNumber;
        emailAddress = builder.emailAddress;
    }

    public static class Builder{
        private String homeNumber;
        private String emailAddress;

        public Builder homeNumber(String homeNumber){
            this.homeNumber = homeNumber;
            return this;
        }

        public Builder emailAddress(String value){
            this.emailAddress = value;
            return this;
        }

        public Builder copy(ContactDetails value)
        {
            this.homeNumber = value.homeNumber;
            this.emailAddress = value.emailAddress;

            return this;
        }

        public ContactDetails build(){return new ContactDetails(this);}
    }
}
