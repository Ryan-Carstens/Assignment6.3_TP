package za.ac.cput.assignment6.factories;

import za.ac.cput.assignment6.domain.DetailsCheck;

/**
 * Created by sanXion on 2016/05/11.
 */
public interface DetailsCheckFactory {
    DetailsCheck createDetailsCheck(Long id, String response);
}
