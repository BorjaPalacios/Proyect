package pojos.records;

import entities.WordRecordsEntity;
import utils.MethodParser;

import java.util.Date;

public class WordRecordsPojo {

    private Long id;
    private String phrase;
    private String result;
    private String method;
    private Date operatorDate;


    public WordRecordsPojo(WordRecordsEntity entity) {
        this.id = entity.getId();
        this.phrase = entity.getPhrase();
        this.result = entity.getResult();
        this.method = entity.getMethod();
        this.operatorDate = entity.getOperatorDate();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Date getOperatorDate() {
        return operatorDate;
    }

    public void setOperatorDate(Date operatorDate) {
        this.operatorDate = operatorDate;
    }
}
