package ae.isoft.moi.installment.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link ae.isoft.moi.installment.domain.ContractPayment} entity.
 */
public class ContractPaymentDTO implements Serializable {

    private Long id;

    private Integer status;

    private Integer installmentNo;

    private BigDecimal installmentAmount;

    private Instant installmentDate;

    private BigDecimal installmentLateFees;

    private Instant paymentDate;

    private Integer paymentType;

    private String receiptNo;

    private BigDecimal creationFees;

    private ContractDTO contract;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getInstallmentNo() {
        return installmentNo;
    }

    public void setInstallmentNo(Integer installmentNo) {
        this.installmentNo = installmentNo;
    }

    public BigDecimal getInstallmentAmount() {
        return installmentAmount;
    }

    public void setInstallmentAmount(BigDecimal installmentAmount) {
        this.installmentAmount = installmentAmount;
    }

    public Instant getInstallmentDate() {
        return installmentDate;
    }

    public void setInstallmentDate(Instant installmentDate) {
        this.installmentDate = installmentDate;
    }

    public BigDecimal getInstallmentLateFees() {
        return installmentLateFees;
    }

    public void setInstallmentLateFees(BigDecimal installmentLateFees) {
        this.installmentLateFees = installmentLateFees;
    }

    public Instant getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Instant paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }

    public BigDecimal getCreationFees() {
        return creationFees;
    }

    public void setCreationFees(BigDecimal creationFees) {
        this.creationFees = creationFees;
    }

    public ContractDTO getContract() {
        return contract;
    }

    public void setContract(ContractDTO contract) {
        this.contract = contract;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ContractPaymentDTO)) {
            return false;
        }

        ContractPaymentDTO contractPaymentDTO = (ContractPaymentDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, contractPaymentDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ContractPaymentDTO{" +
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
            ", contract=" + getContract() +
            "}";
    }
}
