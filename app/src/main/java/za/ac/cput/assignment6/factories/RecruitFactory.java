package za.ac.cput.assignment6.factories;

import za.ac.cput.assignment6.domain.ContactDetails;
import za.ac.cput.assignment6.domain.FullNameDetails;
import za.ac.cput.assignment6.domain.Recruit;

/**
 * Created by sanXion on 2016/05/11.
 */
public interface RecruitFactory {
    Recruit createRecruit(Long id, String southAfricanID, FullNameDetails fullNameDetails, ContactDetails contactDetails);
}
