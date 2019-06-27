package filterData;

import java.io.Serializable;

/**
 * @Description:
 * @Date: 2019/4/29 12:10
 */

public class Data implements Serializable {
    private static final long serialVersionUID = -8406983997819037701L;
    private Integer formId;
    private String formName;
    private String font;
    private Integer fontSize;
    private String fontColor;
    private String fontStyle;
    private String textAlign;
    private Integer formStyle;
    private Integer validateStyle;
    private Integer posX;
    private Integer posY;
    private Integer width;
    private Integer height;
    private Integer pageNo;


    public Integer getFormId() {
        return formId;
    }

    public void setFormId(Integer formId) {
        this.formId = formId;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font;
    }

    public Integer getFontSize() {
        return fontSize;
    }

    public void setFontSize(Integer fontSize) {
        this.fontSize = fontSize;
    }

    public String getFontColor() {
        return fontColor;
    }

    public void setFontColor(String fontColor) {
        this.fontColor = fontColor;
    }

    public String getFontStyle() {
        return fontStyle;
    }

    public void setFontStyle(String fontStyle) {
        this.fontStyle = fontStyle;
    }

    public String getTextAlign() {
        return textAlign;
    }

    public void setTextAlign(String textAlign) {
        this.textAlign = textAlign;
    }

    public Integer getFormStyle() {
        return formStyle;
    }

    public void setFormStyle(Integer formStyle) {
        this.formStyle = formStyle;
    }

    public Integer getValidateStyle() {
        return validateStyle;
    }

    public void setValidateStyle(Integer validateStyle) {
        this.validateStyle = validateStyle;
    }

    public Integer getPosX() {
        return posX;
    }

    public void setPosX(Integer posX) {
        this.posX = posX;
    }

    public Integer getPosY() {
        return posY;
    }

    public void setPosY(Integer posY) {
        this.posY = posY;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }
}
