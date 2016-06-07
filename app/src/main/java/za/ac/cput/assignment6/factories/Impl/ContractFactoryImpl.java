package za.ac.cput.assignment6.factories.Impl;

import za.ac.cput.assignment6.domain.ContractDetails;
import za.ac.cput.assignment6.domain.Contract;
import za.ac.cput.assignment6.factories.ContractFactory;

/**
 * Created by sanXion on 2016/05/09.
 */
public class ContractFactoryImpl implements ContractFactory {
    private static ContractFactoryImpl factory = null;

    private ContractFactoryImpl(){

    }


    public static ContractFactoryImpl getInstance(){
        if (factory == null)
        {factory = new ContractFactoryImpl();}

        return factory;
    }

    public Contract createContract(Long id, int IdCheckNum, int DetailsCheckNum, ContractDetails contractDetails){
        return new Contract.Builder()
                .id(id)
                .IdCheckNum(IdCheckNum)
                .DetailsCheckNum(DetailsCheckNum)
                .contractDetails(contractDetails)
                .build();
    }

}
