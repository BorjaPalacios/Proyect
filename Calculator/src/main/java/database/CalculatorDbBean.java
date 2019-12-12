package database;

import entities.CalculatorRecordsEntity;
import enums.OperatorType;
import org.apache.log4j.Logger;
import pojos.records.CalculatorRecordsPojo;
import utils.OperatorParser;


import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Stateless(name = CalculatorDbBean.BEAN_NAME)
public class CalculatorDbBean {

    public static final String BEAN_NAME = "calculatorDbBean";

    @Inject
    private transient Logger logger;

    @PersistenceContext
    private EntityManager entityManager;

    public CalculatorRecordsPojo createRecord(double input1, double input2, double result, String operator) throws Exception {

        try {
            CalculatorRecordsEntity entity = new CalculatorRecordsEntity(input1, input2, result, operator);
            entityManager.persist(entity);
            logger.info("Exito al grabar en la bbdd");

            return new CalculatorRecordsPojo(entity);
        }catch (Exception e){
            logger.error("Error al grabar--" + e.getMessage());
            throw new Exception("error al grabar en la bbdd");
        }
    }

    public List<CalculatorRecordsPojo> getAllRecords(Integer limit, Integer offset) throws Exception {

        try{
            List<CalculatorRecordsPojo> records = new ArrayList<>();
            List<CalculatorRecordsEntity> entities = entityManager
                    .createNamedQuery(CalculatorRecordsEntity.GET_ALL_RECORDS, CalculatorRecordsEntity.class)
                    .setFirstResult(offset).setMaxResults(limit).getResultList();

            for (CalculatorRecordsEntity entity : entities) {
                records.add(new CalculatorRecordsPojo(entity));
            }

            logger.info("exito al devolver records");

            return records;
        }catch (Exception e){
            logger.error("error al devolver records---" + e.getMessage());
            throw new Exception("error al devolver records");
        }
    }

    public List<CalculatorRecordsPojo> getOperationTypeRecords(OperatorType operator, Integer limit, Integer offset) throws Exception {

        try{
            List<CalculatorRecordsPojo> records = new ArrayList<>();
            List<CalculatorRecordsEntity> entities = entityManager
                    .createNamedQuery(CalculatorRecordsEntity.GET_OPERATIONTYPE_RECORDS, CalculatorRecordsEntity.class)
                    .setParameter( "operator", OperatorParser.valueOf(operator)).setFirstResult(offset).setMaxResults(limit).getResultList();

            for (CalculatorRecordsEntity entity : entities){
                records.add(new CalculatorRecordsPojo(entity));
            }

            logger.info("exito al devolver records");

            return records;
        }catch (Exception e){
            logger.error("error al devolver records---" + e.getMessage());
            throw new Exception("error al devolver records");
        }
    }

    public CalculatorRecordsPojo getSingleRecord(Long id) throws Exception {

        try {
            CalculatorRecordsEntity entity = entityManager
                    .createNamedQuery(CalculatorRecordsEntity.GET_SINGLE_RECORD, CalculatorRecordsEntity.class)
                    .setParameter("id", id).getSingleResult();
            return new CalculatorRecordsPojo(entity);

        }catch (NoResultException e){
            return null;
        }catch (Exception e){
            logger.error("error al deviolver un record: " + e.getMessage());
            throw  new Exception("error al devolver un record: " +e.getMessage());
        }
    }

    public boolean removeRecord(Long id) throws Exception {
        try {
            CalculatorRecordsEntity entity = entityManager
                    .createNamedQuery(CalculatorRecordsEntity.GET_SINGLE_RECORD, CalculatorRecordsEntity.class)
                    .setParameter("id", id).getSingleResult();

            entityManager.remove(entity);
            return true;
        }catch (NoResultException e){
            throw e;
        }catch (Exception e){
            throw e;
        }
    }


}
