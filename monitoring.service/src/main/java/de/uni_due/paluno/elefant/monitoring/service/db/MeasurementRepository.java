package de.uni_due.paluno.elefant.monitoring.service.db;

import de.uni_due.paluno.elefant.monitoring.service.model.Measurement;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @author Ole Meyer
 */

public interface MeasurementRepository extends MongoRepository<Measurement,String> {
}
