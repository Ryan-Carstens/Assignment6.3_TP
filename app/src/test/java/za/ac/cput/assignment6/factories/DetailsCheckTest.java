package za.ac.cput.assignment6.factories;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import za.ac.cput.assignment6.domain.DetailsCheck;
import za.ac.cput.assignment6.factories.Impl.DetailsCheckFactoryImpl;

/**
 * Created by Ryan Carstens 213133040.
 */
public class DetailsCheckTest {
    private DetailsCheckFactory factory;

    @Before
    public void setUp() throws Exception{
        factory = DetailsCheckFactoryImpl.getInstance();
    }

    @Test
    public void testCreate() throws Exception
    {
        DetailsCheck detailsCheck1 = factory.createDetailsCheck(1L,"true");
        Assert.assertEquals(detailsCheck1.getResponse(),"true");
    }

    @Test
    public void testUpdate() throws Exception
    {
        DetailsCheck detailsCheck1 = factory.createDetailsCheck(1L,"true");
        DetailsCheck updatedDetailsCheck = new DetailsCheck.Builder().copy(detailsCheck1).response("false").build();
        Assert.assertEquals(updatedDetailsCheck.getResponse(),"false");
    }

}



