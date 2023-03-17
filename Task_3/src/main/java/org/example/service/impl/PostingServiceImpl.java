package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.dao.PostingRepository;
import org.example.model.Login;
import org.example.model.Posting;
import org.example.service.PostingService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PostingServiceImpl implements PostingService {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private final PostingRepository postingRepository;
    @Override
    public List<Posting> saveAll(List<Posting> postings) {
        return postingRepository.saveAll(postings);
    }

    @Override
    public List<Posting> setUpIsAuthorizedPostings(List<Posting> postings, List<Login> logins) {
        postings.forEach(posting -> {
            posting.setAuthorized(logins.stream()
                    .anyMatch(login -> login.getAppAccountName().equals(posting.getUsername()) && login.isActive()));
        });
        return postings;
    }

    @Override
    public List<Posting> getPostingsByPeriodAndIsAuthorized(String startDate, String endDate, boolean isAuthorized) {
        LocalDate start = LocalDate.parse(startDate, DATE_FORMATTER);
        LocalDate end = LocalDate.parse(endDate, DATE_FORMATTER);
        return postingRepository.getPostingsByAuthorizedAndDocDateBeforeAndDocDateAfter(isAuthorized, start, end);
    }
}
