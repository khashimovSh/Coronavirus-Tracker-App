package io.shakhzod.coronavirus.repository;

import io.shakhzod.coronavirus.models.LocationStats;
import org.springframework.data.repository.CrudRepository;


public interface CoronaRepository extends CrudRepository<LocationStats,Long> {

}
