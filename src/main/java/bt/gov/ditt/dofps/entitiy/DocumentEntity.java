package bt.gov.ditt.dofps.entitiy;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.File;

/**
 * Created by Pema Drakpa on 1/23/2020.
 */
@Entity
@Table(name = "t_document")
public class DocumentEntity{
    @Id
    @Column(name = "Document_Id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int document_Id;
    @Column(name = "Application_Number")
    private String application_Number;
    @Column(name = "Document_Type")
    private String document_Type;
    @Column(name = "Document_Name")
    private String Document_Name;
    @Column(name = "Upload_URL")
    private String upload_URL;
    @Column(name = "UUID")
    private String uUID;
    @Column(name = "Type")
    private String type;

    @Transient
    private File attachment;

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
        return Document_Name;
    }

    public void setDocument_Name(String document_Name) {
        Document_Name = document_Name;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public File getAttachment() {
        return attachment;
    }

    public void setAttachment(File attachment) {
        this.attachment = attachment;
    }
}
