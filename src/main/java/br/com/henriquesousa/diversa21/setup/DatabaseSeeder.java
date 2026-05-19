package br.com.henriquesousa.diversa21.setup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseSeeder.class);

    private final LocationSeeder locationSeeder;

    public DatabaseSeeder(LocationSeeder locationSeeder) {
        this.locationSeeder = locationSeeder;
    }

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("Starting database initialization...");

        locationSeeder.seed();

        LOGGER.info("Database initialization completed successfully.");
    }
}
