package ae.isoft.moi.installment.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * A ContractSystemFee.
 */
@Entity
@Table(name = "contract_system_fee")
public class ContractSystemFee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "systemcode")
    private String systemcode;

    @Column(name = "system_name_ar")
    private String systemNameAr;

    @Column(name = "system_name_en")
    private String systemNameEn;

    @Column(name = "system_total_fees", precision = 21, scale = 2)
    private BigDecimal systemTotalFees;

    @Column(name = "status")
    private Integer status;

    @Column(name = "status_date")
    private Instant statusDate;

    @OneToMany(mappedBy = "contractSystemFee")
    @JsonIgnoreProperties(value = { "contractSystemFee" }, allowSetters = true)
    private Set<ContractSystemFeeDetails> contractSystemFeeDetails = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = { "trafficContract", "installmentPlan", "contractSystemFees", "contractPayments" }, allowSetters = true)
    private Contract contract;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ContractSystemFee id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSystemcode() {
        return this.systemcode;
    }

    public ContractSystemFee systemcode(String systemcode) {
        this.setSystemcode(systemcode);
        return this;
    }

    public void setSystemcode(String systemcode) {
        this.systemcode = systemcode;
    }

    public String getSystemNameAr() {
        return this.systemNameAr;
    }

    public ContractSystemFee systemNameAr(String systemNameAr) {
        this.setSystemNameAr(systemNameAr);
        return this;
    }

    public void setSystemNameAr(String systemNameAr) {
        this.systemNameAr = systemNameAr;
    }

    public String getSystemNameEn() {
        return this.systemNameEn;
    }

    public ContractSystemFee systemNameEn(String systemNameEn) {
        this.setSystemNameEn(systemNameEn);
        return this;
    }

    public void setSystemNameEn(String systemNameEn) {
        this.systemNameEn = systemNameEn;
    }

    public BigDecimal getSystemTotalFees() {
        return this.systemTotalFees;
    }

    public ContractSystemFee systemTotalFees(BigDecimal systemTotalFees) {
        this.setSystemTotalFees(systemTotalFees);
        return this;
    }

    public void setSystemTotalFees(BigDecimal systemTotalFees) {
        this.systemTotalFees = systemTotalFees;
    }

    public Integer getStatus() {
        return this.status;
    }

    public ContractSystemFee status(Integer status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Instant getStatusDate() {
        return this.statusDate;
    }

    public ContractSystemFee statusDate(Instant statusDate) {
        this.setStatusDate(statusDate);
        return this;
    }

    public void setStatusDate(Instant statusDate) {
        this.statusDate = statusDate;
    }

    public Set<ContractSystemFeeDetails> getContractSystemFeeDetails() {
        return this.contractSystemFeeDetails;
    }

    public void setContractSystemFeeDetails(Set<ContractSystemFeeDetails> contractSystemFeeDetails) {
        if (this.contractSystemFeeDetails != null) {
            this.contractSystemFeeDetails.forEach(i -> i.setContractSystemFee(null));
        }
        if (contractSystemFeeDetails != null) {
            contractSystemFeeDetails.forEach(i -> i.setContractSystemFee(this));
        }
        this.contractSystemFeeDetails = contractSystemFeeDetails;
    }

    public ContractSystemFee contractSystemFeeDetails(Set<ContractSystemFeeDetails> contractSystemFeeDetails) {
        this.setContractSystemFeeDetails(contractSystemFeeDetails);
        return this;
    }

    public ContractSystemFee addContractSystemFeeDetails(ContractSystemFeeDetails contractSystemFeeDetails) {
        this.contractSystemFeeDetails.add(contractSystemFeeDetails);
        contractSystemFeeDetails.setContractSystemFee(this);
        return this;
    }

    public ContractSystemFee removeContractSystemFeeDetails(ContractSystemFeeDetails contractSystemFeeDetails) {
        this.contractSystemFeeDetails.remove(contractSystemFeeDetails);
        contractSystemFeeDetails.setContractSystemFee(null);
        return this;
    }

    public Contract getContract() {
        return this.contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public ContractSystemFee contract(Contract contract) {
        this.setContract(contract);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ContractSystemFee)) {
            return false;
        }
        return id != null && id.equals(((ContractSystemFee) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ContractSystemFee{" +
            "id=" + getId() +
            ", systemcode='" + getSystemcode() + "'" +
            ", systemNameAr='" + getSystemNameAr() + "'" +
            ", systemNameEn='" + getSystemNameEn() + "'" +
            ", systemTotalFees=" + getSystemTotalFees() +
            ", status=" + getStatus() +
            ", statusDate='" + getStatusDate() + "'" +
            "}";
    }
}
