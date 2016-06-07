package za.ac.cput.assignment6.factories.Impl;

import za.ac.cput.assignment6.domain.ContactDetails;
import za.ac.cput.assignment6.domain.FullNameDetails;
import za.ac.cput.assignment6.domain.Recruit;
import za.ac.cput.assignment6.factories.RecruitFactory;

/**
 * Created by Ryan Carstens 213133040.
 */

public class RecruitFactoryImpl implements RecruitFactory {
    private static RecruitFactoryImpl factory = null;

    private RecruitFactoryImpl(){

    }


    public static RecruitFactoryImpl getInstance(){
        if (factory == null)
        {factory = new RecruitFactoryImpl();}

        return factory;
    }

    public Recruit createRecruit(Long id, String southAfricanID, FullNameDetails fullNameDetails, ContactDetails contactDetails){
        return new Recruit.Builder()
                .id(id)
                .southAfricanID(southAfricanID)
                .fullNameDetails(fullNameDetails)
                .contactDetails(contactDetails)
                .build();
    }
}
