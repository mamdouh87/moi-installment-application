package ae.isoft.moi.installment.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the {@link ae.isoft.moi.installment.domain.RequestSystemFee} entity.
 */
public class RequestSystemFeeDTO implements Serializable {

    private Long id;

    private String systemcode;

    private String systemNameAr;

    private String systemNameEn;

    private BigDecimal systemTotalFees;

    private RequestDTO request;

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

    public RequestDTO getRequest() {
        return request;
    }

    public void setRequest(RequestDTO request) {
        this.request = request;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RequestSystemFeeDTO)) {
            return false;
        }

        RequestSystemFeeDTO requestSystemFeeDTO = (RequestSystemFeeDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, requestSystemFeeDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RequestSystemFeeDTO{" +
            "id=" + getId() +
            ", systemcode='" + getSystemcode() + "'" +
            ", systemNameAr='" + getSystemNameAr() + "'" +
            ", systemNameEn='" + getSystemNameEn() + "'" +
            ", systemTotalFees=" + getSystemTotalFees() +
            ", request=" + getRequest() +
            "}";
    }
}
