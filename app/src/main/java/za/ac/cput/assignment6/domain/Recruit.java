package za.ac.cput.assignment6.domain;

import java.io.Serializable;

/**
 * Created by Ryan Carstens 213133040.
 */
public class Recruit implements Serializable, UserInterface {
    private Long id;
    private String southAfricanID;
    private FullNameDetails fullNameDetails;
    private ContactDetails contactDetails;


    public Long getId() {
        return id;
    }

    public String getSouthAfricanID() {
        return southAfricanID;
    }

    public FullNameDetails getFullNameDetails() {
        return fullNameDetails;
    }

    public ContactDetails getContactDetails() {
        return contactDetails;
    }

    private Recruit()
    {

    }

    private Recruit(Builder builder) {
        this.id = builder.id;
        this.southAfricanID = builder.southAfricanID;
        this.fullNameDetails = builder.fullNameDetails;
        this.contactDetails = builder.contactDetails;
    }

    public static class Builder{
        private Long id;
        private String southAfricanID;
        private FullNameDetails fullNameDetails;
        private ContactDetails contactDetails;

        public Builder id(Long value){
            this.id = value;
            return this;
        }

        public Builder southAfricanID(String value){
            this.southAfricanID = value;
            return this;
        }

        public Builder fullNameDetails(FullNameDetails value){
            this.fullNameDetails = value;
            return this;
        }

        public Builder contactDetails(ContactDetails value){
            this.contactDetails = value;
            return this;
        }

        public Builder copy(Recruit value) {
            this.id = value.id;
            this.southAfricanID = value.southAfricanID;
            this.fullNameDetails = value.fullNameDetails;
            this.contactDetails = value.contactDetails;
            return this;
        }

        public Recruit build() {
            return new Recruit(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Recruit recruit = (Recruit) o;

        return id != null ? id.equals(recruit.id) : recruit.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}