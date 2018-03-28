package info;

/**
 * Created by Administrator on 3/15/2018.
 */

public class DictionnaryCauseInfo {
    private int id, ordering;
    private String causeCode, causeName, isEnable,createdBy, isParent,depCode, isStatus, systemCode, idHas;

    public DictionnaryCauseInfo
            (String causeCode, String causeName, String isEnable,int ordering, String createdBy, String isParent,String idHas, String depCode, String isStatus, String systemCode, int id) {
        this.causeCode = causeCode;
        this.causeName = causeName;
        this.isEnable = isEnable;
        this.ordering = ordering;
        this.createdBy = createdBy;
        this.isParent = isParent;
        this.idHas = idHas;
        this.depCode = depCode;
        this.isStatus = isStatus;
        this.systemCode = systemCode;
        this.id = id;
    }
    public DictionnaryCauseInfo(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrdering() {
        return ordering;
    }

    public void setOrdering(int ordering) {
        this.ordering = ordering;
    }

    public String getIdHas() {
        return idHas;
    }

    public void setIdHas(String idHas) {
        this.idHas = idHas;
    }

    public String getCauseCode() {
        return causeCode;
    }

    public void setCauseCode(String causeCode) {
        this.causeCode = causeCode;
    }

    public String getCauseName() {
        return causeName;
    }

    public void setCauseName(String causeName) {
        this.causeName = causeName;
    }

    public String getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(String isEnable) {
        this.isEnable = isEnable;
    }

    public String getCreateBy() {
        return createdBy;
    }

    public void setCreateBy(String createBy) {
        this.createdBy = createBy;
    }

    public String getIsParent() {
        return isParent;
    }

    public void setIsParent(String isParent) {
        this.isParent = isParent;
    }

    public String getDepCode() {
        return depCode;
    }

    public void setDepCode(String depCode) {
        this.depCode = depCode;
    }

    public String getIsStatus() {
        return isStatus;
    }

    public void setIsStatus(String isStatus) {
        this.isStatus = isStatus;
    }

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }
}
