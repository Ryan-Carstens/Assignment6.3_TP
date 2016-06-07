package za.ac.cput.assignment6.repositories;

import android.test.AndroidTestCase;

import junit.framework.Assert;

import java.util.Set;

import za.ac.cput.assignment6.domain.FullNameDetails;
import za.ac.cput.assignment6.domain.LoginDetails;
import za.ac.cput.assignment6.domain.RegistryManager;
import za.ac.cput.assignment6.repository.Impl.RegistryManagerRepositoryImpl;
import za.ac.cput.assignment6.repository.RegistryManagerRepository;

/**
 * Created by sanXion on 2016/05/18.
 */
public class RegistryManagerRepositoryImplTest extends AndroidTestCase {

    private static final String TAG="RegistryManager TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        RegistryManagerRepository repo = new RegistryManagerRepositoryImpl(this.getContext());

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

        RegistryManager inserttedChassis = repo.save(registryManager);
        id = inserttedChassis.getId();
        Assert.assertNotNull(TAG + " CREATE",inserttedChassis);
        Assert.assertNotNull(TAG + " CREATE",inserttedChassis.getFullNameDetails());
        Assert.assertNotNull(TAG + " CREATE",inserttedChassis.getLoginDetails());

        //READ ALL
        Set<RegistryManager> allRegistryManager = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",allRegistryManager.size()>0);

        //READ ENTITY
        RegistryManager entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);


        //UPDATE ENTITY
        RegistryManager updateEntity = new RegistryManager.Builder()
                .copy(entity)
                .southAfricanID("8905166701088")
                .build();
        repo.update(updateEntity);
        RegistryManager newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","8905166701088",newEntity.getSouthAfricanID());

        // DELETE ENTITY
        //repo.delete(inserttedChassis);
        //repo.delete(updateEntity); leave commented
        //RegistryManager deletedEntity = repo.findById(id);
        //Assert.assertNull(TAG+" DELETE",deletedEntity);
    }
}