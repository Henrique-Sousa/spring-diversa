package br.com.henriquesousa.diversa21.setup;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.henriquesousa.diversa21.entity.Country;
import br.com.henriquesousa.diversa21.entity.State;
import br.com.henriquesousa.diversa21.repository.CountryRepository;
import br.com.henriquesousa.diversa21.repository.StateRepository;

@Component
public class LocationSeeder implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocationSeeder.class);

    private final CountryRepository countryRepository;
    private final StateRepository stateRepository;

    public LocationSeeder(CountryRepository countryRepository, StateRepository stateRepository) {
        this.countryRepository = countryRepository;
        this.stateRepository = stateRepository; 
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            Country country1 = countryRepository.findByCode("BR").orElseGet(() -> {
                Country c = new Country();
                c.setUid(UUID.fromString("82892d93-4e91-4f53-9ede-bce5902db19c"));
                c.setName("Brazil");
                c.setCode("BR");
                LOGGER.debug("LocationSeeder:: Saving new country: Brazil");
                return countryRepository.saveAndFlush(c);
            });

            Country country2 = countryRepository.findByCode("PT").orElseGet(() -> {
                Country c = new Country();
                c.setUid(UUID.fromString("67afd9d1-3753-4e7d-8526-88e4f4a9fae2"));
                c.setName("Portugal");
                c.setCode("PT");
                LOGGER.debug("LocationSeeder:: Saving new country: Portugal");
                return countryRepository.saveAndFlush(c);
            });

            if (!stateRepository.existsByCode("MS")) {
                State state1 = new State();
                state1.setUid(UUID.fromString("018a0996-dbea-4f15-9ac1-e24a77d86abb"));
                state1.setName("Mato Grosso do Sul");
                state1.setCode("MS");
                state1.setCountry(country1);
                stateRepository.saveAndFlush(state1);
                LOGGER.debug("LocationSeeder:: Inserted state: MS");
            }

            if (!stateRepository.existsByCode("MG")) {
                State state2 = new State();
                state2.setUid(UUID.fromString("b2fdf24d-a274-49dc-a3d3-94c09089c481"));
                state2.setName("Minas Gerais");
                state2.setCode("MG");
                state2.setCountry(country1);
                stateRepository.saveAndFlush(state2);
                LOGGER.debug("LocationSeeder:: Inserted state: MG");
            }

            if (!stateRepository.existsByCode("DL")) {
                State state3 = new State();
                state3.setUid(UUID.fromString("6ebdc61a-4160-487d-96ed-4d06d3d0cbd5"));
                state3.setName("Douro Litoral");
                state3.setCode("DL");
                state3.setCountry(country2);
                stateRepository.saveAndFlush(state3);
            }

            if (!stateRepository.existsByCode("CO")) {
                State state4 = new State();
                state4.setUid(UUID.fromString("f006d303-9a57-44a0-9c90-6674c1e93b66"));
                state4.setName("Coimbra");
                state4.setCode("CO");
                state4.setCountry(country2);
                stateRepository.saveAndFlush(state4);
            }

        } catch (Throwable e) {
            LOGGER.warn("There was an error while seeding location data", e);
        }
    }
}
