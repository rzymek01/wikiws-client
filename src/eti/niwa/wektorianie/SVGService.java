package eti.niwa.wektorianie;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

@WebService()
public class SVGService {

    @WebMethod
    public String processPicture(String from) {
        return String.format("Hello, world, from %s", from);
    }

}
