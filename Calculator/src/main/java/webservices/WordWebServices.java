package webservices;

import business.WordProcess;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import enums.ErrorCode;
import enums.MethodType;
import exception.CalculatorException;
import org.apache.log4j.Logger;
import pojos.records.WordRecordsPojo;
import pojos.request.CaesarRequest;
import pojos.request.WordRequest;
import pojos.response.WordResponse;
import utils.MethodParser;

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


@Path(WordWebServices.API_ROOT)
@Stateless(name = WordWebServices.SERVICE_NAME)

public class WordWebServices {


    public static final String SERVICE_NAME = "WordWebServices";
    public static final String API_ROOT = "/word";

    @Inject
    Logger logger;

    @Inject
    WordProcess wordProcess;

    @POST
    @Path("/lower")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response toLower(@Context UriInfo uriInfo,
                            String body) throws Exception {
        try {
            WordRequest request = new Gson().fromJson(body, WordRequest.class);
            WordRecordsPojo pojo = wordProcess.process(request.getPhrase(), MethodType.LOWER, false, 0L);
            WordResponse response = new WordResponse(pojo.getId(), pojo.getResult());

            String json = new Gson().toJson(response);

            return Response.created(URI.create(toRecordsUri(uriInfo) + pojo.getId())).entity(json).build();
        } catch (CalculatorException ce) {
            throw ce;
        } catch (JsonParseException e) {
            logger.error("Error: " + e.getMessage());
            throw new CalculatorException(ErrorCode.BAD_BODY);
        } catch (Exception e) {
            logger.error("Error: " + e.getMessage());
            throw new CalculatorException(ErrorCode.UNKNOW_ERROR);
        }

    }

    @POST
    @Path("/upper")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response toUpper(@Context UriInfo uriInfo,
                            String body) throws CalculatorException {
        try {
            WordRequest request = new Gson().fromJson(body, WordRequest.class);
            WordRecordsPojo pojo = wordProcess.process(request.getPhrase(), MethodType.UPPER, false, 0L);
            WordResponse response = new WordResponse(pojo.getId(), pojo.getResult());

            String json = new Gson().toJson(response);

            return Response.created(URI.create(toRecordsUri(uriInfo) + pojo.getId())).entity(json).build();
        } catch (CalculatorException ce) {
            throw ce;
        } catch (JsonParseException e) {
            logger.error("Error: " + e.getMessage());
            throw new CalculatorException(ErrorCode.BAD_BODY);
        } catch (Exception e) {
            logger.error("Error: " + e.getMessage());
            throw new CalculatorException(ErrorCode.UNKNOW_ERROR);
        }

    }

    @POST
    @Path("/erase/{word}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response erase(@Context UriInfo uriInfo, String body, @PathParam("word") String word) throws CalculatorException {
        try {
            WordRequest request = new Gson().fromJson(body, WordRequest.class);
            WordRecordsPojo pojo = wordProcess.eraseProcess(request.getPhrase(), word);
            WordResponse response = new WordResponse(pojo.getId(), pojo.getResult());

            String json = new Gson().toJson(response);

            return Response.created(URI.create(toRecordsUri(uriInfo) + pojo.getId())).entity(json).build();
        } catch (CalculatorException ce) {
            throw ce;
        } catch (JsonParseException e) {
            logger.error("Error: " + e.getMessage());
            throw new CalculatorException(ErrorCode.BAD_BODY);
        } catch (Exception e) {
            logger.error("Error: " + e.getMessage());
            throw new CalculatorException(ErrorCode.UNKNOW_ERROR);
        }
    }

    @POST
    @Path("/camel")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response toCamel(@Context UriInfo uriInfo,
                            String body) throws CalculatorException {
        try {
            WordRequest request = new Gson().fromJson(body, WordRequest.class);
            WordRecordsPojo pojo = wordProcess.process(request.getPhrase(), MethodType.CAMEL, false, 0L);
            WordResponse response = new WordResponse(pojo.getId(), pojo.getResult());

            String json = new Gson().toJson(response);

            return Response.created(URI.create(toRecordsUri(uriInfo) + pojo.getId())).entity(json).build();
        } catch (CalculatorException ce) {
            throw ce;
        } catch (JsonParseException e) {
            logger.error("Error: " + e.getMessage());
            throw new CalculatorException(ErrorCode.BAD_BODY);
        } catch (Exception e) {
            logger.error("Error: " + e.getMessage());
            throw new CalculatorException(ErrorCode.UNKNOW_ERROR);
        }
    }

