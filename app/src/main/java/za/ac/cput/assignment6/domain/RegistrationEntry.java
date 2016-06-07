package za.ac.cput.assignment6.domain;

import java.io.Serializable;

/**
 * Created by Ryan Carstens 213133040.
 */
public class RegistrationEntry implements Serializable{
    private Long id;
    private String southAfricanID;
    private String gender;
    private String placementChoice;


    public Long getId() {
        return id;
    }

    public String getSouthAfricanID() {
        return southAfricanID;
    }

    public String getGender() {
        return gender;
    }

    public String getPlacementChoice() {
        return placementChoice;
    }

    private RegistrationEntry()
    {

    }

    private RegistrationEntry(Builder builder) {
        this.id = builder.id;
        this.southAfricanID = builder.southAfricanID;
        this.gender = builder.gender;
        this.placementChoice = builder.placementChoice;
    }

    public static class Builder{
        private Long id;
        private String southAfricanID;
        private String gender;
        private String placementChoice;

        public Builder id(Long value){
            this.id = value;
            return this;
        }

        public Builder southAfricanID(String value){
            this.southAfricanID = value;
            return this;
        }

        public Builder gender(String value){
            this.gender = value;
            return this;
        }

        public Builder placementChoice(String value){
            this.placementChoice = value;
            return this;
        }

        public Builder copy(RegistrationEntry value) {
            this.id = value.id;
            this.southAfricanID = value.southAfricanID;
            this.gender = value.gender;
            this.placementChoice = value.placementChoice;
            return this;
        }

        public RegistrationEntry build() {
            return new RegistrationEntry(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RegistrationEntry registrationEntry = (RegistrationEntry) o;

        return id != null ? id.equals(registrationEntry.id) : registrationEntry.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
