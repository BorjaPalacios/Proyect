package webservices;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import enums.ErrorCode;
import enums.OperatorType;
import exception.CalculatorException;
import org.apache.log4j.Logger;
import pojos.records.CalculatorRecordsPojo;
import pojos.request.*;
import pojos.response.*;
import business.CalculatorProcess;
import pojos.response.SingletonResponse;
import singletons.CalculatorSingleton;
import utils.OperatorParser;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path(CalculatorWebServices.API_ROOT)
@Stateless(name = CalculatorWebServices.SERVICE_NAME)

public class CalculatorWebServices {

    public static final String SERVICE_NAME = "CalculatorWebServices";
    public static final String API_ROOT = "/calcule";

    @Inject
    private CalculatorProcess calculatorProcess;

    @Inject
    private CalculatorSingleton calculatorSingleton;

    @Inject
    private Logger logger;


    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    //@Token
    public Response AddNumber(@Context UriInfo uriInfo, String body) throws Exception{

        try {
            CalculatorRequest calculatorRequest = new Gson().fromJson(body, CalculatorRequest.class);
            CalculatorRecordsPojo pojo = calculatorProcess.calcule(calculatorRequest.getInput1(), calculatorRequest.getInput2(), OperatorType.ADD);
            CalculatorResponse calculatorResponse = new CalculatorResponse(pojo.getId(), pojo.getResult());

            String json = new Gson().toJson(calculatorResponse);


            return Response.created(URI.create(toRecordsUri(uriInfo) + pojo.getId())).entity(json).build();


        } catch (CalculatorException ce) {
            throw ce;
        }catch (NumberFormatException e) {
            logger.error("Error: " +e.getMessage());
            throw new CalculatorException(ErrorCode.ERROR_PARAM);
        } catch (JsonParseException e) {
            logger.error("Error: " + e.getMessage());
            throw new CalculatorException(ErrorCode.BAD_BODY);
        }catch (Exception e){
            logger.error("Error: " + e.getMessage());
            throw new CalculatorException(ErrorCode.UNKNOW_ERROR);
        }
    }

    @POST
    @Path("/subtract")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    //@Token
    public Response SubtractNumber(@Context UriInfo uriInfo, String body) throws Exception{

        try {
            CalculatorRequest calculatorRequest = new Gson().fromJson(body, CalculatorRequest.class);
            CalculatorRecordsPojo pojo = calculatorProcess.calcule(calculatorRequest.getInput1(), calculatorRequest.getInput2(), OperatorType.SUBTRACT);
            CalculatorResponse calculatorResponse = new CalculatorResponse(pojo.getId(), pojo.getResult());

            String json = new Gson().toJson(calculatorResponse);


            return Response.created(URI.create(toRecordsUri(uriInfo) + pojo.getId())).entity(json).build();

        } catch (CalculatorException ce) {
            throw ce;
        }catch (NumberFormatException e) {
            logger.error("Error: " +e.getMessage());
            throw new CalculatorException(ErrorCode.ERROR_PARAM);
        } catch (JsonParseException e) {
            logger.error("Error: " + e.getMessage());
            throw new CalculatorException(ErrorCode.BAD_BODY);
        }catch (Exception e){
            logger.error("Error: " + e.getMessage());
            throw new CalculatorException(ErrorCode.UNKNOW_ERROR);
        }
    }

    @POST
    @Path("/multiply")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    //@Token
    public Response MultiplyNumber(@Context UriInfo uriInfo, String body) throws Exception{

        try {
            CalculatorRequest calculatorRequest = new Gson().fromJson(body, CalculatorRequest.class);
            CalculatorRecordsPojo pojo = calculatorProcess.calcule(calculatorRequest.getInput1(), calculatorRequest.getInput2(), OperatorType.MULTIPLY);
            CalculatorResponse calculatorResponse = new CalculatorResponse(pojo.getId(), pojo.getResult());

            String json = new Gson().toJson(calculatorResponse);


            return Response.created(URI.create(toRecordsUri(uriInfo) + pojo.getId())).entity(json).build();

        } catch (CalculatorException ce) {
            throw ce;
        }catch (NumberFormatException e) {
            logger.error("Error: " +e.getMessage());
            throw new CalculatorException(ErrorCode.ERROR_PARAM);
        } catch (JsonParseException e) {
            logger.error("Error: " + e.getMessage());
            throw new CalculatorException(ErrorCode.BAD_BODY);
        }catch (Exception e){
            logger.error("Error: " + e.getMessage());
            throw new CalculatorException(ErrorCode.UNKNOW_ERROR);
        }
    }

