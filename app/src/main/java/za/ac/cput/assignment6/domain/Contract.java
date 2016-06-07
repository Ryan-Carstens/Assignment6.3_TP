package za.ac.cput.assignment6.domain;

import java.io.Serializable;

/**
 * Created by Ryan Carstens 213133040.
 */
public class Contract implements Serializable{
    private Long id;
    private int IdCheckNum;
    private int DetailsCheckNum;
    private ContractDetails contractDetails;

    public Long getId() {
        return id;
    }

    public int getDetailsCheckNum() {
        return DetailsCheckNum;
    }

    public int getIdCheckNum() {
        return IdCheckNum;
    }

    public ContractDetails getContractDetails() {
        return contractDetails;
    }

    private Contract()
    {

    }

    private Contract(Builder builder) {
        this.id = builder.id;
        this.IdCheckNum = builder.IdCheckNum;
        this.DetailsCheckNum = builder.DetailsCheckNum;
        this.contractDetails = builder.contractDetails;
    }

    public static class Builder{
        private Long id;
        private int IdCheckNum;
        private int DetailsCheckNum;
        private ContractDetails contractDetails;

        public Builder id(Long value){
            this.id = value;
            return this;
        }

        public Builder IdCheckNum(int value){
            this.IdCheckNum = value;
            return this;
        }

        public Builder DetailsCheckNum(int value){
            this.DetailsCheckNum = value;
            return this;
        }

        public Builder contractDetails(ContractDetails value){
            this.contractDetails = value;
            return this;
        }

        public Builder copy(Contract value) {
            this.id = value.id;
            this.IdCheckNum = value.IdCheckNum;
            this.DetailsCheckNum = value.DetailsCheckNum;
            this.contractDetails = value.contractDetails;
            return this;
        }

        public Contract build() {
            return new Contract(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contract contract = (Contract) o;

        return id != null ? id.equals(contract.id) : contract.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}