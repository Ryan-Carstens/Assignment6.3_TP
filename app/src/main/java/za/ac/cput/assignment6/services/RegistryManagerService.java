package za.ac.cput.assignment6.services;

import java.util.ArrayList;

import za.ac.cput.assignment6.domain.RegistryManager;

/**
 * Created by sanXion on 2016/05/31.
 */
public interface RegistryManagerService {
    RegistryManager createRegistryManager(RegistryManager registryManager);
    boolean updateRegistryManagerDetails(RegistryManager registryManager);
    ArrayList<RegistryManager> registryManagerLogin(String email, String password);
    ArrayList<RegistryManager> findByEmail(String email);
    boolean deleteAllRegistryManagers();
}
