package za.ac.cput.assignment6.factories;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import za.ac.cput.assignment6.domain.ContactDetails;
import za.ac.cput.assignment6.domain.FullNameDetails;
import za.ac.cput.assignment6.domain.LoginDetails;
import za.ac.cput.assignment6.domain.RegistryManager;
import za.ac.cput.assignment6.factories.Impl.RegistryManagerFactoryImpl;

/**
 * Created by Ryan Carstens 213133040.
 */

public class RegistryManagerTest {

    private RegistryManagerFactory factory;

    @Before
    public void setUp() throws Exception{
        factory = RegistryManagerFactoryImpl.getInstance();
    }

    @Test
    public void testCreate() throws Exception
    {
        FullNameDetails fullNameDetails = new FullNameDetails.Builder()
                .firstName("Rob")
                .lastName("Ash")
                .build();

        LoginDetails loginDetails = new LoginDetails.Builder()
                .email("ra@gmail.com")
                .password("ra123")
                .build();

        RegistryManager registryManager = factory.createRegistryManager(1l, "9006235201088", fullNameDetails, loginDetails);

        Assert.assertEquals(registryManager.getSouthAfricanID(), "9006235201088");
    }

    @Test
    public void testUpdate() throws Exception
    {
        FullNameDetails fullNameDetails = new FullNameDetails.Builder()
                .firstName("Rob")
                .lastName("Ash")
                .build();

        LoginDetails loginDetails = new LoginDetails.Builder()
                .email("ra@gmail.com")
                .password("ra123")
                .build();

        RegistryManager registryManager = factory.createRegistryManager(1l, "9006235201088", fullNameDetails, loginDetails);
        RegistryManager updatedRegistryManager = new RegistryManager.Builder().copy(registryManager).southAfricanID("8906235201088").build();
        Assert.assertEquals(updatedRegistryManager.getSouthAfricanID(), "8906235201088");
    }
}
