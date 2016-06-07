package za.ac.cput.assignment6.factories;

import za.ac.cput.assignment6.domain.Contract;
import za.ac.cput.assignment6.domain.ContractDetails;

/**
 * Created by sanXion on 2016/05/11.
 */
public interface ContractFactory {
    Contract createContract(Long id, int IdCheckNum, int DetailsCheckNum, ContractDetails contractDetails);
}
