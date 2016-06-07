package za.ac.cput.assignment6.services.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.ArrayList;
import java.util.Set;

import za.ac.cput.assignment6.conf.databases.GlobalContext;
import za.ac.cput.assignment6.domain.FullNameDetails;
import za.ac.cput.assignment6.domain.LoginDetails;
import za.ac.cput.assignment6.domain.RegistryManager;
import za.ac.cput.assignment6.repository.Impl.RegistryManagerRepositoryImpl;
import za.ac.cput.assignment6.repository.RegistryManagerRepository;
import za.ac.cput.assignment6.services.RegistryManagerService;

/**
 * Created by sanXion on 2016/05/31.
 */
public class RegistryManagerServiceImpl extends Service implements RegistryManagerService {

    final private RegistryManagerRepository repository;

    private final IBinder localBinder = new ActivateServiceLocalBinder();

    private static RegistryManagerServiceImpl service = null;

    public static RegistryManagerServiceImpl getInstance()
    {
        if(service == null)
            service = new RegistryManagerServiceImpl();
        return service;
    }

    private RegistryManagerServiceImpl()
    {
        repository = new RegistryManagerRepositoryImpl(GlobalContext.getAppContext());
    }

    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    public class ActivateServiceLocalBinder extends Binder {
        public RegistryManagerServiceImpl getService() {
            return RegistryManagerServiceImpl.this;
        }
    }

    /*
    *This adds a Contract object to the database
     */
    @Override
    public RegistryManager createRegistryManager(RegistryManager registryManager) {
        try{
            return repository.save(registryManager);
        }
        catch(Exception x)
        {
            x.printStackTrace();
        }
        return null;
    }

    /*
    *This updates a RegistryManagers contents
     */
    @Override
    public boolean updateRegistryManagerDetails(RegistryManager registryManager) {
        try{
            LoginDetails updateLoginDetails = new LoginDetails.Builder()
                    .email(registryManager.getLoginDetails().getEmail())
                    .password(registryManager.getLoginDetails().getPassword())
                    .build();

            FullNameDetails updateFullNameDetails = new FullNameDetails.Builder()
                    .firstName(registryManager.getFullNameDetails().getFirstName())
                    .lastName(registryManager.getFullNameDetails().getLastName())
                    .build();

            //Contract found = repository.findById(animal.getContractId());
            RegistryManager updatedRegistryManagerDetails = new RegistryManager.Builder()
                    .id(registryManager.getId())
                    .southAfricanID(registryManager.getSouthAfricanID())
                    .fullNameDetails(updateFullNameDetails)
                    .loginDetails(updateLoginDetails)
                    .build();
            return repository.update(updatedRegistryManagerDetails).getId() == registryManager.getId();
        }
        catch(Exception x)
        {
            x.printStackTrace();
            return false;
        }

    }

    /*
     *This checks the database for the corresponding user email and password to log the user in
     */
    @Override
    public ArrayList<RegistryManager> registryManagerLogin(String email, String password) {
        try{
            ArrayList<RegistryManager> myList = new ArrayList<>();
            ArrayList<RegistryManager> result = new ArrayList<>();
            Set<RegistryManager> mySet = repository.findAll();

            if(!myList.addAll(mySet))
                return null;

            for(int i=0; i<myList.size(); i++)
                if((myList.get(i).getLoginDetails().getEmail().equalsIgnoreCase(email)) && (myList.get(i).getLoginDetails().getPassword().equalsIgnoreCase(password)))
                    result.add(myList.get(i));

            if(result.size() > 1)
                return result;
            else
                return new ArrayList<RegistryManager>();

        }
        catch(Exception x)
        {
            x.printStackTrace();
        }
        return null;
    }


    @Override
    public ArrayList<RegistryManager> findByEmail(String email) {
        try{
            ArrayList<RegistryManager> myList = new ArrayList<>();
            ArrayList<RegistryManager> result = new ArrayList<>();
            Set<RegistryManager> mySet = repository.findAll();

            if(!myList.addAll(mySet))
                return null;

            for(int i=0; i<myList.size(); i++)
                if(myList.get(i).getLoginDetails().getEmail().equalsIgnoreCase(email))
                    result.add(myList.get(i));

            if(result.size() > 1)
                return result;
            else
                return new ArrayList<RegistryManager>();

        }
        catch(Exception x)
        {
            x.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteAllRegistryManagers() {
        try{
            repository.deleteAll();
            return repository.findAll().size() == 0;
        }
        catch(Exception x)
        {
            x.printStackTrace();
            return false;
        }
    }
}
