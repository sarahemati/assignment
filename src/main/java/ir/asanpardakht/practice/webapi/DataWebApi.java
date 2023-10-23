package ir.asanpardakht.practice.webapi;

import java.util.List;

import ir.asanpardakht.practice.business.service.DataService;
import ir.asanpardakht.practice.model.DataEntity;
import jakarta.annotation.PostConstruct;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/data")
public class DataWebApi {

	private DataService dataService;

	@PostConstruct
	public void init() {
		dataService = new DataService();
	}

	@GET
	@Path("/{count}/generate")
	@Produces(MediaType.TEXT_PLAIN)
	public Response generateDataToFile(@PathParam("count") @DefaultValue("100") int count) throws Exception {
		try {
			int result = dataService.generateDataToFile(count);
			return Response.status(Status.CREATED).entity(result).build();
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Generate Failed.").build();
		}
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response select(@PathParam("id") int id) throws Exception {
		try {
			DataEntity result = dataService.select(id);
			return Response.status(Status.OK).entity(result).build();
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Select Failed.").build();
		}
	}

	@GET
	@Path("/cache")
	@Produces(MediaType.APPLICATION_JSON)
	public Response showCache() throws Exception {
		try {
			List<String> result = dataService.showCache();
			return Response.status(Status.OK).entity(result).build();
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Show cache Failed.").build();
		}
	}

	@POST
	@Path("/")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response save(DataEntity dataEntity) throws Exception {
		try {
			boolean result = dataService.save(dataEntity);
			return Response.status(Status.OK).entity(result).build();
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Save Failed.").build();
		}
	}

	@POST
	@Path("/list")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response save(List<DataEntity> list) throws Exception {
		try {
			boolean result = dataService.save(list);
			return Response.status(Status.OK).entity(result).build();
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Save List Failed.").build();
		}
	}

	@PUT
	@Path("/")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response replace(DataEntity dataEntity) throws Exception {
		try {
			boolean result = dataService.replace(dataEntity);
			return Response.status(Status.OK).entity(result).build();
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Replace Failed.").build();
		}
	}

	@PUT
	@Path("/list")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response replace(List<DataEntity> list) throws Exception {
		try {
			boolean result = dataService.replace(list);
			return Response.status(Status.OK).entity(result).build();
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Replace List Failed.").build();
		}
	}
}
