package za.ac.cput.assignment6;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import za.ac.cput.assignment6.factories.ContractTest;
import za.ac.cput.assignment6.factories.DetailsCheckTest;
import za.ac.cput.assignment6.factories.IdentificationCheckTest;
import za.ac.cput.assignment6.factories.RecruitTest;
import za.ac.cput.assignment6.factories.RegistrationEntryTest;
import za.ac.cput.assignment6.factories.RegistryManagerTest;


/**
 * Created by Ryan Carstens 213133040.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        ContractTest.class,
        DetailsCheckTest.class,
        IdentificationCheckTest.class,
        RecruitTest.class,
        RegistrationEntryTest.class,
        RegistryManagerTest.class})
public class AppUnitTestSuite {
}
