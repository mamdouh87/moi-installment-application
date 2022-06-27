package ae.isoft.moi.installment.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;

/**
 * A InstallmentsPlan.
 */
@Entity
@Table(name = "installments_plan")
public class InstallmentsPlan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "status")
    private Integer status;

    @Column(name = "name_ar")
    private String nameAr;

    @Column(name = "name_en")
    private String nameEn;

    @Column(name = "number_of_installments")
    private Integer numberOfInstallments;

    @Column(name = "installment_interval_days")
    private Integer installmentIntervalDays;

    @Column(name = "interest_rate", precision = 21, scale = 2)
    private BigDecimal interestRate;

    @Column(name = "installment_grace_days")
    private Integer installmentGraceDays;

    @Column(name = "daily_late_percentage", precision = 21, scale = 2)
    private BigDecimal dailyLatePercentage;

    @Column(name = "daily_late_fee", precision = 21, scale = 2)
    private BigDecimal dailyLateFee;

    @Column(name = "max_total_amount", precision = 21, scale = 2)
    private BigDecimal maxTotalAmount;

    @Column(name = "min_total_amount", precision = 21, scale = 2)
    private BigDecimal minTotalAmount;

    @Column(name = "min_first_installment_amount", precision = 21, scale = 2)
    private BigDecimal minFirstInstallmentAmount;

    @Column(name = "creation_fees", precision = 21, scale = 2)
    private BigDecimal creationFees;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public InstallmentsPlan id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStatus() {
        return this.status;
    }

    public InstallmentsPlan status(Integer status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getNameAr() {
        return this.nameAr;
    }

    public InstallmentsPlan nameAr(String nameAr) {
        this.setNameAr(nameAr);
        return this;
    }

    public void setNameAr(String nameAr) {
        this.nameAr = nameAr;
    }

    public String getNameEn() {
        return this.nameEn;
    }

    public InstallmentsPlan nameEn(String nameEn) {
        this.setNameEn(nameEn);
        return this;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public Integer getNumberOfInstallments() {
        return this.numberOfInstallments;
    }

    public InstallmentsPlan numberOfInstallments(Integer numberOfInstallments) {
        this.setNumberOfInstallments(numberOfInstallments);
        return this;
    }

    public void setNumberOfInstallments(Integer numberOfInstallments) {
        this.numberOfInstallments = numberOfInstallments;
    }

    public Integer getInstallmentIntervalDays() {
        return this.installmentIntervalDays;
    }

    public InstallmentsPlan installmentIntervalDays(Integer installmentIntervalDays) {
        this.setInstallmentIntervalDays(installmentIntervalDays);
        return this;
    }

    public void setInstallmentIntervalDays(Integer installmentIntervalDays) {
        this.installmentIntervalDays = installmentIntervalDays;
    }

    public BigDecimal getInterestRate() {
        return this.interestRate;
    }

    public InstallmentsPlan interestRate(BigDecimal interestRate) {
        this.setInterestRate(interestRate);
        return this;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public Integer getInstallmentGraceDays() {
        return this.installmentGraceDays;
    }

    public InstallmentsPlan installmentGraceDays(Integer installmentGraceDays) {
        this.setInstallmentGraceDays(installmentGraceDays);
        return this;
    }

    public void setInstallmentGraceDays(Integer installmentGraceDays) {
        this.installmentGraceDays = installmentGraceDays;
    }

    public BigDecimal getDailyLatePercentage() {
        return this.dailyLatePercentage;
    }

    public InstallmentsPlan dailyLatePercentage(BigDecimal dailyLatePercentage) {
        this.setDailyLatePercentage(dailyLatePercentage);
        return this;
    }

    public void setDailyLatePercentage(BigDecimal dailyLatePercentage) {
        this.dailyLatePercentage = dailyLatePercentage;
    }

    public BigDecimal getDailyLateFee() {
        return this.dailyLateFee;
    }

    public InstallmentsPlan dailyLateFee(BigDecimal dailyLateFee) {
        this.setDailyLateFee(dailyLateFee);
        return this;
    }

    public void setDailyLateFee(BigDecimal dailyLateFee) {
        this.dailyLateFee = dailyLateFee;
    }

    public BigDecimal getMaxTotalAmount() {
        return this.maxTotalAmount;
    }

    public InstallmentsPlan maxTotalAmount(BigDecimal maxTotalAmount) {
        this.setMaxTotalAmount(maxTotalAmount);
        return this;
    }

    public void setMaxTotalAmount(BigDecimal maxTotalAmount) {
        this.maxTotalAmount = maxTotalAmount;
    }

    public BigDecimal getMinTotalAmount() {
        return this.minTotalAmount;
    }

    public InstallmentsPlan minTotalAmount(BigDecimal minTotalAmount) {
        this.setMinTotalAmount(minTotalAmount);
        return this;
    }

    public void setMinTotalAmount(BigDecimal minTotalAmount) {
        this.minTotalAmount = minTotalAmount;
    }

    public BigDecimal getMinFirstInstallmentAmount() {
        return this.minFirstInstallmentAmount;
    }

    public InstallmentsPlan minFirstInstallmentAmount(BigDecimal minFirstInstallmentAmount) {
        this.setMinFirstInstallmentAmount(minFirstInstallmentAmount);
        return this;
    }

    public void setMinFirstInstallmentAmount(BigDecimal minFirstInstallmentAmount) {
        this.minFirstInstallmentAmount = minFirstInstallmentAmount;
    }

    public BigDecimal getCreationFees() {
        return this.creationFees;
    }

    public InstallmentsPlan creationFees(BigDecimal creationFees) {
        this.setCreationFees(creationFees);
        return this;
    }

    public void setCreationFees(BigDecimal creationFees) {
        this.creationFees = creationFees;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InstallmentsPlan)) {
            return false;
        }
        return id != null && id.equals(((InstallmentsPlan) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InstallmentsPlan{" +
            "id=" + getId() +
            ", status=" + getStatus() +
            ", nameAr='" + getNameAr() + "'" +
            ", nameEn='" + getNameEn() + "'" +
            ", numberOfInstallments=" + getNumberOfInstallments() +
            ", installmentIntervalDays=" + getInstallmentIntervalDays() +
            ", interestRate=" + getInterestRate() +
            ", installmentGraceDays=" + getInstallmentGraceDays() +
            ", dailyLatePercentage=" + getDailyLatePercentage() +
            ", dailyLateFee=" + getDailyLateFee() +
            ", maxTotalAmount=" + getMaxTotalAmount() +
            ", minTotalAmount=" + getMinTotalAmount() +
            ", minFirstInstallmentAmount=" + getMinFirstInstallmentAmount() +
            ", creationFees=" + getCreationFees() +
            "}";
    }
}
