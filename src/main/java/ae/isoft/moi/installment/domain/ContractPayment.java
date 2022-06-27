package ae.isoft.moi.installment.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import javax.persistence.*;

/**
 * A ContractPayment.
 */
@Entity
@Table(name = "contract_payment")
public class ContractPayment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "status")
    private Integer status;

    @Column(name = "installment_no")
    private Integer installmentNo;

    @Column(name = "installment_amount", precision = 21, scale = 2)
    private BigDecimal installmentAmount;

    @Column(name = "installment_date")
    private Instant installmentDate;

    @Column(name = "installment_late_fees", precision = 21, scale = 2)
    private BigDecimal installmentLateFees;

    @Column(name = "payment_date")
    private Instant paymentDate;

    @Column(name = "payment_type")
    private Integer paymentType;

    @Column(name = "receipt_no")
    private String receiptNo;

    @Column(name = "creation_fees", precision = 21, scale = 2)
    private BigDecimal creationFees;

    @ManyToOne
    @JsonIgnoreProperties(value = { "trafficContract", "installmentPlan", "contractSystemFees", "contractPayments" }, allowSetters = true)
    private Contract contract;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ContractPayment id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStatus() {
        return this.status;
    }

    public ContractPayment status(Integer status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getInstallmentNo() {
        return this.installmentNo;
    }

    public ContractPayment installmentNo(Integer installmentNo) {
        this.setInstallmentNo(installmentNo);
        return this;
    }

    public void setInstallmentNo(Integer installmentNo) {
        this.installmentNo = installmentNo;
    }

    public BigDecimal getInstallmentAmount() {
        return this.installmentAmount;
    }

    public ContractPayment installmentAmount(BigDecimal installmentAmount) {
        this.setInstallmentAmount(installmentAmount);
        return this;
    }

    public void setInstallmentAmount(BigDecimal installmentAmount) {
        this.installmentAmount = installmentAmount;
    }

    public Instant getInstallmentDate() {
        return this.installmentDate;
    }

    public ContractPayment installmentDate(Instant installmentDate) {
        this.setInstallmentDate(installmentDate);
        return this;
    }

    public void setInstallmentDate(Instant installmentDate) {
        this.installmentDate = installmentDate;
    }

    public BigDecimal getInstallmentLateFees() {
        return this.installmentLateFees;
    }

    public ContractPayment installmentLateFees(BigDecimal installmentLateFees) {
        this.setInstallmentLateFees(installmentLateFees);
        return this;
    }

    public void setInstallmentLateFees(BigDecimal installmentLateFees) {
        this.installmentLateFees = installmentLateFees;
    }

    public Instant getPaymentDate() {
        return this.paymentDate;
    }

    public ContractPayment paymentDate(Instant paymentDate) {
        this.setPaymentDate(paymentDate);
        return this;
    }

    public void setPaymentDate(Instant paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Integer getPaymentType() {
        return this.paymentType;
    }

    public ContractPayment paymentType(Integer paymentType) {
        this.setPaymentType(paymentType);
        return this;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    public String getReceiptNo() {
        return this.receiptNo;
    }

    public ContractPayment receiptNo(String receiptNo) {
        this.setReceiptNo(receiptNo);
        return this;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }

    public BigDecimal getCreationFees() {
        return this.creationFees;
    }

    public ContractPayment creationFees(BigDecimal creationFees) {
        this.setCreationFees(creationFees);
        return this;
    }

    public void setCreationFees(BigDecimal creationFees) {
        this.creationFees = creationFees;
    }

    public Contract getContract() {
        return this.contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public ContractPayment contract(Contract contract) {
        this.setContract(contract);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ContractPayment)) {
            return false;
        }
        return id != null && id.equals(((ContractPayment) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ContractPayment{" +
            "id=" + getId() +
            ", status=" + getStatus() +
            ", installmentNo=" + getInstallmentNo() +
            ", installmentAmount=" + getInstallmentAmount() +
            ", installmentDate='" + getInstallmentDate() + "'" +
            ", installmentLateFees=" + getInstallmentLateFees() +
            ", paymentDate='" + getPaymentDate() + "'" +
            ", paymentType=" + getPaymentType() +
            ", receiptNo='" + getReceiptNo() + "'" +
            ", creationFees=" + getCreationFees() +
            "}";
    }
}
