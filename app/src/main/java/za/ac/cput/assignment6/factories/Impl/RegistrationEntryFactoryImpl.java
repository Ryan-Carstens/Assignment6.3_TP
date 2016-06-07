package za.ac.cput.assignment6.factories.Impl;

import za.ac.cput.assignment6.domain.RegistrationEntry;
import za.ac.cput.assignment6.factories.RegistrationEntryFactory;

/**
 * Created by Ryan Carstens 213133040.
 */
public class RegistrationEntryFactoryImpl implements RegistrationEntryFactory {
    private static RegistrationEntryFactoryImpl factory = null;

    private RegistrationEntryFactoryImpl(){

    }


    public static RegistrationEntryFactoryImpl getInstance(){
        if (factory == null)
        {factory = new RegistrationEntryFactoryImpl();}

        return factory;
    }

    public RegistrationEntry createRegistrationEntry(Long id, String southAfricanID, String gender, String placementChoice){
        return new RegistrationEntry.Builder()
                .id(id)
                .southAfricanID(southAfricanID)
                .gender(gender)
                .placementChoice(placementChoice)
                .build();
    }
}
