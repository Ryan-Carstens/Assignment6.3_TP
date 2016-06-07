package za.ac.cput.assignment6.factories;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import za.ac.cput.assignment6.domain.ContactDetails;
import za.ac.cput.assignment6.domain.Contract;
import za.ac.cput.assignment6.domain.FullNameDetails;
import za.ac.cput.assignment6.domain.Recruit;
import za.ac.cput.assignment6.factories.Impl.RecruitFactoryImpl;

/**
 * Created by Ryan Carstens 213133040.
 */

public class RecruitTest {

    private RecruitFactory factory;

    @Before
    public void setUp() throws Exception{
        factory = RecruitFactoryImpl.getInstance();
    }

    @Test
    public void testCreate() throws Exception
    {
        FullNameDetails fullNameDetails = new FullNameDetails.Builder()
                .firstName("John")
                .lastName("Doe")
                .build();

        ContactDetails contractDetails = new ContactDetails.Builder()
                .homeNumber("123")
                .emailAddress("jd@gmail.com")
                .build();

        Recruit recruit = factory.createRecruit(1l, "9001105201088", fullNameDetails, contractDetails);

        Assert.assertEquals(recruit.getSouthAfricanID(), "9001105201088");
    }

    @Test
    public void testUpdate() throws Exception
    {
        FullNameDetails fullNameDetails = new FullNameDetails.Builder()
                .firstName("John")
                .lastName("Doe")
                .build();

        ContactDetails contractDetails = new ContactDetails.Builder()
                .homeNumber("123")
                .emailAddress("jd@gmail.com")
                .build();

        Recruit recruit = factory.createRecruit(1l, "9001105201088", fullNameDetails, contractDetails);
        Recruit updatedRecruit = new Recruit.Builder().copy(recruit).southAfricanID("8901105201088").build();
        Assert.assertEquals(updatedRecruit.getSouthAfricanID(), "8901105201088");
    }

    //____________________________________________________________________________________________
}
