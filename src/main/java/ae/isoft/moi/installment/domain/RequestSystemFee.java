package ae.isoft.moi.installment.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * A RequestSystemFee.
 */
@Entity
@Table(name = "request_system_fee")
public class RequestSystemFee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "systemcode")
    private String systemcode;

    @Column(name = "system_name_ar")
    private String systemNameAr;

    @Column(name = "system_name_en")
    private String systemNameEn;

    @Column(name = "system_total_fees", precision = 21, scale = 2)
    private BigDecimal systemTotalFees;

    @OneToMany(mappedBy = "requestSystemFee")
    @JsonIgnoreProperties(value = { "requestSystemFee" }, allowSetters = true)
    private Set<RequestSystemFeeDetails> requestSystemFeeDetails = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = { "requestsystemFees", "customer" }, allowSetters = true)
    private Request request;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public RequestSystemFee id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSystemcode() {
        return this.systemcode;
    }

    public RequestSystemFee systemcode(String systemcode) {
        this.setSystemcode(systemcode);
        return this;
    }

    public void setSystemcode(String systemcode) {
        this.systemcode = systemcode;
    }

    public String getSystemNameAr() {
        return this.systemNameAr;
    }

    public RequestSystemFee systemNameAr(String systemNameAr) {
        this.setSystemNameAr(systemNameAr);
        return this;
    }

    public void setSystemNameAr(String systemNameAr) {
        this.systemNameAr = systemNameAr;
    }

    public String getSystemNameEn() {
        return this.systemNameEn;
    }

    public RequestSystemFee systemNameEn(String systemNameEn) {
        this.setSystemNameEn(systemNameEn);
        return this;
    }

    public void setSystemNameEn(String systemNameEn) {
        this.systemNameEn = systemNameEn;
    }

    public BigDecimal getSystemTotalFees() {
        return this.systemTotalFees;
    }

    public RequestSystemFee systemTotalFees(BigDecimal systemTotalFees) {
        this.setSystemTotalFees(systemTotalFees);
        return this;
    }

    public void setSystemTotalFees(BigDecimal systemTotalFees) {
        this.systemTotalFees = systemTotalFees;
    }

    public Set<RequestSystemFeeDetails> getRequestSystemFeeDetails() {
        return this.requestSystemFeeDetails;
    }

    public void setRequestSystemFeeDetails(Set<RequestSystemFeeDetails> requestSystemFeeDetails) {
        if (this.requestSystemFeeDetails != null) {
            this.requestSystemFeeDetails.forEach(i -> i.setRequestSystemFee(null));
        }
        if (requestSystemFeeDetails != null) {
            requestSystemFeeDetails.forEach(i -> i.setRequestSystemFee(this));
        }
        this.requestSystemFeeDetails = requestSystemFeeDetails;
    }

    public RequestSystemFee requestSystemFeeDetails(Set<RequestSystemFeeDetails> requestSystemFeeDetails) {
        this.setRequestSystemFeeDetails(requestSystemFeeDetails);
        return this;
    }

    public RequestSystemFee addRequestSystemFeeDetails(RequestSystemFeeDetails requestSystemFeeDetails) {
        this.requestSystemFeeDetails.add(requestSystemFeeDetails);
        requestSystemFeeDetails.setRequestSystemFee(this);
        return this;
    }

    public RequestSystemFee removeRequestSystemFeeDetails(RequestSystemFeeDetails requestSystemFeeDetails) {
        this.requestSystemFeeDetails.remove(requestSystemFeeDetails);
        requestSystemFeeDetails.setRequestSystemFee(null);
        return this;
    }

    public Request getRequest() {
        return this.request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public RequestSystemFee request(Request request) {
        this.setRequest(request);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RequestSystemFee)) {
            return false;
        }
        return id != null && id.equals(((RequestSystemFee) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RequestSystemFee{" +
            "id=" + getId() +
            ", systemcode='" + getSystemcode() + "'" +
            ", systemNameAr='" + getSystemNameAr() + "'" +
            ", systemNameEn='" + getSystemNameEn() + "'" +
            ", systemTotalFees=" + getSystemTotalFees() +
            "}";
    }
}
