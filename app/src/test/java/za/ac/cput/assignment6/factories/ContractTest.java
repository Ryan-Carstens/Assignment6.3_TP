package za.ac.cput.assignment6.factories;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import za.ac.cput.assignment6.domain.ContractDetails;
import za.ac.cput.assignment6.domain.Contract;
import za.ac.cput.assignment6.factories.Impl.ContractFactoryImpl;

/**
 * Created by Ryan Carstens 213133040.
 */

public class ContractTest {
    private ContractFactory factory;

    @Before
    public void setUp() throws Exception{
        factory = ContractFactoryImpl.getInstance();
    }

    @Test
    public void testCreate() throws Exception
    {
        ContractDetails contractDetails = new ContractDetails.Builder()
                .contractNum(10)
                .contractType("Navy")
                .build();

        Contract contract = factory.createContract(1l, 1, 1, contractDetails);

        Assert.assertEquals(contract.getIdCheckNum(), 1);
    }

    @Test
    public void testUpdate() throws Exception
    {
        ContractDetails contractDetails = new ContractDetails.Builder()
                .contractNum(10)
                .contractType("Navy")
                .build();

        Contract contract = factory.createContract(1l, 1, 1, contractDetails);
        Contract updatedContract = new Contract.Builder().copy(contract).IdCheckNum(0).build();
        Assert.assertEquals(updatedContract.getIdCheckNum(),0);
    }
}
