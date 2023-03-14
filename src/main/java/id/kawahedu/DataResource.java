package id.kawahedu;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/films")
public class DataResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Data> getAllData() {
        return Data.listAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDataById(@PathParam("id") Long id) {
        Data data = Data.findById(id);
        if (data == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(data).build();
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createData(Data data) {
        data.persist();
        return Response.status(Response.Status.CREATED).entity(data).build();
    }

    @PUT
    @Transactional
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateData(@PathParam("id") Long id, Data newData) {
        Data data = Data.findById(id);
        if (data == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        data.setTitle(newData.getTitle());
        data.setActor(newData.getActor());
        data.setDirector(newData.getDirector());
        data.setGenre(newData.getGenre());
        data.setReleaseYear(newData.getReleaseYear());
        data.setRating(newData.getRating());
        data.persist();
        return Response.ok(data).build();
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public Response deleteData(@PathParam("id") Long id) {
        Data data = Data.findById(id);
        if (data == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        data.delete();
        return Response.noContent().build();
    }

}