    @POST
    @Path("/divide")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    //@Token
    public Response DivideNumber(@Context UriInfo uriInfo, String body) throws Exception{

        try {
            CalculatorRequest calculatorRequest = new Gson().fromJson(body, CalculatorRequest.class);

            if (calculatorRequest.getInput2() == 0) throw new CalculatorException(ErrorCode.DIVIDE_ZERO);

            CalculatorRecordsPojo pojo = calculatorProcess.calcule(calculatorRequest.getInput1(), calculatorRequest.getInput2(), OperatorType.DIVIDE);
            CalculatorResponse calculatorResponse = new CalculatorResponse(pojo.getId(), pojo.getResult());

            String json = new Gson().toJson(calculatorResponse);

            return Response.created(URI.create(toRecordsUri(uriInfo) + pojo.getId())).entity(json).build();

        } catch (CalculatorException ce) {
            throw ce;
        }catch (NumberFormatException e) {
            logger.error("Error: " +e.getMessage());
            throw new CalculatorException(ErrorCode.ERROR_PARAM);
        } catch (JsonParseException e) {
            logger.error("Error: " + e.getMessage());
            throw new CalculatorException(ErrorCode.BAD_BODY);
        }catch (Exception e){
            logger.error("Error: " + e.getMessage());
            throw new CalculatorException(ErrorCode.UNKNOW_ERROR);
        }
    }

    @POST
    @Path("/power")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    //@Token
    public Response PowerNumber(@Context UriInfo uriInfo, String body) throws Exception{

        try{
            CalculatorRequest calculatorRequest = new Gson().fromJson(body, CalculatorRequest.class);

            if( calculatorRequest.getInput1() == 0 && calculatorRequest.getInput2() == 0) throw new CalculatorException(ErrorCode.UNDERTERMINED);
            CalculatorRecordsPojo pojo = calculatorProcess.calcule(calculatorRequest.getInput1(), calculatorRequest.getInput2(), OperatorType.POWER);
            CalculatorResponse calculatorResponse = new CalculatorResponse(pojo.getId(), pojo.getResult());

            String json = new Gson().toJson(calculatorResponse);

            return Response.created(URI.create(toRecordsUri(uriInfo) + pojo.getId())).entity(json).build();

        } catch (CalculatorException ce) {
            throw ce;
        }catch (NumberFormatException e) {
            logger.error("Error: " +e.getMessage());
            throw new CalculatorException(ErrorCode.ERROR_PARAM);
        } catch (JsonParseException e) {
            logger.error("Error: " + e.getMessage());
            throw new CalculatorException(ErrorCode.BAD_BODY);
        }catch (Exception e){
            logger.error("Error: " + e.getMessage());
            throw new CalculatorException(ErrorCode.UNKNOW_ERROR);
        }
    }

    @POST
    @Path("/root")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    //@Token
    public Response RootNumber(@Context UriInfo uriInfo, String body) throws Exception{

        try{
            CalculatorRequest calculatorRequest = new Gson().fromJson(body, CalculatorRequest.class);

            if(calculatorRequest.getInput2() == 0) throw new CalculatorException(ErrorCode.INDICE_ZERO);
            if(calculatorRequest.getInput1() < 0) throw new CalculatorException(ErrorCode.NEGATIVE_NUMBER);

            CalculatorRecordsPojo pojo = calculatorProcess.calcule(calculatorRequest.getInput1(), calculatorRequest.getInput2(), OperatorType.ROOT);
            CalculatorResponse calculatorResponse = new CalculatorResponse(pojo.getId(), pojo.getResult());

            String json = new Gson().toJson(calculatorResponse);

            return Response.created(URI.create(toRecordsUri(uriInfo) + pojo.getId())).entity(json).build();

        } catch (CalculatorException ce) {
            throw ce;
        }catch (NumberFormatException e) {
            logger.error("Error: " +e.getMessage());
            throw new CalculatorException(ErrorCode.ERROR_PARAM);
        } catch (JsonParseException e) {
            logger.error("Error: " + e.getMessage());
            throw new CalculatorException(ErrorCode.BAD_BODY);
        }catch (Exception e){
            logger.error("Error: " + e.getMessage());
            throw new CalculatorException(ErrorCode.UNKNOW_ERROR);
        }
    }

