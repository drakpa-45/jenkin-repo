package bt.gov.ditt.dofps.dto;


public class InviteDTO {
    private Integer id;
    private String relationshipDID;
    private String deepLinkURL;

    private String proofRequestURL;
    private String proofRequestThreadId;
    private String proofRequestName;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRelationshipDID() {
        return relationshipDID;
    }

    public void setRelationshipDID(String relationshipDID) {
        this.relationshipDID = relationshipDID;
    }

    public String getDeepLinkURL() {
        return deepLinkURL;
    }

    public void setDeepLinkURL(String deepLinkURL) {
        this.deepLinkURL = deepLinkURL;
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

    public String getProofRequestName() {
        return proofRequestName;
    }

    public void setProofRequestName(String proofRequestName) {
        this.proofRequestName = proofRequestName;
    }
}
