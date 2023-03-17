package org.example.utils;

import lombok.RequiredArgsConstructor;
import org.example.model.Login;
import org.example.model.Posting;
import org.example.reader.CustomCSVReader;
import org.example.service.LoginService;
import org.example.service.PostingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LoadDataOnStartUp {

    private static final Logger LOG = LoggerFactory.getLogger(LoadDataOnStartUp.class);

    private static final String LOGINS_FILENAME = "logins.csv";
    private static final String POSTINGS_FILENAME = "postings.csv";

    private final CustomCSVReader reader;
    private final LoginService loginService;
    private final PostingService postingService;

    @EventListener(ApplicationReadyEvent.class)
    public void loadData() {
        List<Login> logins = reader.readLogins(LOGINS_FILENAME);
        List<Posting> postings = reader.readPostings(POSTINGS_FILENAME);
        LOG.info("All data was successfully read!");

        postings = postingService.setUpIsAuthorizedPostings(postings, logins);

        saveLoginsToDatabase(logins);
        savePostingsToDatabase(postings);
        LOG.info("All data was successfully imported to database!");
    }

    private void saveLoginsToDatabase(List<Login> logins) {
        loginService.saveAll(logins);
    }

    private void savePostingsToDatabase(List<Posting> postings) {
        postingService.saveAll(postings);
    }
}
