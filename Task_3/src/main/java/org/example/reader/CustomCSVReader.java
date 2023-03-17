package org.example.reader;

import org.example.model.Login;
import org.example.model.Posting;

import java.util.List;

public interface CustomCSVReader {

    List<Login> readLogins(String filename);

    List<Posting> readPostings(String filename);
}
