package eti.niwa.wektorianie;
import eti.niwa.wektorianie.imageprocessing.MainProcessing;
import eti.niwa.wektorianie.util.ImageIO;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import sun.applet.Main;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebService()
//@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use= SOAPBinding.Use.LITERAL)
/**
 * Any changes in this file will require some changes in eti.niwa.wektorianie.jaxws package
 *
 * Please, follow this webpage to regenerate them
 * http://www.mkyong.com/webservices/jax-ws/wrapper-class-package-jaxws-methodname-is-not-found-have-you-run-apt-to-generate-them/
 * http://stackoverflow.com/questions/17118919/wsgen-class-not-found
 *
 * tldr:
 * call `wsgen -keep -cp . eti.niwa.wektorianie.SVGService` from directory `target/classes`
 * copy `target/classes/eti/niwa/wektorianie/jaxws/*.java` into `src/eti/niwa/wektorianie/jaxws/`
 */
public class SVGService {

    @WebMethod
    public String processPicture(String from) {
        return String.format("Hello, world, from %s", from);
    }

    @WebMethod(operationName = "hackProcessPicture")
    public String hackProcessPicture(@WebParam(name = "filename") String filename) {
        nu.pattern.OpenCV.loadShared();
        if (StringUtils.isEmpty(filename)) {
            return "Param 'filename' is empty.";
        }

        final File file = new File(filename);
        if (!file.exists()) {
            return String.format("File '%s' does not exist.", filename);
        }

        final MainProcessing mainProcessing = MainProcessing.fromFilename(filename);
        mainProcessing.process();
        return "Ok";
    }
}
