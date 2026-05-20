package br.com.henriquesousa.diversa21.setup;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import br.com.henriquesousa.diversa21.entity.Institution;
import br.com.henriquesousa.diversa21.repository.InstitutionRepository;

@Component
public class InstitutionSeeder implements Seeder {

    private static final Logger LOGGER = LoggerFactory.getLogger(InstitutionSeeder.class);

    private final InstitutionRepository institutionRepository;
    private final LocationSeeder locationSeeder;

    public InstitutionSeeder(
            InstitutionRepository institutionRepository,
            LocationSeeder locationSeeder
    ) {
        this.institutionRepository = institutionRepository;
        this.locationSeeder = locationSeeder;
    }

    @Override
    public void seed() {
        try {
            if(!institutionRepository.existsByEmail("contato@saorafael.com")) {
                Institution i = new Institution();
                i.setUid(UUID.fromString("2e2849c2-e8ce-447a-acfe-73e88ddc3bb6"));
                i.setName("Clínica São Rafael");
                i.setSlug("Clinica-Sao-Rafael");
                i.setDescription("Clínica São Rafael, manda email aqui: contato@saorafael.com.");
                i.setAddress("Av. das Flores, 123");
                i.setCity(locationSeeder.campoGrande);
                i.setTelephone("2147483647");
                i.setPostalCode("SR2001");
                i.setEmail("contato@saorafael.com");
                i.setWebsite("https://saorafael.org");
                LOGGER.debug("InstitutionSeeder:: Saving new institution: Clínica São Rafael");
                institutionRepository.saveAndFlush(i);
            }

            if(!institutionRepository.existsByEmail("imaginacao@centroimagem.com")) {
                Institution i = new Institution();
                i.setUid(UUID.fromString("4f57c799-f086-49c3-800e-e76cb9673b89"));
                i.setName("Centro de Imagem");
                i.setSlug("Centro-de-Imagem");
                i.setDescription("Centro de Imagem Teste, manda email aqui: imaginacao@centroimagem.com.");
                i.setAddress("Rua do Diagnóstico, 45");
                i.setCity(locationSeeder.cantanhede);
                i.setTelephone("2147483647");
                i.setPostalCode("CI3002");
                i.setEmail("imaginacao@centroimagem.com");
                i.setWebsite("https://centroimagem.test");
                LOGGER.debug("InstitutionSeeder:: Saving new institution: Centro de Imagem");
                institutionRepository.saveAndFlush(i);
            }

            if(!institutionRepository.existsByEmail("lab@modelolabs.com")) {
                Institution i = new Institution();
                i.setUid(UUID.fromString("23afc11a-8597-4e0d-a26d-be57db923f7f"));
                i.setName("Laboratório Modelo");
                i.setSlug("Laboratorio-Modelo");
                i.setDescription("Laboratório Modelo, manda email aqui: lab@modelolabs.com.");
                i.setAddress("Alameda dos Testes, 78");
                i.setCity(locationSeeder.uberlandia);
                i.setTelephone("2147483647");
                i.setPostalCode("LM4003");
                i.setEmail("lab@modelolabs.com");
                i.setWebsite("https://modelolabs.example");
                LOGGER.debug("InstitutionSeeder:: Saving new institution: Laboratório Modelo");
                institutionRepository.saveAndFlush(i);
            }

            if(!institutionRepository.existsByEmail("emergencia@urgenciacentral.com")) {
                Institution i = new Institution();
                i.setUid(UUID.fromString("11ca5279-c686-4061-8e57-5c661309dd0b"));
                i.setName("Urgência Central");
                i.setSlug("Urgencia-Central");
                i.setDescription("Urgência Central Teste, manda email aqui: emergencia@urgenciacentral.com.");
                i.setAddress("Praça da Emergência, s/n");
                i.setCity(locationSeeder.porto);
                i.setTelephone("2147483647");
                i.setPostalCode("UC5004");
                i.setEmail("emergencia@urgenciacentral.com");
                i.setWebsite("https://urgenciacentral.test");
                LOGGER.debug("InstitutionSeeder:: Saving new institution: Urgência Central");
                institutionRepository.saveAndFlush(i);
            }
        } catch (Exception e) {
            LOGGER.warn("There was an error while seeding location data", e);
        }
    }
}