    @POST
    @Path("/percentage")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
   // @Token
    public  Response PercentageNumber (@Context UriInfo uriInfo, String body) throws Exception{
        try {
            CalculatorRequest calculatorRequest = new Gson().fromJson(body, CalculatorRequest.class);
            CalculatorRecordsPojo pojo = calculatorProcess.calcule(calculatorRequest.getInput1(), calculatorRequest.getInput2(), OperatorType.PERCENTAGE);
            CalculatorResponse calculatorResponse = new CalculatorResponse(pojo.getId(), pojo.getResult());

            String json = new Gson().toJson(calculatorResponse);

            return Response.created(URI.create(toRecordsUri(uriInfo) + pojo.getId())).entity(json).build();

        } catch (CalculatorException ce) {
            throw ce;
        }catch (NumberFormatException e) {
            logger.error("Error: " +e.getMessage());
            throw new CalculatorException(ErrorCode.ERROR_PARAM);
        } catch (JsonParseException e) {
            logger.error("Error: " + e.getMessage());
            throw new CalculatorException(ErrorCode.BAD_BODY);
        }catch (Exception e){
            logger.error("Error: " + e.getMessage());
            throw new CalculatorException(ErrorCode.UNKNOW_ERROR);
        }

    }

    @POST
    @Path("/log")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    //@Token
    public Response LogNumber (@Context UriInfo uriInfo, String body) throws Exception
    {
        try{

            CalculatorRequest calculatorRequest = new Gson().fromJson(body, CalculatorRequest.class);

            if(calculatorRequest.getInput1() <= 0 || calculatorRequest.getInput2() <= 0) throw new CalculatorException(ErrorCode.LOG_NUMBER);

            CalculatorRecordsPojo pojo = calculatorProcess.calcule(calculatorRequest.getInput1(), calculatorRequest.getInput2(), OperatorType.LOG);
            CalculatorResponse calculatorResponse = new CalculatorResponse(pojo.getId(), pojo.getResult());

            String json = new Gson().toJson(calculatorResponse);

            return Response.created(URI.create(toRecordsUri(uriInfo) + pojo.getId())).entity(json).build();

        } catch (CalculatorException ce) {
            throw ce;
        }catch (NumberFormatException e) {
            logger.error("Error: " +e.getMessage());
            throw new CalculatorException(ErrorCode.ERROR_PARAM);
        } catch (JsonParseException e) {
            logger.error("Error: " + e.getMessage());
            throw new CalculatorException(ErrorCode.BAD_BODY);
        }catch (Exception e){
            logger.error("Error: " + e.getMessage());
            throw new CalculatorException(ErrorCode.UNKNOW_ERROR);
        }
    }

