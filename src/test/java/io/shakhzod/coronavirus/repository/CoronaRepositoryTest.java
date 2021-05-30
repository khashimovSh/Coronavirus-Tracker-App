package io.shakhzod.coronavirus.repository;

import io.shakhzod.coronavirus.models.LocationStats;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
class CoronaRepositoryTest {

    @Autowired
    private CoronaRepository coronaRepository;

    @AfterEach
    void tearDown() {
        coronaRepository.deleteAll();
    }

    @Test
    void itShouldFindColumnWithCountryName()
    {
        // given
        String country = "Atlanta";
        LocationStats exampleCountry = new LocationStats();
        exampleCountry.setCountry(country);
        exampleCountry.setState("United Eden");
        exampleCountry.setDiffFromPrevDay(0);
        exampleCountry.setLatestTotalCases(0);
        coronaRepository.save(exampleCountry);
        LocationStats exampleCountry2 = new LocationStats();
        exampleCountry2.setCountry(country);
        exampleCountry2.setState("Paradise");
        exampleCountry2.setDiffFromPrevDay(0);
        exampleCountry2.setLatestTotalCases(0);
        coronaRepository.save(exampleCountry2);

        // when
        List<LocationStats> expected = coronaRepository.findAllByCountry(country);

        // then
        assertThat(expected).isNotEqualTo(null);
        assertThat(expected.size()).isEqualTo(2);
        assertThat(expected.get(0).getCountry()).isEqualTo("Atlanta");
        assertThat(expected.get(0).getState()).isEqualTo("United Eden");
        assertThat(expected.get(1).getState()).isEqualTo("Paradise");
    }
}