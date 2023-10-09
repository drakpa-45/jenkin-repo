package bt.gov.ditt.dofps.dto;

//import lombok.Data;

import groovy.transform.ToString;

/**
 * Description: NdiDTO
 * Date:  2022-Jun-29
 *
 * @author: Bikash Rai
 * @version: 1.0.0
 * ======================
 * Change History:
 * Version:1.0.0
 * Author:
 * Date: 2022-Jun-29
 * Change Description:
 * Search Tag:
 */

//@Data

@ToString

public class NdiDTO {
    private String statusCode;
    private InviteDTO data;
    private String message;
    private String relationDID;
    private String proofRequestName;
    private String proofRequestURL;
    private String proofRequestThreadId;
    private String deepLinkURL;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public InviteDTO getData() {
        return data;
    }

    public void setData(InviteDTO data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRelationDID() {
        return relationDID;
    }

    public void setRelationDID(String relationDID) {
        this.relationDID = relationDID;
    }

    public String getProofRequestName() {
        return proofRequestName;
    }

    public void setProofRequestName(String proofRequestName) {
        this.proofRequestName = proofRequestName;
    }

    public String getProofRequestURL() {
        return proofRequestURL;
    }

    public void setProofRequestURL(String proofRequestURL) {
        this.proofRequestURL = proofRequestURL;
    }

    public String getProofRequestThreadId() {
        return proofRequestThreadId;
    }

    public void setProofRequestThreadId(String proofRequestThreadId) {
        this.proofRequestThreadId = proofRequestThreadId;
    }

    public String getDeepLinkURL() {
        return deepLinkURL;
    }

    public void setDeepLinkURL(String deepLinkURL) {
        this.deepLinkURL = deepLinkURL;
    }
}
