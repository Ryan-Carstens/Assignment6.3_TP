package za.ac.cput.assignment6.services.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.ArrayList;
import java.util.Set;

import za.ac.cput.assignment6.conf.databases.GlobalContext;
import za.ac.cput.assignment6.domain.Contract;
import za.ac.cput.assignment6.domain.ContractDetails;
import za.ac.cput.assignment6.repository.ContractRepository;
import za.ac.cput.assignment6.repository.Impl.ContractRepositoryImpl;
import za.ac.cput.assignment6.services.ContractService;

/**
 * Created by sanXion on 2016/05/29.
 */
public class ContractServiceImpl extends Service implements ContractService {

    final private ContractRepository repository;

    private final IBinder localBinder = new ActivateServiceLocalBinder();

    private static ContractServiceImpl service = null;

    public static ContractServiceImpl getInstance()
    {
        if(service == null)
            service = new ContractServiceImpl();
        return service;
    }

    private ContractServiceImpl()
    {
        repository = new ContractRepositoryImpl(GlobalContext.getAppContext());
    }

    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    public class ActivateServiceLocalBinder extends Binder {
        public ContractServiceImpl getService() {
            return ContractServiceImpl.this;
        }
    }

    /*
    *This adds a Contract object to the database
     */
    @Override
    public Contract storeContract(Contract contract) {
        try{
            return repository.save(contract);
        }
        catch(Exception x)
        {
            x.printStackTrace();
        }
        return null;
    }

    /*
    *This updates a Contracts contents
     */
    @Override
    public boolean updateContractContents(Contract contract) {
        try{
            ContractDetails updateContractDetails = new ContractDetails.Builder()
                    .contractType(contract.getContractDetails().getContractType())
                    .contractNum(contract.getContractDetails().getContractNum())
                    .build();

            //Contract found = repository.findById(animal.getContractId());
            Contract updatedContractContents = new Contract.Builder()
                    .id(contract.getId())
                    .IdCheckNum(contract.getIdCheckNum())
                    .DetailsCheckNum(contract.getDetailsCheckNum())
                    .contractDetails(updateContractDetails)
                    .build();
            return repository.update(updatedContractContents).getId() == contract.getId();
        }
        catch(Exception x)
        {
            x.printStackTrace();
            return false;
        }
    }

    @Override
    public ArrayList<Contract> findByContractType(String contractType) {
        try{
            ArrayList<Contract> myList = new ArrayList<>();
            ArrayList<Contract> result = new ArrayList<>();
            Set<Contract> mySet = repository.findAll();

            if(!myList.addAll(mySet))
                return null;

            for(int i=0; i<myList.size(); i++)
                if(myList.get(i).getContractDetails().getContractType().equalsIgnoreCase(contractType))
                    result.add(myList.get(i));

            if(result.size() > 1)
                return result;
            else
                return new ArrayList<Contract>();

        }
        catch(Exception x)
        {
            x.printStackTrace();
        }
        return null;
    }

    @Override
    public Contract deleteContract(Contract contract) {
        try
        {
            return repository.delete(contract);
        }
        catch(Exception x)
        {
            x.printStackTrace();
        }
       return null;
    }

//    @Override
//    public boolean deleteAllContracts() {
//        try{
//            repository.deleteAll();
//            return repository.findAll().size() == 0;
//        }
//        catch(Exception x)
//        {
//            x.printStackTrace();
//            return false;
//        }
//    }

    @Override
    public ArrayList<Contract> getContracts() {
        try{
            ArrayList<Contract> result = new ArrayList<>();
            if(result.addAll(repository.findAll()))
                return result;
            else
                return new ArrayList<Contract>();
        }
        catch(Exception x)
        {
            x.printStackTrace();
        }
        return null;
    }


}
