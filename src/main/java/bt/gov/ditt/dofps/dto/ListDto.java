package bt.gov.ditt.dofps.dto;

import java.util.List;

/**
 * Created by Pema Drakpa on 3/8/2020.
 */
public class ListDto {

    private List<WorkFlowDto> workFlowDtoList;

    public ListDto() {
    }

    public ListDto(List<WorkFlowDto> workFlowDtoList) {
        this.workFlowDtoList = workFlowDtoList;
    }

    public List<WorkFlowDto> getWorkFlowDtoList() {
        return workFlowDtoList;
    }

    public void setWorkFlowDtoList(List<WorkFlowDto> workFlowDtoList) {
        this.workFlowDtoList = workFlowDtoList;
    }
}
