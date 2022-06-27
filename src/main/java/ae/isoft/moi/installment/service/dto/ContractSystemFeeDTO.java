package ae.isoft.moi.installment.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link ae.isoft.moi.installment.domain.ContractSystemFee} entity.
 */
public class ContractSystemFeeDTO implements Serializable {

    private Long id;

    private String systemcode;

    private String systemNameAr;

    private String systemNameEn;

    private BigDecimal systemTotalFees;

    private Integer status;

    private Instant statusDate;

    private ContractDTO contract;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSystemcode() {
        return systemcode;
    }

    public void setSystemcode(String systemcode) {
        this.systemcode = systemcode;
    }

    public String getSystemNameAr() {
        return systemNameAr;
    }

    public void setSystemNameAr(String systemNameAr) {
        this.systemNameAr = systemNameAr;
    }

    public String getSystemNameEn() {
        return systemNameEn;
    }

    public void setSystemNameEn(String systemNameEn) {
        this.systemNameEn = systemNameEn;
    }

    public BigDecimal getSystemTotalFees() {
        return systemTotalFees;
    }

    public void setSystemTotalFees(BigDecimal systemTotalFees) {
        this.systemTotalFees = systemTotalFees;
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
        if (!(o instanceof ContractSystemFeeDTO)) {
            return false;
        }

        ContractSystemFeeDTO contractSystemFeeDTO = (ContractSystemFeeDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, contractSystemFeeDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ContractSystemFeeDTO{" +
            "id=" + getId() +
            ", systemcode='" + getSystemcode() + "'" +
            ", systemNameAr='" + getSystemNameAr() + "'" +
            ", systemNameEn='" + getSystemNameEn() + "'" +
            ", systemTotalFees=" + getSystemTotalFees() +
            ", status=" + getStatus() +
            ", statusDate='" + getStatusDate() + "'" +
            ", contract=" + getContract() +
            "}";
    }
}
