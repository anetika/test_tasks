package org.example.service;

import org.example.model.Login;
import org.example.model.Posting;

import java.util.List;

public interface PostingService {

    List<Posting> saveAll(List<Posting> postings);

    List<Posting> setUpIsAuthorizedPostings(List<Posting> postings, List<Login> logins);

    List<Posting> getPostingsByPeriodAndIsAuthorized(String startDate, String endDate, boolean isAuthorized);
}
