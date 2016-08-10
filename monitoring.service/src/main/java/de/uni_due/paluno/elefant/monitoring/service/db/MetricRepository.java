package de.uni_due.paluno.elefant.monitoring.service.db;

import de.uni_due.paluno.elefant.monitoring.service.model.MetricDefinition;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Ole Meyer
 */
public interface MetricRepository extends MongoRepository<MetricDefinition,String> {
}
