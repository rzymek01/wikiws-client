package eti.niwa.wektorianie.client;

import java.util.logging.Level;
import java.util.logging.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletProperties;

@ApplicationPath("/rest")
public class RESTService extends ResourceConfig {

    public RESTService() {
        super(Basic.class, MultiPartFeature.class);
        property(ServletProperties.FILTER_FORWARD_ON_404, true);
    }


}
