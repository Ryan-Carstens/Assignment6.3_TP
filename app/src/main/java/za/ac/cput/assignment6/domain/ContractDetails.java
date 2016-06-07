package za.ac.cput.assignment6.domain;

import java.io.Serializable;

/**
 * Created by sanXion on 2016/05/09.
 */
public class ContractDetails implements Serializable {
    private String contractType;
    private int contractNum;

    public ContractDetails(){

    }

    public String getContractType() {
        return contractType;
    }

    public int getContractNum() {
        return contractNum;
    }

    public ContractDetails(Builder builder){
        contractType = builder.contractType;
        contractNum = builder.contractNum;
    }

    public static class Builder{
        private String contractType;
        private int contractNum;

        public Builder contractType(String contractType){
            this.contractType = contractType;
            return this;
        }

        public Builder contractNum(int value){
            this.contractNum = value;
            return this;
        }

        public Builder copy(ContractDetails value)
        {
            this.contractType = value.contractType;
            this.contractNum = value.contractNum;

            return this;
        }

        public ContractDetails build(){return new ContractDetails(this);}
    }
}
