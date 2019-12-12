package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "word")
@NamedQueries({
        @NamedQuery(name =WordRecordsEntity.GET_WORD_ALL_RECORDS, query = "SELECT c FROM WordRecordsEntity c order by c.id desc"),
        @NamedQuery(name = WordRecordsEntity.GET_WORD_SINGLE_RECORD, query = "SELECT c FROM WordRecordsEntity c WHERE c.id = :id"),
})
public class WordRecordsEntity implements Serializable {

    public static final String GET_WORD_ALL_RECORDS = "getWordAllRecords";
    public static final String GET_WORD_SINGLE_RECORD = "getWordSingleRecord";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "phrase")
    private String phrase;

    @Column(name = "result")
    private String result;

    @Column(name = "method")
    private String method;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "operatorDate")
    private Date operatorDate;


    public WordRecordsEntity(){}

    public WordRecordsEntity(String phrase, String result, String method) {
        this.phrase = phrase;
        this.result = result;
        this.method = method;
        this.operatorDate = new Date();
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
