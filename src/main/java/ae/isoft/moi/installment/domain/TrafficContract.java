package ae.isoft.moi.installment.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * A TrafficContract.
 */
@Entity
@Table(name = "traffic_contract")
public class TrafficContract implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "request_id")
    private Integer requestId;

    @Column(name = "request_no")
    private String requestNo;

    @Column(name = "request_service_code")
    private String requestServiceCode;

    @Column(name = "request_description")
    private String requestDescription;

    @Column(name = "plate_no")
    private String plateNo;

    @Column(name = "motor_no")
    private String motorNo;

    @Column(name = "chassis_no")
    private String chassisNo;

    @Column(name = "traffic_unit_code")
    private String trafficUnitCode;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public TrafficContract id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRequestId() {
        return this.requestId;
    }

    public TrafficContract requestId(Integer requestId) {
        this.setRequestId(requestId);
        return this;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public String getRequestNo() {
        return this.requestNo;
    }

    public TrafficContract requestNo(String requestNo) {
        this.setRequestNo(requestNo);
        return this;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }

    public String getRequestServiceCode() {
        return this.requestServiceCode;
    }

    public TrafficContract requestServiceCode(String requestServiceCode) {
        this.setRequestServiceCode(requestServiceCode);
        return this;
    }

    public void setRequestServiceCode(String requestServiceCode) {
        this.requestServiceCode = requestServiceCode;
    }

    public String getRequestDescription() {
        return this.requestDescription;
    }

    public TrafficContract requestDescription(String requestDescription) {
        this.setRequestDescription(requestDescription);
        return this;
    }

    public void setRequestDescription(String requestDescription) {
        this.requestDescription = requestDescription;
    }

    public String getPlateNo() {
        return this.plateNo;
    }

    public TrafficContract plateNo(String plateNo) {
        this.setPlateNo(plateNo);
        return this;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }

    public String getMotorNo() {
        return this.motorNo;
    }

    public TrafficContract motorNo(String motorNo) {
        this.setMotorNo(motorNo);
        return this;
    }

    public void setMotorNo(String motorNo) {
        this.motorNo = motorNo;
    }

    public String getChassisNo() {
        return this.chassisNo;
    }

    public TrafficContract chassisNo(String chassisNo) {
        this.setChassisNo(chassisNo);
        return this;
    }

    public void setChassisNo(String chassisNo) {
        this.chassisNo = chassisNo;
    }

    public String getTrafficUnitCode() {
        return this.trafficUnitCode;
    }

    public TrafficContract trafficUnitCode(String trafficUnitCode) {
        this.setTrafficUnitCode(trafficUnitCode);
        return this;
    }

    public void setTrafficUnitCode(String trafficUnitCode) {
        this.trafficUnitCode = trafficUnitCode;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TrafficContract)) {
            return false;
        }
        return id != null && id.equals(((TrafficContract) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TrafficContract{" +
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
