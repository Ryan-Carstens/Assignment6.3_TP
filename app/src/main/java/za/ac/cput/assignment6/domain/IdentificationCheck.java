package za.ac.cput.assignment6.domain;

import java.io.Serializable;

/**
 * Created by Ryan Carstens 213133040.
 */
public class IdentificationCheck implements Serializable {
    private Long id;
    private String response;

    public Long getId() {
        return id;
    }

    public String getResponse() {
        return response;
    }

    private IdentificationCheck()
    {

    }

    private IdentificationCheck(Builder builder) {
        this.id = builder.id;
        this.response = builder.response;
    }

    public static class Builder{
        private Long id;
        private String response;

        public Builder id(Long value){
            this.id = value;
            return this;
        }

        public Builder response(String value){
            this.response = value;
            return this;
        }

        public Builder copy(IdentificationCheck value) {
            this.id = value.id;
            this.response = value.response;
            return this;
        }

        public IdentificationCheck build() {
            return new IdentificationCheck(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IdentificationCheck identificationCheck = (IdentificationCheck) o;

        return id != null ? id.equals(identificationCheck.id) : identificationCheck.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
