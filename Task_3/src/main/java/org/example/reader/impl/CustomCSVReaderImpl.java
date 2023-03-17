package org.example.reader.impl;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import org.example.model.Login;
import org.example.model.Posting;
import org.example.reader.CustomCSVReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Component
public class CustomCSVReaderImpl implements CustomCSVReader {

    private static final Logger LOG = LoggerFactory.getLogger(CustomCSVReaderImpl.class);
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    @Override
    public List<Login> readLogins(String filename) {
        try (CSVReader reader = new CSVReaderBuilder(new FileReader(new ClassPathResource(filename).getFile())).withSkipLines(1).build()) {
            return reader.readAll().stream().map(data -> Login.builder()
                    .application(data[0].trim())
                    .appAccountName(data[1].trim())
                    .isActive(Boolean.parseBoolean(data[2].trim().toLowerCase(Locale.ROOT)))
                    .jobTitle(data[3].trim())
                    .department(data[4].trim())
                    .build()).collect(Collectors.toList());
        } catch (IOException | CsvException e) {
            LOG.error("File not found!", e);
            throw new RuntimeException("File not found!");
        }
    }

    @Override
    public List<Posting> readPostings(String filename) {
        CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
        NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
        try (CSVReader reader = new CSVReaderBuilder(new FileReader(new ClassPathResource(filename).getFile())).withCSVParser(parser)
                .withSkipLines(1).build()) {
            return reader.readAll().stream().map(data -> {
                        try {
                            return Posting.builder()
                                    .postingNumber(data[0].trim())
                                    .item(Integer.parseInt(data[1].trim()))
                                    .docDate(LocalDate.parse(data[2].trim(), DATE_FORMATTER))
                                    .postingDate(LocalDate.parse(data[3].trim(), DATE_FORMATTER))
                                    .materialDescription(data[4].trim())
                                    .quantity(Integer.parseInt(data[5].trim()))
                                    .bun(data[6].trim())
                                    .price(format.parse(data[7].trim()).doubleValue())
                                    .currency(data[8].trim())
                                    .username(data[9].trim())
                                    .build();
                        } catch (ParseException e) {
                            LOG.error("Incorrect data formats!", e);
                            throw new RuntimeException(e);
                        }
                    })
                    .collect(Collectors.toList());
        } catch (IOException | CsvException e) {
            LOG.error("File not found!", e);
            throw new RuntimeException("File not found!", e);
        }
    }
}
