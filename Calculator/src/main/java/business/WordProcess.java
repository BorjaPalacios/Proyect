package business;


import database.WordDbBean;
import enums.ErrorCode;
import enums.MethodType;
import exception.CalculatorException;
import org.apache.commons.lang3.text.WordUtils;
import org.apache.log4j.Logger;
import pojos.records.WordRecordsPojo;
import utils.MethodParser;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import java.util.List;

@Stateless(name = WordProcess.BEAN_NAME)
public class WordProcess {

    @Inject
    private WordDbBean wordDbBean;

    public static final String BEAN_NAME = "wordProcess";

    @Inject
    Logger logger;

    public WordRecordsPojo process(String phrase, MethodType method, boolean update, Long id) throws Exception{
        try{
            String result = "";
            WordRecordsPojo pojo = null;

            if (method == MethodType.LOWER) result = phrase.toLowerCase();
            else if (method == MethodType.CAMEL) result = toCamel(phrase);
            else if (method == MethodType.FCAMEL) result = fromCamel(phrase);
            else result = phrase.toUpperCase();

            if(update == false) {
                pojo = wordDbBean.createRecord(phrase, result, MethodParser.valueOf(method));
            }else{
                pojo = wordDbBean.updateRecord(phrase, result, MethodParser.valueOf(method), id);
            }

            return pojo;
        }catch (Exception e){
            logger.error("Error: " + e.getMessage());
            throw new CalculatorException(ErrorCode.UNKNOW_ERROR);
        }
    }

    public String toCamel(String phrase) throws CalculatorException{
        try{
            String capitalize = WordUtils.capitalizeFully(phrase);
            capitalize = capitalize.replace(" ", "");

            return capitalize;

        }catch (Exception e){
            logger.error("Error: " + e.getMessage());
            throw new CalculatorException(ErrorCode.UNKNOW_ERROR);
        }
    }

    public String fromCamel(String phrase) throws CalculatorException{
        try{
            for(int i = 1; i < phrase.length(); i++){
                if(Character.isUpperCase(phrase.charAt(i))){
                    String phraseInitial = phrase.substring(0,i);
                    String phraseFinal = phrase.substring(i).
                            replace(phrase.charAt(i)+"", " "+Character.toLowerCase(phrase.charAt(i)));
                    phrase = phraseInitial + phraseFinal;
                    i = 1;
                }
            }

            return phrase;
            }catch (Exception e){
                logger.error("Error: " + e.getMessage());
                throw new CalculatorException(ErrorCode.UNKNOW_ERROR);
            }
    }

    public List<WordRecordsPojo> getAllRecords(Integer limit, Integer offset) throws Exception {
        try {
            return wordDbBean.getAllRecords(limit, offset);
        } catch (Exception e){
            logger.error("Error getting all records: " + e.getMessage());
            throw new CalculatorException(ErrorCode.UNKNOW_ERROR);
        }
    }

    public WordRecordsPojo getSingleRecord (Long id) throws Exception {
        try {
            WordRecordsPojo record = wordDbBean.getSingleRecord(id);
            if (record == null) throw new NoResultException();
            return record;

        }catch (NoResultException nr){
            throw new CalculatorException(ErrorCode.NOT_FOUND);
        }catch (Exception e){
            throw e;
        }
    }

    public boolean removeRecord (Long id) throws Exception{
        try{
            return wordDbBean.removeRecord(id);

        } catch (NoResultException nr) {
            throw new CalculatorException(ErrorCode.NOT_FOUND);
        }catch (Exception e){
            throw new CalculatorException(ErrorCode.UNKNOW_ERROR);
        }

    }

    public WordRecordsPojo caesarProcess (String phrase, Integer convert) throws CalculatorException{
        try{
            final int longitud = 26, minus = 97, mayus = 65;
            String phraseConvert = "";

            for (int i = 0; i < phrase.length(); i++){
                Character actualChar = phrase.charAt(i);

                if(!Character.isLetter(actualChar)) {
                    phraseConvert += actualChar;
                    continue;
                }

                int letra = (int) actualChar - (Character.isUpperCase(actualChar) ? mayus: minus);
                int nuevaPos = (letra + convert) % longitud;

                phraseConvert += Character.toString((char) (nuevaPos + (Character.isUpperCase(actualChar) ? mayus : minus)));
            }

            return wordDbBean.createRecord(phrase, phraseConvert, MethodParser.valueOf(MethodType.CAESAR));
        }catch (Exception e){
            throw new CalculatorException(ErrorCode.UNKNOW_ERROR);
        }
    }

    public WordRecordsPojo eraseProcess (String phrase, String word) throws CalculatorException{
        try{
            String newPhrase = phrase.replace(word, "");

            return wordDbBean.createRecord(phrase, newPhrase, MethodParser.valueOf(MethodType.ERASE));
        }catch (Exception e){
            throw new CalculatorException(ErrorCode.UNKNOW_ERROR);
        }
    }
}
