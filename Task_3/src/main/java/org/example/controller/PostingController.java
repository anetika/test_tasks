package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.model.Posting;
import org.example.service.PostingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostingController {

    private final PostingService postingService;

    @GetMapping("/postings")
    public List<Posting> getPostingsByPeriod(@RequestParam String startDate,
                                             @RequestParam String endDate,
                                             @RequestParam boolean isAuthorized) {
        return postingService.getPostingsByPeriodAndIsAuthorized(startDate, endDate, isAuthorized);
    }
}
