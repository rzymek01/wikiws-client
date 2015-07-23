
package eti.niwa.wektorianie.jaxws;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "hackProcessPictureResponse", namespace = "http://wektorianie.niwa.eti/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "hackProcessPictureResponse", namespace = "http://wektorianie.niwa.eti/")
public class HackProcessPictureResponse {


    @XmlElement(name = "return", namespace = "")
    private String _return;

    /**
     *
     * @return
     *     returns String
     */
    public String getReturn() {
        return this._return;
    }

    /**
     *
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(String _return) {
        this._return = _return;
    }

}
