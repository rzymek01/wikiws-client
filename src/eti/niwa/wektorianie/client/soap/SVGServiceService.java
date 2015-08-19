
package eti.niwa.wektorianie.client.soap;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "SVGServiceService", targetNamespace = "http://wektorianie.niwa.eti/", wsdlLocation = "http://localhost:8080/Wektorianie-1.0-SNAPSHOT/?wsdl")
public class SVGServiceService
    extends Service
{

    private final static URL SVGSERVICESERVICE_WSDL_LOCATION;
    private final static WebServiceException SVGSERVICESERVICE_EXCEPTION;
    private final static QName SVGSERVICESERVICE_QNAME = new QName("http://wektorianie.niwa.eti/", "SVGServiceService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/Wektorianie-1.0-SNAPSHOT/?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        SVGSERVICESERVICE_WSDL_LOCATION = url;
        SVGSERVICESERVICE_EXCEPTION = e;
    }

    public SVGServiceService() {
        super(__getWsdlLocation(), SVGSERVICESERVICE_QNAME);
    }

    public SVGServiceService(WebServiceFeature... features) {
        super(__getWsdlLocation(), SVGSERVICESERVICE_QNAME, features);
    }

    public SVGServiceService(URL wsdlLocation) {
        super(wsdlLocation, SVGSERVICESERVICE_QNAME);
    }

    public SVGServiceService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, SVGSERVICESERVICE_QNAME, features);
    }

    public SVGServiceService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SVGServiceService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns SVGService
     */
    @WebEndpoint(name = "SVGServicePort")
    public SVGService getSVGServicePort() {
        return super.getPort(new QName("http://wektorianie.niwa.eti/", "SVGServicePort"), SVGService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns SVGService
     */
    @WebEndpoint(name = "SVGServicePort")
    public SVGService getSVGServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://wektorianie.niwa.eti/", "SVGServicePort"), SVGService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (SVGSERVICESERVICE_EXCEPTION!= null) {
            throw SVGSERVICESERVICE_EXCEPTION;
        }
        return SVGSERVICESERVICE_WSDL_LOCATION;
    }

}