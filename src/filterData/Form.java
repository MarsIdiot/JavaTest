package filterData;

import java.io.Serializable;

/**
 * @Description:
 * @Date: 2019/4/29 12:10
 */

public class Form implements Serializable {
    private static final long serialVersionUID = -8406983997819032701L;

    private String formId;
        private String formName;

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }
}
