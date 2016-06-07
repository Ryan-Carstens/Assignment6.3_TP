package za.ac.cput.assignment6.services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;


import junit.framework.Assert;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Set;

import za.ac.cput.assignment6.conf.databases.GlobalContext;
import za.ac.cput.assignment6.domain.Contract;
import za.ac.cput.assignment6.domain.ContractDetails;
import za.ac.cput.assignment6.services.Impl.ContractServiceImpl;

/**
 * Created by sanXion on 2016/05/29.
 */
public class ContractServiceTest  extends AndroidTestCase {
    private ContractServiceImpl myService;
    private boolean isBound;
    Long newContractId;
    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(this.getContext(), ContractServiceImpl.class);
        GlobalContext.context = this.getContext();
        myService = ContractServiceImpl.getInstance();
        GlobalContext.getAppContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            ContractServiceImpl.ActivateServiceLocalBinder binder
                    = (ContractServiceImpl.ActivateServiceLocalBinder) service;
            myService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;

        }
    };

    @Test
    public void testCreateContract()
    {
        ContractDetails contractDetails = new ContractDetails.Builder()
                .contractType("Navy")
                .contractNum(12345)
                .build();

        Contract contract = new Contract.Builder()
                .IdCheckNum(1)
                .DetailsCheckNum(1)
                .contractDetails(contractDetails)
                .build();

        contract = myService.storeContract(contract);
        if(contract.getId() != null)
            newContractId = contract.getId();
        Assert.assertNotNull(contract.getId());
    }

    @Test
    public void testUpdateContract()
    {
        ContractDetails contractDetails = new ContractDetails.Builder()
                .contractType("Navy")
                .contractNum(12345)
                .build();

        Contract original = new Contract.Builder()
                .IdCheckNum(1)
                .DetailsCheckNum(1)
                .contractDetails(contractDetails)
                .build();

        original = myService.storeContract(original);

        Contract updatedContract = new Contract.Builder().copy(original).DetailsCheckNum(9).build();
        boolean temp = myService.updateContractContents(updatedContract);
        Assert.assertTrue(temp);
        //Assert.assertEquals(true, myService.updateContractContents(updatedContract));
    }

    @Test
    public void testFindByContractType()
    {
        Assert.assertNotNull(myService.findByContractType("Navy"));
    }

    @Test
    public void testGetLivingAreas()
    {
        Assert.assertNotNull(myService.getContracts());
    }


//    @Test
//    public void testDeleteAll()
//    {
//        Assert.assertTrue(myService.deleteAllContracts());
//    }
}