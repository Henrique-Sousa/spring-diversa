package br.com.henriquesousa.diversa21.setup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseSeeder.class);

    private final LocationSeeder locationSeeder;
    private final InstitutionSeeder institutionSeeder; 

    public DatabaseSeeder(LocationSeeder locationSeeder, InstitutionSeeder institutionSeeder) {
        this.locationSeeder = locationSeeder;
        this.institutionSeeder = institutionSeeder;
    }

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("Starting database initialization...");

        locationSeeder.seed();
        institutionSeeder.seed();

        LOGGER.info("Database initialization completed successfully.");
    }
}
