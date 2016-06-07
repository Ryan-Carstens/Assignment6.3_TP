package za.ac.cput.assignment6.factories;

import za.ac.cput.assignment6.domain.RegistrationEntry;

/**
 * Created by sanXion on 2016/05/11.
 */
public interface RegistrationEntryFactory {
    RegistrationEntry createRegistrationEntry(Long id, String southAfricanID, String gender, String placementChoice);
}
