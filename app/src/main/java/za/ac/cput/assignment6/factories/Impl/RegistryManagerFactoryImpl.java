package za.ac.cput.assignment6.factories.Impl;

import za.ac.cput.assignment6.domain.FullNameDetails;
import za.ac.cput.assignment6.domain.LoginDetails;
import za.ac.cput.assignment6.domain.RegistryManager;
import za.ac.cput.assignment6.factories.RegistryManagerFactory;

/**
 * Created by Ryan Carstens 213133040.
 */
public class RegistryManagerFactoryImpl implements RegistryManagerFactory {
    private static RegistryManagerFactoryImpl factory = null;

    private RegistryManagerFactoryImpl(){

    }


    public static RegistryManagerFactoryImpl getInstance(){
        if (factory == null)
        {factory = new RegistryManagerFactoryImpl();}

        return factory;
    }

    public RegistryManager createRegistryManager(Long id, String southAfricanID, FullNameDetails fullNameDetails, LoginDetails loginDetails){
        return new RegistryManager.Builder()
                .id(id)
                .southAfricanID(southAfricanID)
                .fullNameDetails(fullNameDetails)
                .loginDetails(loginDetails)
                .build();
    }
}
