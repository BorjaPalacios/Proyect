package database;

import entities.WordRecordsEntity;
import org.apache.log4j.Logger;
import pojos.records.WordRecordsPojo;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;

@Stateless(name = WordDbBean.BEAN_NAME)
public class WordDbBean {

    public static final String BEAN_NAME = "wordDbBean";

    @Inject
    private transient Logger logger;

    @PersistenceContext
    private EntityManager entityManager;

    public WordRecordsPojo createRecord(String phrase, String result, String operator) throws Exception {

        try {
            WordRecordsEntity entity = new WordRecordsEntity(phrase, result, operator);
            entityManager.persist(entity);
            logger.info("Exito al grabar en la bbdd");

            return new WordRecordsPojo(entity);
        }catch (Exception e){
            logger.error("Error al grabar--" + e.getMessage());
            throw new Exception("error al grabar en la bbdd");
        }
    }

    public List<WordRecordsPojo> getAllRecords(Integer limit, Integer offset) throws Exception {

        try{
            List<WordRecordsPojo> records = new ArrayList<>();
            List<WordRecordsEntity> entities = entityManager
                    .createNamedQuery(WordRecordsEntity.GET_WORD_ALL_RECORDS, WordRecordsEntity.class)
                    .setFirstResult(offset).setMaxResults(limit).getResultList();

            for (WordRecordsEntity entity : entities) {
                records.add(new WordRecordsPojo(entity));
            }

            logger.info("exito al devolver records");

            return records;
        }catch (Exception e){
            logger.error("error al devolver records---" + e.getMessage());
            throw new Exception("error al devolver records");
        }
    }

    public WordRecordsPojo getSingleRecord(Long id) throws Exception {

        try {
            WordRecordsEntity entity = entityManager
                    .createNamedQuery(WordRecordsEntity.GET_WORD_SINGLE_RECORD, WordRecordsEntity.class)
                    .setParameter("id", id).getSingleResult();
            return new WordRecordsPojo(entity);

        }catch (NoResultException e){
            return null;
        }catch (Exception e){
            logger.error("error al deviolver un record: " + e.getMessage());
            throw  new Exception("error al devolver un record: " +e.getMessage());
        }
    }

    public boolean removeRecord(Long id) throws Exception {
        try {
            WordRecordsEntity entity = entityManager
                    .createNamedQuery(WordRecordsEntity.GET_WORD_SINGLE_RECORD, WordRecordsEntity.class)
                    .setParameter("id", id).getSingleResult();

            entityManager.remove(entity);
            return true;
        }catch (NoResultException e){
            throw e;
        }catch (Exception e){
            throw e;
        }
    }

    public WordRecordsPojo updateRecord(String phrase, String result, String method, Long id) throws Exception {
        try {
            WordRecordsEntity entity = entityManager.find(WordRecordsEntity.class, id);
            if(entity == null){
                throw new NotFoundException(String.valueOf(id));
            }

            entity.setPhrase(phrase);
            entity.setResult(result);
            entity.setMethod(method);

            entityManager.merge(entity);

            logger.info("Exito al grabar en la bbdd");

            return new WordRecordsPojo(entity);
        }catch (Exception e){
            logger.error("Error al grabar--" + e.getMessage());
            throw new Exception("error al grabar en la bbdd");
        }
    }

}
