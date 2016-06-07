package za.ac.cput.assignment6.repositories;

import android.test.AndroidTestCase;

import junit.framework.Assert;

import java.util.Set;

import za.ac.cput.assignment6.domain.DetailsCheck;
import za.ac.cput.assignment6.repository.DetailsCheckRepository;
import za.ac.cput.assignment6.repository.Impl.DetailsCheckRepositoryImpl;

/**
 * Created by sanXion on 2016/05/17.
 */
public class DetailsCheckRepositoryImplTest extends AndroidTestCase {

    private static final String TAG="DetailsCheck TEST";
    private Long id;


    public void testCreateReadUpdateDelete() throws Exception {
        DetailsCheckRepository repo = new DetailsCheckRepositoryImpl(this.getContext());


        DetailsCheck detailsCheck = new DetailsCheck.Builder()
                .response("pass")
                .build();

        DetailsCheck inserttedChassis = repo.save(detailsCheck);
        id = inserttedChassis.getId();
        Assert.assertNotNull(TAG + " CREATE",inserttedChassis);

        //READ ALL
        Set<DetailsCheck> allDetailsCheck = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",allDetailsCheck.size()>0);

        //READ ENTITY
        DetailsCheck entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);


        //UPDATE ENTITY
        DetailsCheck updateEntity = new DetailsCheck.Builder()
                .copy(entity)
                .response("fail")
                .build();
        repo.update(updateEntity);
        DetailsCheck newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","fail",newEntity.getResponse());

        // DELETE ENTITY
        repo.delete(inserttedChassis);
        //repo.delete(updateEntity);
        DetailsCheck deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);
    }
    
}
