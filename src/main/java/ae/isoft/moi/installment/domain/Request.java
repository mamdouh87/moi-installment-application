package ae.isoft.moi.installment.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * A Request.
 */
@Entity
@Table(name = "request")
public class Request implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "request_no")
    private String requestNo;

    @Column(name = "request_service_code")
    private String requestServiceCode;

    @Column(name = "request_description")
    private String requestDescription;

    @Column(name = "vehicle_type_id")
    private Integer vehicleTypeId;

    @Column(name = "license_type_id")
    private Integer licenseTypeId;

    @Column(name = "plate_no")
    private String plateNo;

    @Column(name = "motor_no")
    private String motorNo;

    @Column(name = "chassis_no")
    private String chassisNo;

    @Column(name = "traffic_unit_code")
    private String trafficUnitCode;

    @OneToMany(mappedBy = "request")
    @JsonIgnoreProperties(value = { "requestSystemFeeDetails", "request" }, allowSetters = true)
    private Set<RequestSystemFee> requestsystemFees = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = { "country", "requests" }, allowSetters = true)
    private Customer customer;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Request id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRequestNo() {
        return this.requestNo;
    }

    public Request requestNo(String requestNo) {
        this.setRequestNo(requestNo);
        return this;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }

    public String getRequestServiceCode() {
        return this.requestServiceCode;
    }

    public Request requestServiceCode(String requestServiceCode) {
        this.setRequestServiceCode(requestServiceCode);
        return this;
    }

    public void setRequestServiceCode(String requestServiceCode) {
        this.requestServiceCode = requestServiceCode;
    }

    public String getRequestDescription() {
        return this.requestDescription;
    }

    public Request requestDescription(String requestDescription) {
        this.setRequestDescription(requestDescription);
        return this;
    }

    public void setRequestDescription(String requestDescription) {
        this.requestDescription = requestDescription;
    }

    public Integer getVehicleTypeId() {
        return this.vehicleTypeId;
    }

    public Request vehicleTypeId(Integer vehicleTypeId) {
        this.setVehicleTypeId(vehicleTypeId);
        return this;
    }

    public void setVehicleTypeId(Integer vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    public Integer getLicenseTypeId() {
        return this.licenseTypeId;
    }

    public Request licenseTypeId(Integer licenseTypeId) {
        this.setLicenseTypeId(licenseTypeId);
        return this;
    }

    public void setLicenseTypeId(Integer licenseTypeId) {
        this.licenseTypeId = licenseTypeId;
    }

    public String getPlateNo() {
        return this.plateNo;
    }

    public Request plateNo(String plateNo) {
        this.setPlateNo(plateNo);
        return this;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }

    public String getMotorNo() {
        return this.motorNo;
    }

    public Request motorNo(String motorNo) {
        this.setMotorNo(motorNo);
        return this;
    }

    public void setMotorNo(String motorNo) {
        this.motorNo = motorNo;
    }

    public String getChassisNo() {
        return this.chassisNo;
    }

    public Request chassisNo(String chassisNo) {
        this.setChassisNo(chassisNo);
        return this;
    }

    public void setChassisNo(String chassisNo) {
        this.chassisNo = chassisNo;
    }

    public String getTrafficUnitCode() {
        return this.trafficUnitCode;
    }

    public Request trafficUnitCode(String trafficUnitCode) {
        this.setTrafficUnitCode(trafficUnitCode);
        return this;
    }

    public void setTrafficUnitCode(String trafficUnitCode) {
        this.trafficUnitCode = trafficUnitCode;
    }

    public Set<RequestSystemFee> getRequestsystemFees() {
        return this.requestsystemFees;
    }

    public void setRequestsystemFees(Set<RequestSystemFee> requestSystemFees) {
        if (this.requestsystemFees != null) {
            this.requestsystemFees.forEach(i -> i.setRequest(null));
        }
        if (requestSystemFees != null) {
            requestSystemFees.forEach(i -> i.setRequest(this));
        }
        this.requestsystemFees = requestSystemFees;
    }

    public Request requestsystemFees(Set<RequestSystemFee> requestSystemFees) {
        this.setRequestsystemFees(requestSystemFees);
        return this;
    }

    public Request addRequestsystemFees(RequestSystemFee requestSystemFee) {
        this.requestsystemFees.add(requestSystemFee);
        requestSystemFee.setRequest(this);
        return this;
    }

    public Request removeRequestsystemFees(RequestSystemFee requestSystemFee) {
        this.requestsystemFees.remove(requestSystemFee);
        requestSystemFee.setRequest(null);
        return this;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Request customer(Customer customer) {
        this.setCustomer(customer);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Request)) {
            return false;
        }
        return id != null && id.equals(((Request) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Request{" +
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
            "}";
    }
}
