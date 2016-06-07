package za.ac.cput.assignment6.factories;

import za.ac.cput.assignment6.domain.IdentificationCheck;

/**
 * Created by sanXion on 2016/05/11.
 */
public interface IdentificationCheckFactory {
    IdentificationCheck createIdentificationCheck(Long id, String response);
}
