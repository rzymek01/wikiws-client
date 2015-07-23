
package eti.niwa.wektorianie.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "hackProcessPicture", namespace = "http://wektorianie.niwa.eti/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "hackProcessPicture", namespace = "http://wektorianie.niwa.eti/")
public class HackProcessPicture {

    @XmlElement(name = "filename", namespace = "")
    private String filename;

    /**
     * 
     * @return
     *     returns String
     */
    public String getFilename() {
        return this.filename;
    }

    /**
     * 
     * @param filename
     *     the value for the filename property
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

}
