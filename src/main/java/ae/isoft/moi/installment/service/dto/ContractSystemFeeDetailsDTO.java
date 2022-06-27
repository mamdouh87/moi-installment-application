package ae.isoft.moi.installment.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link ae.isoft.moi.installment.domain.ContractSystemFeeDetails} entity.
 */
public class ContractSystemFeeDetailsDTO implements Serializable {

    private Long id;

    private String feeCode;

    private String feeNameAr;

    private String feeNameEn;

    private BigDecimal feeAmount;

    private Integer status;

    private Instant statusDate;

    private Integer draftContractSystemFee;

    private ContractSystemFeeDTO contractSystemFee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFeeCode() {
        return feeCode;
    }

    public void setFeeCode(String feeCode) {
        this.feeCode = feeCode;
    }

    public String getFeeNameAr() {
        return feeNameAr;
    }

    public void setFeeNameAr(String feeNameAr) {
        this.feeNameAr = feeNameAr;
    }

    public String getFeeNameEn() {
        return feeNameEn;
    }

    public void setFeeNameEn(String feeNameEn) {
        this.feeNameEn = feeNameEn;
    }

    public BigDecimal getFeeAmount() {
        return feeAmount;
    }

    public void setFeeAmount(BigDecimal feeAmount) {
        this.feeAmount = feeAmount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Instant getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(Instant statusDate) {
        this.statusDate = statusDate;
    }

    public Integer getDraftContractSystemFee() {
        return draftContractSystemFee;
    }

    public void setDraftContractSystemFee(Integer draftContractSystemFee) {
        this.draftContractSystemFee = draftContractSystemFee;
    }

    public ContractSystemFeeDTO getContractSystemFee() {
        return contractSystemFee;
    }

    public void setContractSystemFee(ContractSystemFeeDTO contractSystemFee) {
        this.contractSystemFee = contractSystemFee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ContractSystemFeeDetailsDTO)) {
            return false;
        }

        ContractSystemFeeDetailsDTO contractSystemFeeDetailsDTO = (ContractSystemFeeDetailsDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, contractSystemFeeDetailsDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ContractSystemFeeDetailsDTO{" +
            "id=" + getId() +
            ", feeCode='" + getFeeCode() + "'" +
            ", feeNameAr='" + getFeeNameAr() + "'" +
            ", feeNameEn='" + getFeeNameEn() + "'" +
            ", feeAmount=" + getFeeAmount() +
            ", status=" + getStatus() +
            ", statusDate='" + getStatusDate() + "'" +
            ", draftContractSystemFee=" + getDraftContractSystemFee() +
            ", contractSystemFee=" + getContractSystemFee() +
            "}";
    }
}
