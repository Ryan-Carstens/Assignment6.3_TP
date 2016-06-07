package za.ac.cput.assignment6.domain;

import java.io.Serializable;

/**
 * Created by Ryan Carstens 213133040.
 */
public class RegistryManager implements Serializable, UserInterface {
    private Long id;
    private String southAfricanID;
    private FullNameDetails fullNameDetails;
    private LoginDetails loginDetails;

    public Long getId() {
        return id;
    }

    public String getSouthAfricanID() {
        return southAfricanID;
    }

    public FullNameDetails getFullNameDetails() {
        return fullNameDetails;
    }

    public LoginDetails getLoginDetails() {
        return loginDetails;
    }

    private RegistryManager()
    {

    }

    private RegistryManager(Builder builder) {
        this.id = builder.id;
        this.southAfricanID = builder.southAfricanID;
        this.fullNameDetails = builder.fullNameDetails;
        this.loginDetails = builder.loginDetails;
    }

    public static class Builder{
        private Long id;
        private String southAfricanID;
        private FullNameDetails fullNameDetails;
        private LoginDetails loginDetails;

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

        public Builder loginDetails(LoginDetails value){
            this.loginDetails = value;
            return this;
        }

        public Builder copy(RegistryManager value) {
            this.id = value.id;
            this.southAfricanID = value.southAfricanID;
            this.fullNameDetails = value.fullNameDetails;
            this.loginDetails = value.loginDetails;
            return this;
        }

        public RegistryManager build() {
            return new RegistryManager(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RegistryManager registryManager = (RegistryManager) o;

        return id != null ? id.equals(registryManager.id) : registryManager.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
