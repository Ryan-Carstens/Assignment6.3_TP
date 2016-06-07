package za.ac.cput.assignment6.factories;

import za.ac.cput.assignment6.domain.FullNameDetails;
import za.ac.cput.assignment6.domain.LoginDetails;
import za.ac.cput.assignment6.domain.RegistryManager;

/**
 * Created by sanXion on 2016/05/11.
 */
public interface RegistryManagerFactory {
    RegistryManager createRegistryManager(Long id, String southAfricanID, FullNameDetails fullNameDetails, LoginDetails loginDetails);
}
