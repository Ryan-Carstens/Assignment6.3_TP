package za.ac.cput.assignment6.factories;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import za.ac.cput.assignment6.domain.RegistrationEntry;
import za.ac.cput.assignment6.factories.Impl.RegistrationEntryFactoryImpl;

/**
 * Created by Ryan Carstens 213133040.
 */

public class RegistrationEntryTest {

    private RegistrationEntryFactory factory;

    @Before
    public void setUp() throws Exception{
        factory = RegistrationEntryFactoryImpl.getInstance();
    }

    @Test
    public void testCreate() throws Exception
    {
        RegistrationEntry registrationEntry1 = factory.createRegistrationEntry(1l, "9001105201088", "Male", "Navy");

        Assert.assertEquals(registrationEntry1.getPlacementChoice(), "Navy");
    }

    @Test
    public void testUpdate() throws Exception
    {
        RegistrationEntry registrationEntry1 = factory.createRegistrationEntry(1l, "9001105201088", "Male", "Navy");
        RegistrationEntry updatedRegistrationEntry = new RegistrationEntry.Builder().copy(registrationEntry1).placementChoice("Army").build();
        Assert.assertEquals(updatedRegistrationEntry.getPlacementChoice(),"Army");
    }
}
