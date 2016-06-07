package za.ac.cput.assignment6.services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import junit.framework.Assert;

import org.junit.Test;

import za.ac.cput.assignment6.conf.databases.GlobalContext;
import za.ac.cput.assignment6.domain.FullNameDetails;
import za.ac.cput.assignment6.domain.LoginDetails;
import za.ac.cput.assignment6.domain.RegistryManager;
import za.ac.cput.assignment6.services.Impl.RegistryManagerServiceImpl;

/**
 * Created by sanXion on 2016/05/31.
 */
public class RegistryManagerServiceTest extends AndroidTestCase {

    private RegistryManagerServiceImpl myService;
    private boolean isBound;
    Long newRegistryManagerId;
    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(this.getContext(), RegistryManagerServiceImpl.class);
        GlobalContext.context = this.getContext();
        myService = RegistryManagerServiceImpl.getInstance();
        GlobalContext.getAppContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            RegistryManagerServiceImpl.ActivateServiceLocalBinder binder
                    = (RegistryManagerServiceImpl.ActivateServiceLocalBinder) service;
            myService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;

        }
    };

    @Test
    public void testCreateRegistryManager()
    {
        FullNameDetails fullNameDetails = new FullNameDetails.Builder()
                .firstName("Chris")
                .lastName("Henrico")
                .build();

        LoginDetails loginDetails = new LoginDetails.Builder()
                .email("chenrico@gmail.com")
                .password("chenrico101")
                .build();

        RegistryManager registryManager = new RegistryManager.Builder()
                .southAfricanID("9105166701088")
                .fullNameDetails(fullNameDetails)
                .loginDetails(loginDetails)
                .build();

        registryManager = myService.createRegistryManager(registryManager);
        if(registryManager.getId() != null)
            newRegistryManagerId = registryManager.getId();
        Assert.assertNotNull(registryManager.getId());
    }

    @Test
    public void testUpdateRegistryManagerDetails()
    {
        FullNameDetails fullNameDetails = new FullNameDetails.Builder()
                .firstName("Chris")
                .lastName("Henrico")
                .build();

        LoginDetails loginDetails = new LoginDetails.Builder()
                .email("chenrico2@gmail.com")
                .password("chenrico102")
                .build();

        RegistryManager registryManager = new RegistryManager.Builder()
                .southAfricanID("9205166701088")
                .fullNameDetails(fullNameDetails)
                .loginDetails(loginDetails)
                .build();

        registryManager = myService.createRegistryManager(registryManager);

        RegistryManager updatedRegistryManager = new RegistryManager.Builder().copy(registryManager).southAfricanID("9305166701088").build();
        Assert.assertTrue(myService.updateRegistryManagerDetails(updatedRegistryManager));
    }

    @Test
    public void testRegistryManagerLoginCheck()
    {
        Assert.assertNotNull(myService.registryManagerLogin("chenrico@gmail.com","chenrico101"));
    }



    @Test
    public void testFindByEmail()
    {
        Assert.assertNotNull(myService.findByEmail("chenrico@gmail.com"));
    }

    @Test
    public void testDeleteAll()
    {
        Assert.assertTrue(myService.deleteAllRegistryManagers());
    }
}
