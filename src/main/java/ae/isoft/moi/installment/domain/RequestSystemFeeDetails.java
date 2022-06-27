package ae.isoft.moi.installment.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;

/**
 * A RequestSystemFeeDetails.
 */
@Entity
@Table(name = "request_system_fee_details")
public class RequestSystemFeeDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "fee_code")
    private String feeCode;

    @Column(name = "fee_name_ar")
    private String feeNameAr;

    @Column(name = "fee_name_en")
    private String feeNameEn;

    @Column(name = "fee_amount", precision = 21, scale = 2)
    private BigDecimal feeAmount;

    @ManyToOne
    @JsonIgnoreProperties(value = { "requestSystemFeeDetails", "request" }, allowSetters = true)
    private RequestSystemFee requestSystemFee;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public RequestSystemFeeDetails id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFeeCode() {
        return this.feeCode;
    }

    public RequestSystemFeeDetails feeCode(String feeCode) {
        this.setFeeCode(feeCode);
        return this;
    }

    public void setFeeCode(String feeCode) {
        this.feeCode = feeCode;
    }

    public String getFeeNameAr() {
        return this.feeNameAr;
    }

    public RequestSystemFeeDetails feeNameAr(String feeNameAr) {
        this.setFeeNameAr(feeNameAr);
        return this;
    }

    public void setFeeNameAr(String feeNameAr) {
        this.feeNameAr = feeNameAr;
    }

    public String getFeeNameEn() {
        return this.feeNameEn;
    }

    public RequestSystemFeeDetails feeNameEn(String feeNameEn) {
        this.setFeeNameEn(feeNameEn);
        return this;
    }

    public void setFeeNameEn(String feeNameEn) {
        this.feeNameEn = feeNameEn;
    }

    public BigDecimal getFeeAmount() {
        return this.feeAmount;
    }

    public RequestSystemFeeDetails feeAmount(BigDecimal feeAmount) {
        this.setFeeAmount(feeAmount);
        return this;
    }

    public void setFeeAmount(BigDecimal feeAmount) {
        this.feeAmount = feeAmount;
    }

    public RequestSystemFee getRequestSystemFee() {
        return this.requestSystemFee;
    }

    public void setRequestSystemFee(RequestSystemFee requestSystemFee) {
        this.requestSystemFee = requestSystemFee;
    }

    public RequestSystemFeeDetails requestSystemFee(RequestSystemFee requestSystemFee) {
        this.setRequestSystemFee(requestSystemFee);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RequestSystemFeeDetails)) {
            return false;
        }
        return id != null && id.equals(((RequestSystemFeeDetails) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RequestSystemFeeDetails{" +
            "id=" + getId() +
            ", feeCode='" + getFeeCode() + "'" +
            ", feeNameAr='" + getFeeNameAr() + "'" +
            ", feeNameEn='" + getFeeNameEn() + "'" +
            ", feeAmount=" + getFeeAmount() +
            "}";
    }
}
