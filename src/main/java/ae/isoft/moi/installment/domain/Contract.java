package ae.isoft.moi.installment.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * A Contract.
 */
@Entity
@Table(name = "contract")
public class Contract implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "contract_no")
    private String contractNo;

    @Column(name = "status")
    private Integer status;

    @Column(name = "mobile_no")
    private String mobileNo;

    @Column(name = "address")
    private String address;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "customer_id")
    private Integer customerId;

    @Column(name = "national_id")
    private String nationalId;

    @Column(name = "passport_no")
    private String passportNo;

    @Column(name = "country_id")
    private Integer countryId;

    @Column(name = "trade_license")
    private String tradeLicense;

    @Column(name = "sign_date")
    private Instant signDate;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "actual_contracted_amount", precision = 21, scale = 2)
    private BigDecimal actualContractedAmount;

    @Column(name = "interest_percentage", precision = 21, scale = 2)
    private BigDecimal interestPercentage;

    @Column(name = "contract_amount", precision = 21, scale = 2)
    private BigDecimal contractAmount;

    @OneToOne
    @JoinColumn(unique = true)
    private TrafficContract trafficContract;

    @OneToOne
    @JoinColumn(unique = true)
    private InstallmentsPlan installmentPlan;

    @OneToMany(mappedBy = "contract")
    @JsonIgnoreProperties(value = { "contractSystemFeeDetails", "contract" }, allowSetters = true)
    private Set<ContractSystemFee> contractSystemFees = new HashSet<>();

    @OneToMany(mappedBy = "contract")
    @JsonIgnoreProperties(value = { "contract" }, allowSetters = true)
    private Set<ContractPayment> contractPayments = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Contract id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContractNo() {
        return this.contractNo;
    }

    public Contract contractNo(String contractNo) {
        this.setContractNo(contractNo);
        return this;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public Integer getStatus() {
        return this.status;
    }

    public Contract status(Integer status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMobileNo() {
        return this.mobileNo;
    }

    public Contract mobileNo(String mobileNo) {
        this.setMobileNo(mobileNo);
        return this;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getAddress() {
        return this.address;
    }

    public Contract address(String address) {
        this.setAddress(address);
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFullName() {
        return this.fullName;
    }

    public Contract fullName(String fullName) {
        this.setFullName(fullName);
        return this;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getCustomerId() {
        return this.customerId;
    }

    public Contract customerId(Integer customerId) {
        this.setCustomerId(customerId);
        return this;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getNationalId() {
        return this.nationalId;
    }

    public Contract nationalId(String nationalId) {
        this.setNationalId(nationalId);
        return this;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getPassportNo() {
        return this.passportNo;
    }

    public Contract passportNo(String passportNo) {
        this.setPassportNo(passportNo);
        return this;
    }

    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    public Integer getCountryId() {
        return this.countryId;
    }

    public Contract countryId(Integer countryId) {
        this.setCountryId(countryId);
        return this;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public String getTradeLicense() {
        return this.tradeLicense;
    }

    public Contract tradeLicense(String tradeLicense) {
        this.setTradeLicense(tradeLicense);
        return this;
    }

    public void setTradeLicense(String tradeLicense) {
        this.tradeLicense = tradeLicense;
    }

    public Instant getSignDate() {
        return this.signDate;
    }

    public Contract signDate(Instant signDate) {
        this.setSignDate(signDate);
        return this;
    }

    public void setSignDate(Instant signDate) {
        this.signDate = signDate;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public Contract userId(Integer userId) {
        this.setUserId(userId);
        return this;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BigDecimal getActualContractedAmount() {
        return this.actualContractedAmount;
    }

    public Contract actualContractedAmount(BigDecimal actualContractedAmount) {
        this.setActualContractedAmount(actualContractedAmount);
        return this;
    }

    public void setActualContractedAmount(BigDecimal actualContractedAmount) {
        this.actualContractedAmount = actualContractedAmount;
    }

    public BigDecimal getInterestPercentage() {
        return this.interestPercentage;
    }

    public Contract interestPercentage(BigDecimal interestPercentage) {
        this.setInterestPercentage(interestPercentage);
        return this;
    }

    public void setInterestPercentage(BigDecimal interestPercentage) {
        this.interestPercentage = interestPercentage;
    }

    public BigDecimal getContractAmount() {
        return this.contractAmount;
    }

    public Contract contractAmount(BigDecimal contractAmount) {
        this.setContractAmount(contractAmount);
        return this;
    }

    public void setContractAmount(BigDecimal contractAmount) {
        this.contractAmount = contractAmount;
    }

    public TrafficContract getTrafficContract() {
        return this.trafficContract;
    }

    public void setTrafficContract(TrafficContract trafficContract) {
        this.trafficContract = trafficContract;
    }

    public Contract trafficContract(TrafficContract trafficContract) {
        this.setTrafficContract(trafficContract);
        return this;
    }

    public InstallmentsPlan getInstallmentPlan() {
        return this.installmentPlan;
    }

    public void setInstallmentPlan(InstallmentsPlan installmentsPlan) {
        this.installmentPlan = installmentsPlan;
    }

    public Contract installmentPlan(InstallmentsPlan installmentsPlan) {
        this.setInstallmentPlan(installmentsPlan);
        return this;
    }

    public Set<ContractSystemFee> getContractSystemFees() {
        return this.contractSystemFees;
    }

    public void setContractSystemFees(Set<ContractSystemFee> contractSystemFees) {
        if (this.contractSystemFees != null) {
            this.contractSystemFees.forEach(i -> i.setContract(null));
        }
        if (contractSystemFees != null) {
            contractSystemFees.forEach(i -> i.setContract(this));
        }
        this.contractSystemFees = contractSystemFees;
    }

    public Contract contractSystemFees(Set<ContractSystemFee> contractSystemFees) {
        this.setContractSystemFees(contractSystemFees);
        return this;
    }

    public Contract addContractSystemFees(ContractSystemFee contractSystemFee) {
        this.contractSystemFees.add(contractSystemFee);
        contractSystemFee.setContract(this);
        return this;
    }

    public Contract removeContractSystemFees(ContractSystemFee contractSystemFee) {
        this.contractSystemFees.remove(contractSystemFee);
        contractSystemFee.setContract(null);
        return this;
    }

    public Set<ContractPayment> getContractPayments() {
        return this.contractPayments;
    }

    public void setContractPayments(Set<ContractPayment> contractPayments) {
        if (this.contractPayments != null) {
            this.contractPayments.forEach(i -> i.setContract(null));
        }
        if (contractPayments != null) {
            contractPayments.forEach(i -> i.setContract(this));
        }
        this.contractPayments = contractPayments;
    }

    public Contract contractPayments(Set<ContractPayment> contractPayments) {
        this.setContractPayments(contractPayments);
        return this;
    }

    public Contract addContractPayments(ContractPayment contractPayment) {
        this.contractPayments.add(contractPayment);
        contractPayment.setContract(this);
        return this;
    }

    public Contract removeContractPayments(ContractPayment contractPayment) {
        this.contractPayments.remove(contractPayment);
        contractPayment.setContract(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Contract)) {
            return false;
        }
        return id != null && id.equals(((Contract) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Contract{" +
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
            "}";
    }
}
