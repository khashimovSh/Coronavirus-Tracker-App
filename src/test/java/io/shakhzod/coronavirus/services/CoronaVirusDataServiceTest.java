package io.shakhzod.coronavirus.services;

import io.shakhzod.coronavirus.repository.CoronaRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CoronaVirusDataServiceTest {
    @Mock private CoronaRepository coronaRepository;
    private CoronaVirusDataService underTest;

    @BeforeEach
    void setUp() {
        underTest = new CoronaVirusDataService(coronaRepository);
    }

    @Test
    void canSearchByCountry() {
        // when
        underTest.searchByCountry("Atlanta");
        // then
        verify(coronaRepository).findAllByCountry("Atlanta");
    }
}