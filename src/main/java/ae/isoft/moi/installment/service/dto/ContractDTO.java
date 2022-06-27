package ae.isoft.moi.installment.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link ae.isoft.moi.installment.domain.Contract} entity.
 */
public class ContractDTO implements Serializable {

    private Long id;

    private String contractNo;

    private Integer status;

    private String mobileNo;

    private String address;

    private String fullName;

    private Integer customerId;

    private String nationalId;

    private String passportNo;

    private Integer countryId;

    private String tradeLicense;

    private Instant signDate;

    private Integer userId;

    private BigDecimal actualContractedAmount;

    private BigDecimal interestPercentage;

    private BigDecimal contractAmount;

    private TrafficContractDTO trafficContract;

    private InstallmentsPlanDTO installmentPlan;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getPassportNo() {
        return passportNo;
    }

    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public String getTradeLicense() {
        return tradeLicense;
    }

    public void setTradeLicense(String tradeLicense) {
        this.tradeLicense = tradeLicense;
    }

    public Instant getSignDate() {
        return signDate;
    }

    public void setSignDate(Instant signDate) {
        this.signDate = signDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BigDecimal getActualContractedAmount() {
        return actualContractedAmount;
    }

    public void setActualContractedAmount(BigDecimal actualContractedAmount) {
        this.actualContractedAmount = actualContractedAmount;
    }

    public BigDecimal getInterestPercentage() {
        return interestPercentage;
    }

    public void setInterestPercentage(BigDecimal interestPercentage) {
        this.interestPercentage = interestPercentage;
    }

    public BigDecimal getContractAmount() {
        return contractAmount;
    }

    public void setContractAmount(BigDecimal contractAmount) {
        this.contractAmount = contractAmount;
    }

    public TrafficContractDTO getTrafficContract() {
        return trafficContract;
    }

    public void setTrafficContract(TrafficContractDTO trafficContract) {
        this.trafficContract = trafficContract;
    }

    public InstallmentsPlanDTO getInstallmentPlan() {
        return installmentPlan;
    }

    public void setInstallmentPlan(InstallmentsPlanDTO installmentPlan) {
        this.installmentPlan = installmentPlan;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ContractDTO)) {
            return false;
        }

        ContractDTO contractDTO = (ContractDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, contractDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ContractDTO{" +
            "id=" + getId() +
            ", contractNo='" + getContractNo() + "'" +
            ", status=" + getStatus() +
            ", mobileNo='" + getMobileNo() + "'" +
            ", address='" + getAddress() + "'" +
            ", fullName='" + getFullName() + "'" +
            ", customerId=" + getCustomerId() +
            ", nationalId='" + getNationalId() + "'" +
            ", passportNo='" + getPassportNo() + "'" +
            ", countryId=" + getCountryId() +
            ", tradeLicense='" + getTradeLicense() + "'" +
            ", signDate='" + getSignDate() + "'" +
            ", userId=" + getUserId() +
            ", actualContractedAmount=" + getActualContractedAmount() +
            ", interestPercentage=" + getInterestPercentage() +
            ", contractAmount=" + getContractAmount() +
            ", trafficContract=" + getTrafficContract() +
            ", installmentPlan=" + getInstallmentPlan() +
            "}";
    }
}
