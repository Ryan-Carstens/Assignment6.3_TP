package za.ac.cput.assignment6.factories.Impl;

import za.ac.cput.assignment6.domain.IdentificationCheck;
import za.ac.cput.assignment6.factories.IdentificationCheckFactory;

/**
 * Created by Ryan Carstens 213133040.
 */
public class IdentificationCheckFactoryImpl implements IdentificationCheckFactory {
    private static IdentificationCheckFactoryImpl factory = null;

    private IdentificationCheckFactoryImpl(){

    }


    public static IdentificationCheckFactoryImpl getInstance(){
        if (factory == null)
        {factory = new IdentificationCheckFactoryImpl();}

        return factory;
    }

    public IdentificationCheck createIdentificationCheck(Long id, String response){
        return new IdentificationCheck.Builder()
                .id(id)
                .response(response)
                .build();
    }
}
