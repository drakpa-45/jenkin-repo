package bt.gov.ditt.dofps.dto;

import java.math.BigInteger;
import java.util.Date;

/**
 * Created by Pema Drakpa on 1/17/2020.
 */
public class CommonDto {

    private int header_id;
    private String header_Name;
    private int document_Id;
    private String application_Number;
    private String document_Type;
    private String document_Name;
    private String upload_URL;
    private String uUID;
    private int range_Id;
    private String range_Name;
    private String grouptaskRTPDealing="0";
    private String grouptaskWPDealing="0";
    private String grouptaskPRLDealing="0";
    private String product_desc;
    private String personaltaskRTPDealing="0";
    private String personaltaskWPDealing ="0";
    private String personaltaskPRLDealing="0";
    private String groupTaskOtherConsBeating = "0";
    private String personalTaskOtherConsBeating = "0";
    private BigInteger yearCount;
    private int maxLimit;
    private int storey;

    public CommonDto() {
    }

    public CommonDto(int header_id, String header_Name, int document_Id, String application_Number, String document_Type, String document_Name, String upload_URL, String uUID, int range_Id, String range_Name, String grouptaskRTPDealing, String grouptaskWPDealing, String grouptaskPRLDealing, String product_desc, String personaltaskRTPDealing, String personaltaskWPDealing, String personaltaskPRLDealing, String groupTaskOtherConsBeating, String personalTaskOtherConsBeating, BigInteger yearCount, int maxLimit, int storey) {
        this.header_id = header_id;
        this.header_Name = header_Name;
        this.document_Id = document_Id;
        this.application_Number = application_Number;
        this.document_Type = document_Type;
        this.document_Name = document_Name;
        this.upload_URL = upload_URL;
        this.uUID = uUID;
        this.range_Id = range_Id;
        this.range_Name = range_Name;
        this.grouptaskRTPDealing = grouptaskRTPDealing;
        this.grouptaskWPDealing = grouptaskWPDealing;
        this.grouptaskPRLDealing = grouptaskPRLDealing;
        this.product_desc = product_desc;
        this.personaltaskRTPDealing = personaltaskRTPDealing;
        this.personaltaskWPDealing = personaltaskWPDealing;
        this.personaltaskPRLDealing = personaltaskPRLDealing;
        this.groupTaskOtherConsBeating = groupTaskOtherConsBeating;
        this.personalTaskOtherConsBeating = personalTaskOtherConsBeating;
        this.yearCount = yearCount;
        this.maxLimit = maxLimit;
        this.storey = storey;
    }

    public int getHeader_id() {
        return header_id;
    }

    public void setHeader_id(int header_id) {
        this.header_id = header_id;
    }

    public String getHeader_Name() {
        return header_Name;
    }

    public void setHeader_Name(String header_Name) {
        this.header_Name = header_Name;
    }

    public int getDocument_Id() {
        return document_Id;
    }

    public void setDocument_Id(int document_Id) {
        this.document_Id = document_Id;
    }

    public String getApplication_Number() {
        return application_Number;
    }

    public void setApplication_Number(String application_Number) {
        this.application_Number = application_Number;
    }

    public String getDocument_Type() {
        return document_Type;
    }

    public void setDocument_Type(String document_Type) {
        this.document_Type = document_Type;
    }

    public String getDocument_Name() {
        return document_Name;
    }

    public void setDocument_Name(String document_Name) {
        this.document_Name = document_Name;
    }

    public String getUpload_URL() {
        return upload_URL;
    }

    public void setUpload_URL(String upload_URL) {
        this.upload_URL = upload_URL;
    }

    public String getuUID() {
        return uUID;
    }

    public void setuUID(String uUID) {
        this.uUID = uUID;
    }

    public int getRange_Id() {
        return range_Id;
    }

    public void setRange_Id(int range_Id) {
        this.range_Id = range_Id;
    }

    public String getRange_Name() {
        return range_Name;
    }

    public void setRange_Name(String range_Name) {
        this.range_Name = range_Name;
    }

    public String getGrouptaskRTPDealing() {
        return grouptaskRTPDealing;
    }

    public void setGrouptaskRTPDealing(String grouptaskRTPDealing) {
        this.grouptaskRTPDealing = grouptaskRTPDealing;
    }

    public String getGrouptaskWPDealing() {
        return grouptaskWPDealing;
    }

    public void setGrouptaskWPDealing(String grouptaskWPDealing) {
        this.grouptaskWPDealing = grouptaskWPDealing;
    }

    public String getGrouptaskPRLDealing() {
        return grouptaskPRLDealing;
    }

    public void setGrouptaskPRLDealing(String grouptaskPRLDealing) {
        this.grouptaskPRLDealing = grouptaskPRLDealing;
    }

    public String getProduct_desc() {
        return product_desc;
    }

    public void setProduct_desc(String product_desc) {
        this.product_desc = product_desc;
    }

    public String getPersonaltaskRTPDealing() {
        return personaltaskRTPDealing;
    }

    public void setPersonaltaskRTPDealing(String personaltaskRTPDealing) {
        this.personaltaskRTPDealing = personaltaskRTPDealing;
    }

    public String getPersonaltaskWPDealing() {
        return personaltaskWPDealing;
    }

    public void setPersonaltaskWPDealing(String personaltaskWPDealing) {
        this.personaltaskWPDealing = personaltaskWPDealing;
    }

    public String getPersonaltaskPRLDealing() {
        return personaltaskPRLDealing;
    }

    public void setPersonaltaskPRLDealing(String personaltaskPRLDealing) {
        this.personaltaskPRLDealing = personaltaskPRLDealing;
    }

    public String getGroupTaskOtherConsBeating() {
        return groupTaskOtherConsBeating;
    }

    public void setGroupTaskOtherConsBeating(String groupTaskOtherConsBeating) {
        this.groupTaskOtherConsBeating = groupTaskOtherConsBeating;
    }

    public String getPersonalTaskOtherConsBeating() {
        return personalTaskOtherConsBeating;
    }

    public void setPersonalTaskOtherConsBeating(String personalTaskOtherConsBeating) {
        this.personalTaskOtherConsBeating = personalTaskOtherConsBeating;
    }

    public BigInteger getYearCount() {
        return yearCount;
    }

    public void setYearCount(BigInteger yearCount) {
        this.yearCount = yearCount;
    }

    public int getMaxLimit() {
        return maxLimit;
    }

    public void setMaxLimit(int maxLimit) {
        this.maxLimit = maxLimit;
    }

    public int getStorey() {
        return storey;
    }

    public void setStorey(int storey) {
        this.storey = storey;
    }
}
