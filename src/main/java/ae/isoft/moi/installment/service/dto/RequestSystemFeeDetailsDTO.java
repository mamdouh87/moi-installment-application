package ae.isoft.moi.installment.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the {@link ae.isoft.moi.installment.domain.RequestSystemFeeDetails} entity.
 */
public class RequestSystemFeeDetailsDTO implements Serializable {

    private Long id;

    private String feeCode;

    private String feeNameAr;

    private String feeNameEn;

    private BigDecimal feeAmount;

    private RequestSystemFeeDTO requestSystemFee;

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

    public RequestSystemFeeDTO getRequestSystemFee() {
        return requestSystemFee;
    }

    public void setRequestSystemFee(RequestSystemFeeDTO requestSystemFee) {
        this.requestSystemFee = requestSystemFee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RequestSystemFeeDetailsDTO)) {
            return false;
        }

        RequestSystemFeeDetailsDTO requestSystemFeeDetailsDTO = (RequestSystemFeeDetailsDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, requestSystemFeeDetailsDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RequestSystemFeeDetailsDTO{" +
            "id=" + getId() +
            ", feeCode='" + getFeeCode() + "'" +
            ", feeNameAr='" + getFeeNameAr() + "'" +
            ", feeNameEn='" + getFeeNameEn() + "'" +
            ", feeAmount=" + getFeeAmount() +
            ", requestSystemFee=" + getRequestSystemFee() +
            "}";
    }
}
