package bt.gov.ditt.dofps.dto;

/**
 * Created by Tshedup Gyeltshen on 6/26/2020.
 */
public class PrivateLandDto {
    private Integer fP_Product_Id;
    private Integer no_trees;
    private Integer no_poles;
    private Integer no_bamboos;
    private float volume;

    public PrivateLandDto() {
    }

    public PrivateLandDto(Integer fP_Product_Id, Integer no_trees, Integer no_poles, Integer no_bamboos, float volume) {
        this.fP_Product_Id = fP_Product_Id;
        this.no_trees = no_trees;
        this.no_poles = no_poles;
        this.no_bamboos = no_bamboos;
        this.volume = volume;
    }

    public Integer getfP_Product_Id() {
        return fP_Product_Id;
    }

    public void setfP_Product_Id(Integer fP_Product_Id) {
        this.fP_Product_Id = fP_Product_Id;
    }

    public Integer getNo_trees() {
        return no_trees;
    }

    public void setNo_trees(Integer no_trees) {
        this.no_trees = no_trees;
    }

    public Integer getNo_poles() {
        return no_poles;
    }

    public void setNo_poles(Integer no_poles) {
        this.no_poles = no_poles;
    }

    public Integer getNo_bamboos() {
        return no_bamboos;
    }

    public void setNo_bamboos(Integer no_bamboos) {
        this.no_bamboos = no_bamboos;
    }

    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }
}