    @DELETE
    @Path("/remove/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    //@Token
    public Response remove(@PathParam("id") Long id ) throws CalculatorException{
        try{

            if(!calculatorProcess.removeRecord(id)) throw new NoResultException();

            return Response.ok().build();

        }catch (CalculatorException ce){
            throw ce;
        }catch (JsonParseException e){
            throw new CalculatorException(ErrorCode.BAD_BODY);
        }catch (Exception e){
            throw new CalculatorException(ErrorCode.UNKNOW_ERROR);
        }

    }

    @GET
    @Path("/records")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllRecords(@QueryParam("limit") @DefaultValue("10") Integer limit,
                                  @QueryParam("offset") @DefaultValue("0") Integer offset) throws Exception  {
        try {
            List<CalculatorRecordsPojo> records = calculatorProcess.getAllRecords(limit, offset);

            String json = new Gson().toJson(records);

            logger.info("Records webservices works");

            return Response.ok().entity(json).build();

        } catch (Exception e) {
            logger.error("Error on Records webservices: " + e.getMessage());
            throw new Exception("Error" + e.getMessage());
        }
    }

    @GET
    @Path("/records/operator/{operator}")
    @Produces (MediaType.APPLICATION_JSON)
    public Response getOperationTypeRecords(@PathParam("operator") String operator,
                                            @QueryParam("limit") @DefaultValue("10")  Integer limit,
                                            @QueryParam("offset") @DefaultValue("0") Integer offset) throws Exception {
        try{

            List<CalculatorRecordsPojo> records = calculatorProcess.getOperationTypeRecords(OperatorParser.parse(operator.toUpperCase()), limit, offset);

            String json = new Gson().toJson(records);

            logger.info("Records webservices works");

            return Response.ok().entity(json).build();

        } catch (Exception e) {
            logger.error("Error on Records webservices: " + e.getMessage());
            throw new Exception("Error" + e.getMessage());
        }
    }

    @GET
    @Path("/records/{id}")
    @Produces (MediaType.APPLICATION_JSON)
    public Response getSingleRecord(@PathParam("id") Long id) throws Exception {
        try {

            CalculatorRecordsPojo record = calculatorProcess.getSingleRecord(id);

            String json = new Gson().toJson(record);

            return Response.ok().entity(json).build();

        }catch (CalculatorException ce){
            throw ce;
        }catch (Exception e){
            logger.error("Error en records webservice single: " + e.getMessage());
            throw new Exception(e.getMessage());
        }
    }


    @GET
    @Path("/singleton")
    public Response getSingleton() throws Exception{
        try{
            SingletonResponse response = new SingletonResponse(calculatorSingleton.getOperationsInServer());

            String json = new Gson().toJson(response);

            return Response.ok().entity(json).build();

        }catch (Exception e){
            throw new CalculatorException(ErrorCode.UNKNOW_ERROR);
        }
    }

    @POST
    @Path("/table")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    //@Token
    public Response multiplyTable(@Context UriInfo uriInfo, String body) throws CalculatorException{
        try{
            CalculatorTableRequest request = new Gson().fromJson(body, CalculatorTableRequest.class);
            List<CalculatorTableResponse> responses = calculatorProcess.table(request.getNumber(), request.getLimit());

            String json = new Gson().toJson(responses);

            return Response.ok().entity(json).build();

        }catch (CalculatorException ce){
            throw ce;
        }catch (NumberFormatException e) {
            throw new CalculatorException(ErrorCode.ERROR_PARAM);
        }catch (JsonParseException e){
            throw new CalculatorException(ErrorCode.BAD_BODY);
        }catch (Exception e){
            throw new CalculatorException(ErrorCode.UNKNOW_ERROR);
        }

    }

    @POST
    @Path("/sin")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    //@Token
    public Response sin(@Context UriInfo uriInfo, String body) throws CalculatorException{
        try{
            CalculatorTrigonometryRequest request = new Gson().fromJson(body, CalculatorTrigonometryRequest.class);
            request.setInput1(Math.toRadians(request.getInput1()));
            CalculatorRecordsPojo pojo = calculatorProcess.calcule(request.getInput1(),0.0,OperatorType.SIN);
            CalculatorResponse response =  new CalculatorResponse(pojo.getId(),pojo.getResult());

            String json = new Gson().toJson(response);

            return Response.created(URI.create(toRecordsUri(uriInfo) + pojo.getId())).entity(json).build();

        } catch (CalculatorException ce) {
            throw ce;
        }catch (NumberFormatException e) {
            logger.error("Error: " +e.getMessage());
            throw new CalculatorException(ErrorCode.ERROR_PARAM);
        } catch (JsonParseException e) {
            logger.error("Error: " + e.getMessage());
            throw new CalculatorException(ErrorCode.BAD_BODY);
        }catch (Exception e){
            logger.error("Error: " + e.getMessage());
            throw new CalculatorException(ErrorCode.UNKNOW_ERROR);
        }
    }

    @POST
    @Path("/cos")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    //@Token
    public Response cos(@Context UriInfo uriInfo, String body) throws CalculatorException{
        try{
            CalculatorTrigonometryRequest request = new Gson().fromJson(body, CalculatorTrigonometryRequest.class);
            request.setInput1(Math.toRadians(request.getInput1()));
            CalculatorRecordsPojo pojo = calculatorProcess.calcule(request.getInput1(),0.0,OperatorType.COS);
            CalculatorResponse response =  new CalculatorResponse(pojo.getId(),pojo.getResult());

            String json = new Gson().toJson(response);

            return Response.created(URI.create(toRecordsUri(uriInfo) + pojo.getId())).entity(json).build();

        } catch (CalculatorException ce) {
            throw ce;
        }catch (NumberFormatException e) {
            logger.error("Error: " +e.getMessage());
            throw new CalculatorException(ErrorCode.ERROR_PARAM);
        } catch (JsonParseException e) {
            logger.error("Error: " + e.getMessage());
            throw new CalculatorException(ErrorCode.BAD_BODY);
        }catch (Exception e){
            logger.error("Error: " + e.getMessage());
            throw new CalculatorException(ErrorCode.UNKNOW_ERROR);
        }
    }

    @POST
    @Path("/tan")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    //@Token
    public Response tan(@Context UriInfo uriInfo, String body) throws CalculatorException{
        try{
            CalculatorTrigonometryRequest request = new Gson().fromJson(body, CalculatorTrigonometryRequest.class);
            request.setInput1(Math.toRadians(request.getInput1()));
            CalculatorRecordsPojo pojo = calculatorProcess.calcule(request.getInput1(),0.0,OperatorType.TAN);
            CalculatorResponse response =  new CalculatorResponse(pojo.getId(),pojo.getResult());

            String json = new Gson().toJson(response);

            return Response.created(URI.create(toRecordsUri(uriInfo) + pojo.getId())).entity(json).build();

        } catch (CalculatorException ce) {
            throw ce;
        }catch (NumberFormatException e) {
            logger.error("Error: " +e.getMessage());
            throw new CalculatorException(ErrorCode.ERROR_PARAM);
        } catch (JsonParseException e) {
            logger.error("Error: " + e.getMessage());
            throw new CalculatorException(ErrorCode.BAD_BODY);
        }catch (Exception e){
            logger.error("Error: " + e.getMessage());
            throw new CalculatorException(ErrorCode.UNKNOW_ERROR);
        }
    }

    @POST
    @Path("/factorial")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    //@Token
    public Response factorial(@Context UriInfo uriInfo, String body) throws CalculatorException{
        try{
            CalculatorTrigonometryRequest request = new Gson().fromJson(body, CalculatorTrigonometryRequest.class);
            CalculatorRecordsPojo pojo = calculatorProcess.calcule(request.getInput1(),0.0, OperatorType.FACTORIAL);
            CalculatorResponse response = new CalculatorResponse(pojo.getId(), pojo.getResult());

            String json = new Gson().toJson(response);

            return Response.created(URI.create(toRecordsUri(uriInfo) + pojo.getId())).entity(json).build();

        } catch (CalculatorException ce) {
            throw ce;
        }catch (NumberFormatException e) {
            logger.error("Error: " +e.getMessage());
            throw new CalculatorException(ErrorCode.ERROR_PARAM);
        } catch (JsonParseException e) {
            logger.error("Error: " + e.getMessage());
            throw new CalculatorException(ErrorCode.BAD_BODY);
        }catch (Exception e){
            logger.error("Error: " + e.getMessage());
            throw new CalculatorException(ErrorCode.UNKNOW_ERROR);
        }
    }


    private String toRecordsUri(UriInfo uriInfo) {
        return  uriInfo.getPath().split(CalculatorWebServices.API_ROOT)[0] + CalculatorWebServices.API_ROOT + "/records/";
    }



}
