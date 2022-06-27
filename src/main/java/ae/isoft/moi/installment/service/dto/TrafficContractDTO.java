package ae.isoft.moi.installment.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link ae.isoft.moi.installment.domain.TrafficContract} entity.
 */
public class TrafficContractDTO implements Serializable {

    private Long id;

    private Integer requestId;

    private String requestNo;

    private String requestServiceCode;

    private String requestDescription;

    private String plateNo;

    private String motorNo;

    private String chassisNo;

    private String trafficUnitCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }

    public String getRequestServiceCode() {
        return requestServiceCode;
    }

    public void setRequestServiceCode(String requestServiceCode) {
        this.requestServiceCode = requestServiceCode;
    }

    public String getRequestDescription() {
        return requestDescription;
    }

    public void setRequestDescription(String requestDescription) {
        this.requestDescription = requestDescription;
    }

    public String getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }

    public String getMotorNo() {
        return motorNo;
    }

    public void setMotorNo(String motorNo) {
        this.motorNo = motorNo;
    }

    public String getChassisNo() {
        return chassisNo;
    }

    public void setChassisNo(String chassisNo) {
        this.chassisNo = chassisNo;
    }

    public String getTrafficUnitCode() {
        return trafficUnitCode;
    }

    public void setTrafficUnitCode(String trafficUnitCode) {
        this.trafficUnitCode = trafficUnitCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TrafficContractDTO)) {
            return false;
        }

        TrafficContractDTO trafficContractDTO = (TrafficContractDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, trafficContractDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TrafficContractDTO{" +
            "id=" + getId() +
            ", requestId=" + getRequestId() +
            ", requestNo='" + getRequestNo() + "'" +
            ", requestServiceCode='" + getRequestServiceCode() + "'" +
            ", requestDescription='" + getRequestDescription() + "'" +
            ", plateNo='" + getPlateNo() + "'" +
            ", motorNo='" + getMotorNo() + "'" +
            ", chassisNo='" + getChassisNo() + "'" +
            ", trafficUnitCode='" + getTrafficUnitCode() + "'" +
            "}";
    }
}
