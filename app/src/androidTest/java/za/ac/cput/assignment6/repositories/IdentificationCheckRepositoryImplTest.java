package za.ac.cput.assignment6.repositories;

import android.test.AndroidTestCase;

import junit.framework.Assert;

import java.util.Set;

import za.ac.cput.assignment6.domain.IdentificationCheck;
import za.ac.cput.assignment6.repository.IdentificationCheckRepository;
import za.ac.cput.assignment6.repository.Impl.IdentificationCheckRepositoryImpl;

/**
 * Created by sanXion on 2016/05/17.
 */
public class IdentificationCheckRepositoryImplTest extends AndroidTestCase {

    private static final String TAG="IdentificationCheck TEST";
    private Long id;


    public void testCreateReadUpdateDelete() throws Exception {
        IdentificationCheckRepository repo = new IdentificationCheckRepositoryImpl(this.getContext());


        IdentificationCheck identificationCheck = new IdentificationCheck.Builder()
                .response("approved")
                .build();

        IdentificationCheck inserttedChassis = repo.save(identificationCheck);
        id = inserttedChassis.getId();
        Assert.assertNotNull(TAG + " CREATE",inserttedChassis);

        //READ ALL
        Set<IdentificationCheck> allIdentificationCheck = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",allIdentificationCheck.size()>0);

        //READ ENTITY
        IdentificationCheck entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);


        //UPDATE ENTITY
        IdentificationCheck updateEntity = new IdentificationCheck.Builder()
                .copy(entity)
                .response("disapproved")
                .build();
        repo.update(updateEntity);
        IdentificationCheck newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","disapproved",newEntity.getResponse());

        // DELETE ENTITY
        repo.delete(inserttedChassis);
        //repo.delete(updateEntity);
        IdentificationCheck deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);
    }

}