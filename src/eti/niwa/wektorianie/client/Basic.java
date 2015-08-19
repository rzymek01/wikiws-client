package eti.niwa.wektorianie.client;

/**
 * Created by maciek on 05.08.15.
 */

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.BlockingQueue;

import org.apache.commons.io.IOUtils;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import java.io.*;

@Singleton
@Path("/")
public class Basic {

  private Integer counter;

  private BlockingQueue<UploadedImage> imageQueue;
  private ConcurrentHashMap<Integer, ProcessedImage> processedImages;
  private Worker worker;

  public Basic() {
    counter = 0;

    imageQueue = new ArrayBlockingQueue<>(1024, true);
    processedImages = new ConcurrentHashMap<>();
    worker = new Worker(imageQueue, processedImages);
    new Thread(worker).start();
  }

  @GET
  @Path("/hello")
  @Produces(MediaType.TEXT_PLAIN)
  public String sayPlainTextHello() {
    return "Hello!";
  }

  @GET
  @Path("/svg/{id}/is-ready")
  @Produces(MediaType.TEXT_PLAIN)
  public Integer isSVGReady(@PathParam("id") Integer id) {
    ProcessedImage result = processedImages.get(id);
    if (null != result) {
      return result.getProcessingTime();
    } else {
      return -1;
    }
  }

  @GET
  @Path("/svg/{id}.svg")
  @Produces("image/svg+xml")
  public String getSVG(@PathParam("id") Integer id) {
    ProcessedImage result = processedImages.get(id);
    if (null != result) {
      //@todo: delete svg from map
      return result.getSvg();
    } else {
      return "";
    }
  }

  @POST
  @Path("/process-picture")
  @Produces(MediaType.TEXT_PLAIN)
  @Consumes(MediaType.MULTIPART_FORM_DATA)
  public Response processPicture(
      @FormDataParam("file") InputStream uploadedInputStream,
      @FormDataParam("file") FormDataContentDisposition fileDetail
  ) {
    if (null == uploadedInputStream || null == fileDetail) {
      return Response.status(400).build();
    }

//    String uploadedFileLocation = "/tmp/" + fileDetail.getFileName();
//    writeToFile(uploadedInputStream, uploadedFileLocation);
//    String output = "File uploaded to : " + uploadedFileLocation;

    try {
      Integer id = generateImageId();
      UploadedImage image = new UploadedImage(id, IOUtils.toByteArray(uploadedInputStream));
      imageQueue.add(image);

      return Response.status(200).entity(id.toString()).build();
    } catch (IOException ex) {
      return Response.status(500).build();
    }
  }

  private synchronized Integer generateImageId() {
    return ++counter;
  }

  private void writeToFile(InputStream uploadedInputStream, String uploadedFileLocation) {
    try {
      int read = 0;
      byte[] bytes = new byte[1024];

      OutputStream out = new FileOutputStream(new File(uploadedFileLocation));
      while ((read = uploadedInputStream.read(bytes)) != -1) {
        out.write(bytes, 0, read);
      }
      out.flush();
      out.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  //application/octet-stream
}
