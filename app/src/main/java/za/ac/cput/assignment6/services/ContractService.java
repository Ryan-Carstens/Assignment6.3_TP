package za.ac.cput.assignment6.services;

import java.util.ArrayList;

import za.ac.cput.assignment6.domain.Contract;

/**
 * Created by sanXion on 2016/05/29.
 */
public interface ContractService {
    Contract storeContract(Contract contract);
    boolean updateContractContents(Contract contract);
    ArrayList<Contract> findByContractType(String contractType);
    Contract deleteContract(Contract contract);
    //boolean deleteAllContracts();
    ArrayList<Contract> getContracts();
}
