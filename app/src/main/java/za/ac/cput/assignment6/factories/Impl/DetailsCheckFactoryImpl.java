package za.ac.cput.assignment6.factories.Impl;

import za.ac.cput.assignment6.domain.DetailsCheck;
import za.ac.cput.assignment6.factories.DetailsCheckFactory;

/**
 * Created by Ryan Carstens 213133040.
 */
public class DetailsCheckFactoryImpl implements DetailsCheckFactory {
    private static DetailsCheckFactoryImpl factory = null;

    private DetailsCheckFactoryImpl(){

    }


    public static DetailsCheckFactoryImpl getInstance(){
        if (factory == null)
        {factory = new DetailsCheckFactoryImpl();}

        return factory;
    }

    public DetailsCheck createDetailsCheck(Long id, String response){
        return new DetailsCheck.Builder()
                .id(id)
                .response(response)
                .build();
    }
}
