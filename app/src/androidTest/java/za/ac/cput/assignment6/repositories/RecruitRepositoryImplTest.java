package za.ac.cput.assignment6.repositories;

import android.test.AndroidTestCase;

import junit.framework.Assert;

import java.util.Set;

import za.ac.cput.assignment6.domain.ContactDetails;
import za.ac.cput.assignment6.domain.FullNameDetails;
import za.ac.cput.assignment6.domain.Recruit;
import za.ac.cput.assignment6.repository.Impl.RecruitRepositoryImpl;
import za.ac.cput.assignment6.repository.RecruitRepository;

/**
 * Created by sanXion on 2016/05/18.
 */
public class RecruitRepositoryImplTest extends AndroidTestCase {

    private static final String TAG="Recruit TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        RecruitRepository repo = new RecruitRepositoryImpl(this.getContext());

        FullNameDetails fullNameDetails = new FullNameDetails.Builder()
                .firstName("Rob")
                .lastName("Ash")
                .build();

        ContactDetails contactDetails = new ContactDetails.Builder()
                .homeNumber("0217832334")
                .emailAddress("ashr@gmail.com")
                .build();

        Recruit recruit = new Recruit.Builder()
                .southAfricanID("9105166701088")
                .fullNameDetails(fullNameDetails)
                .contactDetails(contactDetails)
                .build();

        Recruit inserttedChassis = repo.save(recruit);
        id = inserttedChassis.getId();
        Assert.assertNotNull(TAG + " CREATE",inserttedChassis);
        Assert.assertNotNull(TAG + " CREATE",inserttedChassis.getFullNameDetails());
        Assert.assertNotNull(TAG + " CREATE",inserttedChassis.getContactDetails());

        //READ ALL
        Set<Recruit> allRecruit = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",allRecruit.size()>0);

        //READ ENTITY
        Recruit entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);


        //UPDATE ENTITY
        Recruit updateEntity = new Recruit.Builder()
                .copy(entity)
                .southAfricanID("8905166701088")
                .build();
        repo.update(updateEntity);
        Recruit newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","8905166701088",newEntity.getSouthAfricanID());

        // DELETE ENTITY
        repo.delete(inserttedChassis);
        //repo.delete(updateEntity);
        Recruit deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);
    }
}