    @POST
    @Path("/fromcamel")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response fromCamel(@Context UriInfo uriInfo,
                              String body) throws CalculatorException {
        try {
            WordRequest request = new Gson().fromJson(body, WordRequest.class);
            WordRecordsPojo pojo = wordProcess.process(request.getPhrase(), MethodType.FCAMEL, false, 0L);
            WordResponse response = new WordResponse(pojo.getId(), pojo.getResult());

            String json = new Gson().toJson(response);

            return Response.created(URI.create(toRecordsUri(uriInfo) + pojo.getId())).entity(json).build();
        } catch (CalculatorException ce) {
            throw ce;
        } catch (JsonParseException e) {
            logger.error("Error: " + e.getMessage());
            throw new CalculatorException(ErrorCode.BAD_BODY);
        } catch (Exception e) {
            logger.error("Error: " + e.getMessage());
            throw new CalculatorException(ErrorCode.UNKNOW_ERROR);
        }
    }

    @DELETE
    @Path("/remove/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    //@Token
    public Response remove(@PathParam("id") Long id) throws CalculatorException {
        try {

            if (!wordProcess.removeRecord(id)) throw new NoResultException();

            return Response.ok().build();

        } catch (CalculatorException ce) {
            throw ce;
        } catch (JsonParseException e) {
            throw new CalculatorException(ErrorCode.BAD_BODY);
        } catch (Exception e) {
            throw new CalculatorException(ErrorCode.UNKNOW_ERROR);
        }

    }

    @GET
    @Path("/records")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllRecords(@QueryParam("limit") @DefaultValue("10") Integer limit,
                                  @QueryParam("offset") @DefaultValue("0") Integer offset) throws Exception {
        try {
            List<WordRecordsPojo> records = wordProcess.getAllRecords(limit, offset);

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
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSingleRecord(@PathParam("id") Long id) throws Exception {
        try {

            WordRecordsPojo record = wordProcess.getSingleRecord(id);

            String json = new Gson().toJson(record);

            return Response.ok().entity(json).build();

        } catch (CalculatorException ce) {
            throw ce;
        } catch (Exception e) {
            logger.error("Error en records webservice single: " + e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    @POST
    @Path("/caesar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response caesarEncryption(@Context UriInfo uriInfo, String body) throws CalculatorException {
        try {
            CaesarRequest request = new Gson().fromJson(body, CaesarRequest.class);
            WordRecordsPojo pojo = wordProcess.caesarProcess(request.getPhrase(), request.getConvert());
            WordResponse response = new WordResponse(pojo.getId(), pojo.getResult());

            String json = new Gson().toJson(response);

            return Response.created(URI.create(toRecordsUri(uriInfo) + pojo.getId())).entity(json).build();
        } catch (Exception e) {
            throw new CalculatorException(ErrorCode.UNKNOW_ERROR);
        }
    }

    @PUT
    @Path("/update/{id}/{method}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@Context UriInfo uriInfo,
                           String body,
                           @PathParam("id") Long id,
                           @PathParam("method") String method) throws CalculatorException {
        try {
            WordRequest request = new Gson().fromJson(body, WordRequest.class);
            WordRecordsPojo pojo = wordProcess.process(request.getPhrase(), MethodParser.parse(method), true, id);
            WordResponse response = new WordResponse(pojo.getId(), pojo.getResult());

            String json = new Gson().toJson(response);

            return Response.created(URI.create(toRecordsUri(uriInfo) + pojo.getId())).entity(json).header("Access-Control-Allow-Methods", "PUT, OPTIONS").build();
        } catch (CalculatorException ce) {
            throw ce;
        } catch (Exception e) {
            throw new CalculatorException(ErrorCode.UNKNOW_ERROR);
        }
    }


    private String toRecordsUri(UriInfo uriInfo) {
        return uriInfo.getPath().split(WordWebServices.API_ROOT)[0] + WordWebServices.API_ROOT + "/records/";
    }

}
