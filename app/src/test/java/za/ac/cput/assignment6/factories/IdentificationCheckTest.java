package za.ac.cput.assignment6.factories;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import za.ac.cput.assignment6.domain.IdentificationCheck;
import za.ac.cput.assignment6.factories.Impl.IdentificationCheckFactoryImpl;

/**
 * Created by Ryan Carstens 213133040.
 */

public class IdentificationCheckTest {

    private IdentificationCheckFactory factory;

    @Before
    public void setUp() throws Exception{
        factory = IdentificationCheckFactoryImpl.getInstance();
    }

    @Test
    public void testCreate() throws Exception
    {
        IdentificationCheck identificationCheck1 = factory.createIdentificationCheck(1L,"false");
        Assert.assertEquals(identificationCheck1.getResponse(),"false");
    }

    @Test
    public void testUpdate() throws Exception
    {
        IdentificationCheck identificationCheck1 = factory.createIdentificationCheck(1L,"false");
        IdentificationCheck updatedIdentificationCheck = new IdentificationCheck.Builder().copy(identificationCheck1).response("true").build();
        Assert.assertEquals(updatedIdentificationCheck.getResponse(),"true");
    }

}
