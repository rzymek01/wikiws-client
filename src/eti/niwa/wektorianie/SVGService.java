package eti.niwa.wektorianie;
import eti.niwa.wektorianie.util.ImageIO;
import org.opencv.core.Core;
import org.opencv.core.Mat;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService()
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use= SOAPBinding.Use.LITERAL)
/**
 * Any changes in this file will require some changes in eti.niwa.wektorianie.jaxws package
 *
 * Please, follow this webpage to regenerate them
 * http://www.mkyong.com/webservices/jax-ws/wrapper-class-package-jaxws-methodname-is-not-found-have-you-run-apt-to-generate-them/
 * http://stackoverflow.com/questions/17118919/wsgen-class-not-found
 *
 * tldr:
 * call `wsgen -keep -cp . eti.niwa.wektorianie.SVGService` from directory `targer/classes`
 * copy `target/classes/eti/niwa/wektorianie/jaxws/*.java` into `src/eti/niwa/wektorianie/jaxws/`
 */
public class SVGService {

    @WebMethod
    public String processPicture(String from) {
        return String.format("Hello, world, from %s", from);
    }

    @WebMethod
    public void hackProcessPicture(String filename) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat mat = ImageIO.loadImage(filename);
        ImageIO.saveImage(filename, mat);
    }

}
