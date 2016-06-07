package za.ac.cput.assignment6.services;

import java.util.ArrayList;

import za.ac.cput.assignment6.domain.Recruit;

/**
 * Created by sanXion on 2016/06/06.
 */
public interface RecruitService {
    Recruit createRecruit(Recruit recruit);
    boolean updateRecruitDetails(Recruit recruit);
    ArrayList<Recruit> findBySouthAfricanID(String southAfricanID);
}
