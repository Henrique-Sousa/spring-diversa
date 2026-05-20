package br.com.henriquesousa.diversa21.setup;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import br.com.henriquesousa.diversa21.entity.City;
import br.com.henriquesousa.diversa21.entity.Country;
import br.com.henriquesousa.diversa21.entity.State;
import br.com.henriquesousa.diversa21.repository.CityRepository;
import br.com.henriquesousa.diversa21.repository.CountryRepository;
import br.com.henriquesousa.diversa21.repository.StateRepository;

@Component
public class LocationSeeder implements Seeder {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocationSeeder.class);

    private final CountryRepository countryRepository;
    private final StateRepository stateRepository;
    private final CityRepository cityRepository;

    City campoGrande;
    City cantanhede;
    City uberlandia;
    City porto;

    public LocationSeeder(
            CountryRepository countryRepository,
            StateRepository stateRepository,
            CityRepository cityRepository
            ) {
        this.countryRepository = countryRepository;
        this.stateRepository = stateRepository;
        this.cityRepository = cityRepository;
    }

    @Override
    public void seed() {
        try {
            Country brasil = countryRepository.findByCode("BR").orElseGet(() -> {
                Country c = new Country();
                c.setUid(UUID.fromString("82892d93-4e91-4f53-9ede-bce5902db19c"));
                c.setName("Brazil");
                c.setCode("BR");
                LOGGER.debug("LocationSeeder:: Saving new country: Brazil");
                return countryRepository.saveAndFlush(c);
            });

            Country portugal = countryRepository.findByCode("PT").orElseGet(() -> {
                Country c = new Country();
                c.setUid(UUID.fromString("67afd9d1-3753-4e7d-8526-88e4f4a9fae2"));
                c.setName("Portugal");
                c.setCode("PT");
                LOGGER.debug("LocationSeeder:: Saving new country: Portugal");
                return countryRepository.saveAndFlush(c);
            });

            State matoGrossoDoSul = stateRepository.findByCode("MS").orElseGet(() -> {
                State s = new State();
                s.setUid(UUID.fromString("018a0996-dbea-4f15-9ac1-e24a77d86abb"));
                s.setName("Mato Grosso do Sul");
                s.setCode("MS");
                s.setCountry(brasil);
                LOGGER.debug("LocationSeeder:: Inserted state: MS");
                return stateRepository.saveAndFlush(s);
            });

            State minasGerais = stateRepository.findByCode("MG").orElseGet(() -> {
                State s = new State();
                s.setUid(UUID.fromString("b2fdf24d-a274-49dc-a3d3-94c09089c481"));
                s.setName("Minas Gerais");
                s.setCode("MG");
                s.setCountry(brasil);
                LOGGER.debug("LocationSeeder:: Inserted state: MG");
                return stateRepository.saveAndFlush(s);
            });

            State douroLitoral = stateRepository.findByCode("DL").orElseGet(() -> {
                State s = new State();
                s.setUid(UUID.fromString("6ebdc61a-4160-487d-96ed-4d06d3d0cbd5"));
                s.setName("Douro Litoral");
                s.setCode("DL");
                s.setCountry(portugal);
                LOGGER.debug("LocationSeeder:: Inserted state: DL");
                return stateRepository.saveAndFlush(s);
            });

            State coimbra = stateRepository.findByCode("CO").orElseGet(() -> {
                State s = new State();
                s.setUid(UUID.fromString("f006d303-9a57-44a0-9c90-6674c1e93b66"));
                s.setName("Coimbra");
                s.setCode("CO");
                s.setCountry(portugal);
                LOGGER.debug("LocationSeeder:: Inserted state: CO");
                return stateRepository.saveAndFlush(s);
            });

            UUID campoGrandeUid = UUID.fromString("2bbb1fb0-6e97-4b3e-aa20-cae18c2dcfaf");
            campoGrande = cityRepository.findByUid(campoGrandeUid).orElseGet(() -> {
                City c = new City();
                c.setUid(campoGrandeUid);
                c.setName("Campo Grande");
                c.setState(matoGrossoDoSul);
                LOGGER.debug("LocationSeeder:: Inserted city: Campo Grande");
                return cityRepository.saveAndFlush(c);
            });

            if (!cityRepository.existsByUid(UUID.fromString("3d38dc97-7fb5-4993-9059-7dc26b0a1ac6"))) {
                City beloHorizonte = new City();
                beloHorizonte.setUid(UUID.fromString(("3d38dc97-7fb5-4993-9059-7dc26b0a1ac6")));
                beloHorizonte.setName("Belo Horizonte");
                beloHorizonte.setState(minasGerais);
                cityRepository.saveAndFlush(beloHorizonte);
                LOGGER.debug("LocationSeeder:: Inserted city: Belo Horizonte");
            }

            UUID uberlandiaUid = UUID.fromString("8122c4f2-2665-4814-8592-3d2e49efdcd2");
            uberlandia = cityRepository.findByUid(uberlandiaUid).orElseGet(() -> {
                City c = new City();
                c.setUid(uberlandiaUid);
                c.setName("Uberlândia");
                c.setState(minasGerais);
                LOGGER.debug("LocationSeeder:: Inserted city: Uberlândia");
                return cityRepository.saveAndFlush(c);
            });

            if (!cityRepository.existsByUid(UUID.fromString("b71ee106-b4f7-4e36-961a-e0c0c406f2fe"))) {
                City figueiraDaFoz = new City();
                figueiraDaFoz.setUid(UUID.fromString(("b71ee106-b4f7-4e36-961a-e0c0c406f2fe")));
                figueiraDaFoz.setName("Figueira da Foz");
                figueiraDaFoz.setState(coimbra);
                cityRepository.saveAndFlush(figueiraDaFoz);
                LOGGER.debug("LocationSeeder:: Inserted city: Figueira da Foz");
            }

            UUID cantanhedeUid = UUID.fromString("512e7b04-4627-40d2-9dcf-3ec6b73a6b73");
            cantanhede = cityRepository.findByUid(cantanhedeUid).orElseGet(() -> {
                City c = new City();
                c.setUid(cantanhedeUid);
                c.setName("Cantanhede");
                c.setState(coimbra);
                LOGGER.debug("LocationSeeder:: Inserted city: Cantanhede");
                return cityRepository.saveAndFlush(c);
            });

            UUID portoUid = UUID.fromString("c8546d8a-d35c-4661-841f-58d207344872");
            porto = cityRepository.findByUid(portoUid).orElseGet(() -> {
                City c = new City();
                c.setUid(portoUid);
                c.setName("Porto");
                c.setState(douroLitoral);
                LOGGER.debug("LocationSeeder:: Inserted city: Porto");
                return cityRepository.saveAndFlush(c);
            });

            if (!cityRepository.existsByUid(UUID.fromString("cf954069-9904-4659-92de-c9e673c50879"))) {
                City vilaNovaDeGaia = new City();
                vilaNovaDeGaia.setUid(UUID.fromString(("cf954069-9904-4659-92de-c9e673c50879")));
                vilaNovaDeGaia.setName("Vila Nova de Gaia");
                vilaNovaDeGaia.setState(douroLitoral);
                cityRepository.saveAndFlush(vilaNovaDeGaia);
                LOGGER.debug("LocationSeeder:: Inserted city: Vila Nova de Gaia");
            }

        } catch (Exception e) {
            LOGGER.warn("There was an error while seeding location data", e);
        }
    }
}
