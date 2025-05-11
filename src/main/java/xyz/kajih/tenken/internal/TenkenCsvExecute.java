package xyz.kajih.tenken.internal;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

@Slf4j
@Component
public class TenkenCsvExecute implements CommandLineRunner {

   public record Person(String name, int age, String email) {}

    private final static String CSV = """
            Bertie;21;bert@email
            Simon;22;simon@email
            Jenny;19;jenny@email
            """;

    public TenkenCsvExecute() {
    }

    @Override
    public void run(String... args) throws IOException {
        log.info("Tenken CommandLine");

        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = CsvSchema.builder()
                .addColumn("name")
                .addColumn("age")
                .addColumn("email")
                .setUseHeader(false)
                .setColumnSeparator(';')
                .build();

        MappingIterator<Person> it = mapper
                .readerFor(Person.class)
                .with(schema)
                .readValues(new StringReader(CSV));


        List<Person> people = it.readAll();

        people.forEach(p -> System.out.println(p.name() + " - " + p.age() + " - " + p.email()));

    }
}
