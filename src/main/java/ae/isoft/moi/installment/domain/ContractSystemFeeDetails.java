package ae.isoft.moi.installment.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import javax.persistence.*;

/**
 * A ContractSystemFeeDetails.
 */
@Entity
@Table(name = "contract_system_fee_details")
public class ContractSystemFeeDetails implements Serializable {

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

    @Column(name = "status")
    private Integer status;

    @Column(name = "status_date")
    private Instant statusDate;

    @Column(name = "draft_contract_system_fee")
    private Integer draftContractSystemFee;

    @ManyToOne
    @JsonIgnoreProperties(value = { "contractSystemFeeDetails", "contract" }, allowSetters = true)
    private ContractSystemFee contractSystemFee;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ContractSystemFeeDetails id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFeeCode() {
        return this.feeCode;
    }

    public ContractSystemFeeDetails feeCode(String feeCode) {
        this.setFeeCode(feeCode);
        return this;
    }

    public void setFeeCode(String feeCode) {
        this.feeCode = feeCode;
    }

    public String getFeeNameAr() {
        return this.feeNameAr;
    }

    public ContractSystemFeeDetails feeNameAr(String feeNameAr) {
        this.setFeeNameAr(feeNameAr);
        return this;
    }

    public void setFeeNameAr(String feeNameAr) {
        this.feeNameAr = feeNameAr;
    }

    public String getFeeNameEn() {
        return this.feeNameEn;
    }

    public ContractSystemFeeDetails feeNameEn(String feeNameEn) {
        this.setFeeNameEn(feeNameEn);
        return this;
    }

    public void setFeeNameEn(String feeNameEn) {
        this.feeNameEn = feeNameEn;
    }

    public BigDecimal getFeeAmount() {
        return this.feeAmount;
    }

    public ContractSystemFeeDetails feeAmount(BigDecimal feeAmount) {
        this.setFeeAmount(feeAmount);
        return this;
    }

    public void setFeeAmount(BigDecimal feeAmount) {
        this.feeAmount = feeAmount;
    }

    public Integer getStatus() {
        return this.status;
    }

    public ContractSystemFeeDetails status(Integer status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Instant getStatusDate() {
        return this.statusDate;
    }

    public ContractSystemFeeDetails statusDate(Instant statusDate) {
        this.setStatusDate(statusDate);
        return this;
    }

    public void setStatusDate(Instant statusDate) {
        this.statusDate = statusDate;
    }

    public Integer getDraftContractSystemFee() {
        return this.draftContractSystemFee;
    }

    public ContractSystemFeeDetails draftContractSystemFee(Integer draftContractSystemFee) {
        this.setDraftContractSystemFee(draftContractSystemFee);
        return this;
    }

    public void setDraftContractSystemFee(Integer draftContractSystemFee) {
        this.draftContractSystemFee = draftContractSystemFee;
    }

    public ContractSystemFee getContractSystemFee() {
        return this.contractSystemFee;
    }

    public void setContractSystemFee(ContractSystemFee contractSystemFee) {
        this.contractSystemFee = contractSystemFee;
    }

    public ContractSystemFeeDetails contractSystemFee(ContractSystemFee contractSystemFee) {
        this.setContractSystemFee(contractSystemFee);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ContractSystemFeeDetails)) {
            return false;
        }
        return id != null && id.equals(((ContractSystemFeeDetails) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ContractSystemFeeDetails{" +
            "id=" + getId() +
            ", feeCode='" + getFeeCode() + "'" +
            ", feeNameAr='" + getFeeNameAr() + "'" +
            ", feeNameEn='" + getFeeNameEn() + "'" +
            ", feeAmount=" + getFeeAmount() +
            ", status=" + getStatus() +
            ", statusDate='" + getStatusDate() + "'" +
            ", draftContractSystemFee=" + getDraftContractSystemFee() +
            "}";
    }
}
