package com.sensor.dao.impl;

import com.sensor.dao.GenericDao;
import com.sensor.dao.SensorDao;
import com.sensor.model.Sensor;
import com.sensor.model.type.SensorType;
import com.sensor.model.type.UnitType;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class SensorDaoImpl extends GenericDao<Sensor, Long> implements SensorDao {

    @Override
    @Transactional
    public List<Sensor> findBySearchCriteria(String searchCriteria, int currentPage, int limit) {
        FullTextEntityManager fullTextEntityManager
                = Search.getFullTextEntityManager(getSession());
        handleInterruptedException(fullTextEntityManager);
        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder()
                .forEntity(Sensor.class)
                .get();
        Query query = queryBuilder
                .keyword()
                .fuzzy()
                .onFields(defineFieldsForSearch(searchCriteria).toArray(String[]::new))
                .matching(searchCriteria)
                .createQuery();
        FullTextQuery jpaQuery
                = fullTextEntityManager.createFullTextQuery(query, Sensor.class)
                .setFirstResult(currentPage * limit)
                .setMaxResults(limit);
        return (List<Sensor>) jpaQuery.getResultList();
    }

    private List<String> defineFieldsForSearch(String searchCriteria) {
        List<String> commonFields = new ArrayList<>(Arrays.asList("name", "model", "description", "location"));
        Arrays.stream(SensorType.values())
                .filter(type -> type.getType().toLowerCase()
                        .contains(searchCriteria.toLowerCase()))
                .findFirst().ifPresent(type1 -> commonFields.add("type"));
        Arrays.stream(UnitType.values())
                .filter(type -> type
                        .getType()
                        .toLowerCase()
                        .contains(searchCriteria.toLowerCase()))
                .findFirst().ifPresent(unitType -> commonFields.add("unit"));
        return commonFields;
    }

    private void handleInterruptedException(FullTextEntityManager fullTextEntityManager) {
        try {
            fullTextEntityManager.createIndexer().startAndWait();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }
}
