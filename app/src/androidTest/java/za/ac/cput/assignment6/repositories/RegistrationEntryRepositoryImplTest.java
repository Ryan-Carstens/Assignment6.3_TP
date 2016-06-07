package za.ac.cput.assignment6.repositories;

import android.test.AndroidTestCase;

import junit.framework.Assert;

import java.util.Set;

import za.ac.cput.assignment6.domain.RegistrationEntry;
import za.ac.cput.assignment6.repository.Impl.RegistrationEntryRepositoryImpl;
import za.ac.cput.assignment6.repository.RegistrationEntryRepository;

/**
 * Created by sanXion on 2016/05/18.
 */
public class RegistrationEntryRepositoryImplTest extends AndroidTestCase {

    private static final String TAG="RegistrationEntry TEST";
    private Long id;


    public void testCreateReadUpdateDelete() throws Exception {
        RegistrationEntryRepository repo = new RegistrationEntryRepositoryImpl(this.getContext());


        RegistrationEntry registrationEntry = new RegistrationEntry.Builder()
                .southAfricanID("9105166701088")
                .gender("male")
                .placementChoice("Navy")
                .build();

        RegistrationEntry inserttedChassis = repo.save(registrationEntry);
        id = inserttedChassis.getId();
        Assert.assertNotNull(TAG + " CREATE",inserttedChassis);

        //READ ALL
        Set<RegistrationEntry> allRegistrationEntry = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",allRegistrationEntry.size()>0);

        //READ ENTITY
        RegistrationEntry entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);


        //UPDATE ENTITY
        RegistrationEntry updateEntity = new RegistrationEntry.Builder()
                .copy(entity)
                .placementChoice("Airforce")
                .build();
        repo.update(updateEntity);
        RegistrationEntry newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","Airforce",newEntity.getPlacementChoice());

        // DELETE ENTITY
        repo.delete(inserttedChassis);
        //repo.delete(updateEntity);
        RegistrationEntry deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);
    }

}
