package ae.isoft.moi.installment.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link ae.isoft.moi.installment.domain.Request} entity.
 */
public class RequestDTO implements Serializable {

    private Long id;

    private String requestNo;

    private String requestServiceCode;

    private String requestDescription;

    private Integer vehicleTypeId;

    private Integer licenseTypeId;

    private String plateNo;

    private String motorNo;

    private String chassisNo;

    private String trafficUnitCode;

    private CustomerDTO customer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(Integer vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    public Integer getLicenseTypeId() {
        return licenseTypeId;
    }

    public void setLicenseTypeId(Integer licenseTypeId) {
        this.licenseTypeId = licenseTypeId;
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

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RequestDTO)) {
            return false;
        }

        RequestDTO requestDTO = (RequestDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, requestDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RequestDTO{" +
            "id=" + getId() +
            ", requestNo='" + getRequestNo() + "'" +
            ", requestServiceCode='" + getRequestServiceCode() + "'" +
            ", requestDescription='" + getRequestDescription() + "'" +
            ", vehicleTypeId=" + getVehicleTypeId() +
            ", licenseTypeId=" + getLicenseTypeId() +
            ", plateNo='" + getPlateNo() + "'" +
            ", motorNo='" + getMotorNo() + "'" +
            ", chassisNo='" + getChassisNo() + "'" +
            ", trafficUnitCode='" + getTrafficUnitCode() + "'" +
            ", customer=" + getCustomer() +
            "}";
    }
}
