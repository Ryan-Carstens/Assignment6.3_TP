package za.ac.cput.assignment6.repositories;

import junit.framework.Assert;

import android.test.AndroidTestCase;

import java.util.Set;

import za.ac.cput.assignment6.domain.Contract;
import za.ac.cput.assignment6.domain.ContractDetails;
import za.ac.cput.assignment6.repository.ContractRepository;
import za.ac.cput.assignment6.repository.Impl.ContractRepositoryImpl;

/**
 * Created by sanXion on 2016/05/15.
 */
public class ContractRepositoryImplTest extends AndroidTestCase {

    private static final String TAG="Contract TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        ContractRepository repo = new ContractRepositoryImpl(this.getContext());

        ContractDetails contractDetails = new ContractDetails.Builder()
                .contractType("Navy")
                .contractNum(12345)
                .build();

        Contract contract = new Contract.Builder()
                .IdCheckNum(1)
                .DetailsCheckNum(1)
                .contractDetails(contractDetails)
                .build();

        Contract inserttedChassis = repo.save(contract);
        id = inserttedChassis.getId();
        Assert.assertNotNull(TAG + " CREATE",inserttedChassis);
        Assert.assertNotNull(TAG + " CREATE",inserttedChassis.getContractDetails());

        //READ ALL
        Set<Contract> allContract = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",allContract.size()>0);

        //READ ENTITY
        Contract entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);


        //UPDATE ENTITY
        Contract updateEntity = new Contract.Builder()
                .copy(entity)
                .IdCheckNum(0)
                .build();
        repo.update(updateEntity);
        Contract newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY",0,newEntity.getIdCheckNum());

        // DELETE ENTITY
        //repo.delete(inserttedChassis);
        //repo.delete(updateEntity); this one leave commented
        //Contract deletedEntity = repo.findById(id);
        //Assert.assertNull(TAG+" DELETE",deletedEntity);
    }
}