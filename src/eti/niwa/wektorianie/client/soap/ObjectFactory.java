
package eti.niwa.wektorianie.client.soap;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the eti.niwa.wektorianie.client.soap package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ProcessPicture_QNAME = new QName("http://wektorianie.niwa.eti/", "processPicture");
    private final static QName _ProcessPictureByFilename_QNAME = new QName("http://wektorianie.niwa.eti/", "processPictureByFilename");
    private final static QName _ProcessPictureResponse_QNAME = new QName("http://wektorianie.niwa.eti/", "processPictureResponse");
    private final static QName _ProcessPictureByFilenameResponse_QNAME = new QName("http://wektorianie.niwa.eti/", "processPictureByFilenameResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: eti.niwa.wektorianie.client.soap
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ProcessPictureResponse }
     * 
     */
    public ProcessPictureResponse createProcessPictureResponse() {
        return new ProcessPictureResponse();
    }

    /**
     * Create an instance of {@link ProcessPictureByFilenameResponse }
     * 
     */
    public ProcessPictureByFilenameResponse createProcessPictureByFilenameResponse() {
        return new ProcessPictureByFilenameResponse();
    }

    /**
     * Create an instance of {@link ProcessPictureByFilename }
     * 
     */
    public ProcessPictureByFilename createProcessPictureByFilename() {
        return new ProcessPictureByFilename();
    }

    /**
     * Create an instance of {@link ProcessPicture }
     * 
     */
    public ProcessPicture createProcessPicture() {
        return new ProcessPicture();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProcessPicture }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wektorianie.niwa.eti/", name = "processPicture")
    public JAXBElement<ProcessPicture> createProcessPicture(ProcessPicture value) {
        return new JAXBElement<ProcessPicture>(_ProcessPicture_QNAME, ProcessPicture.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProcessPictureByFilename }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wektorianie.niwa.eti/", name = "processPictureByFilename")
    public JAXBElement<ProcessPictureByFilename> createProcessPictureByFilename(ProcessPictureByFilename value) {
        return new JAXBElement<ProcessPictureByFilename>(_ProcessPictureByFilename_QNAME, ProcessPictureByFilename.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProcessPictureResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wektorianie.niwa.eti/", name = "processPictureResponse")
    public JAXBElement<ProcessPictureResponse> createProcessPictureResponse(ProcessPictureResponse value) {
        return new JAXBElement<ProcessPictureResponse>(_ProcessPictureResponse_QNAME, ProcessPictureResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProcessPictureByFilenameResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wektorianie.niwa.eti/", name = "processPictureByFilenameResponse")
    public JAXBElement<ProcessPictureByFilenameResponse> createProcessPictureByFilenameResponse(ProcessPictureByFilenameResponse value) {
        return new JAXBElement<ProcessPictureByFilenameResponse>(_ProcessPictureByFilenameResponse_QNAME, ProcessPictureByFilenameResponse.class, null, value);
    }

}
