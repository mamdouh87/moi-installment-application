package ae.isoft.moi.installment.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the {@link ae.isoft.moi.installment.domain.InstallmentsPlan} entity.
 */
public class InstallmentsPlanDTO implements Serializable {

    private Long id;

    private Integer status;

    private String nameAr;

    private String nameEn;

    private Integer numberOfInstallments;

    private Integer installmentIntervalDays;

    private BigDecimal interestRate;

    private Integer installmentGraceDays;

    private BigDecimal dailyLatePercentage;

    private BigDecimal dailyLateFee;

    private BigDecimal maxTotalAmount;

    private BigDecimal minTotalAmount;

    private BigDecimal minFirstInstallmentAmount;

    private BigDecimal creationFees;

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

    public String getNameAr() {
        return nameAr;
    }

    public void setNameAr(String nameAr) {
        this.nameAr = nameAr;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public Integer getNumberOfInstallments() {
        return numberOfInstallments;
    }

    public void setNumberOfInstallments(Integer numberOfInstallments) {
        this.numberOfInstallments = numberOfInstallments;
    }

    public Integer getInstallmentIntervalDays() {
        return installmentIntervalDays;
    }

    public void setInstallmentIntervalDays(Integer installmentIntervalDays) {
        this.installmentIntervalDays = installmentIntervalDays;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public Integer getInstallmentGraceDays() {
        return installmentGraceDays;
    }

    public void setInstallmentGraceDays(Integer installmentGraceDays) {
        this.installmentGraceDays = installmentGraceDays;
    }

    public BigDecimal getDailyLatePercentage() {
        return dailyLatePercentage;
    }

    public void setDailyLatePercentage(BigDecimal dailyLatePercentage) {
        this.dailyLatePercentage = dailyLatePercentage;
    }

    public BigDecimal getDailyLateFee() {
        return dailyLateFee;
    }

    public void setDailyLateFee(BigDecimal dailyLateFee) {
        this.dailyLateFee = dailyLateFee;
    }

    public BigDecimal getMaxTotalAmount() {
        return maxTotalAmount;
    }

    public void setMaxTotalAmount(BigDecimal maxTotalAmount) {
        this.maxTotalAmount = maxTotalAmount;
    }

    public BigDecimal getMinTotalAmount() {
        return minTotalAmount;
    }

    public void setMinTotalAmount(BigDecimal minTotalAmount) {
        this.minTotalAmount = minTotalAmount;
    }

    public BigDecimal getMinFirstInstallmentAmount() {
        return minFirstInstallmentAmount;
    }

    public void setMinFirstInstallmentAmount(BigDecimal minFirstInstallmentAmount) {
        this.minFirstInstallmentAmount = minFirstInstallmentAmount;
    }

    public BigDecimal getCreationFees() {
        return creationFees;
    }

    public void setCreationFees(BigDecimal creationFees) {
        this.creationFees = creationFees;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InstallmentsPlanDTO)) {
            return false;
        }

        InstallmentsPlanDTO installmentsPlanDTO = (InstallmentsPlanDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, installmentsPlanDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InstallmentsPlanDTO{" +
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
