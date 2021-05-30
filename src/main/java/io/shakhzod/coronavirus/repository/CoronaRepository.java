package io.shakhzod.coronavirus.repository;

import io.shakhzod.coronavirus.models.LocationStats;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface CoronaRepository extends CrudRepository<LocationStats,Long> {
    List<LocationStats> findAllByCountry(String country);
}
