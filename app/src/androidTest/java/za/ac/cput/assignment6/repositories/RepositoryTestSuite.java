package za.ac.cput.assignment6.repositories;

import junit.framework.Assert;
import junit.framework.TestSuite;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by sanXion on 2016/05/26.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ContractRepositoryImplTest.class,
        DetailsCheckRepositoryImplTest.class,
        IdentificationCheckRepositoryImplTest.class,
        RecruitRepositoryImplTest.class,
        RegistrationEntryRepositoryImplTest.class,
        RegistryManagerRepositoryImplTest.class
})
public class RepositoryTestSuite {
    @Test
    public void testThis()
    {
        Assert.assertTrue(true);
    }
}
