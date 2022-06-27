package ae.isoft.moi.installment.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * A Customer.
 */
@Entity
@Table(name = "customer")
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "national_id")
    private String nationalId;

    @Column(name = "passport_no")
    private String passportNo;

    @Column(name = "country_id")
    private Integer countryId;

    @Column(name = "mobile_no")
    private String mobileNo;

    @Column(name = "address")
    private String address;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "trade_license")
    private String tradeLicense;

    @OneToOne
    @JoinColumn(unique = true)
    private Country country;

    @OneToMany(mappedBy = "customer")
    @JsonIgnoreProperties(value = { "requestsystemFees", "customer" }, allowSetters = true)
    private Set<Request> requests = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Customer id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNationalId() {
        return this.nationalId;
    }

    public Customer nationalId(String nationalId) {
        this.setNationalId(nationalId);
        return this;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getPassportNo() {
        return this.passportNo;
    }

    public Customer passportNo(String passportNo) {
        this.setPassportNo(passportNo);
        return this;
    }

    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    public Integer getCountryId() {
        return this.countryId;
    }

    public Customer countryId(Integer countryId) {
        this.setCountryId(countryId);
        return this;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public String getMobileNo() {
        return this.mobileNo;
    }

    public Customer mobileNo(String mobileNo) {
        this.setMobileNo(mobileNo);
        return this;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getAddress() {
        return this.address;
    }

    public Customer address(String address) {
        this.setAddress(address);
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFullName() {
        return this.fullName;
    }

    public Customer fullName(String fullName) {
        this.setFullName(fullName);
        return this;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getTradeLicense() {
        return this.tradeLicense;
    }

    public Customer tradeLicense(String tradeLicense) {
        this.setTradeLicense(tradeLicense);
        return this;
    }

    public void setTradeLicense(String tradeLicense) {
        this.tradeLicense = tradeLicense;
    }

    public Country getCountry() {
        return this.country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Customer country(Country country) {
        this.setCountry(country);
        return this;
    }

    public Set<Request> getRequests() {
        return this.requests;
    }

    public void setRequests(Set<Request> requests) {
        if (this.requests != null) {
            this.requests.forEach(i -> i.setCustomer(null));
        }
        if (requests != null) {
            requests.forEach(i -> i.setCustomer(this));
        }
        this.requests = requests;
    }

    public Customer requests(Set<Request> requests) {
        this.setRequests(requests);
        return this;
    }

    public Customer addRequests(Request request) {
        this.requests.add(request);
        request.setCustomer(this);
        return this;
    }

    public Customer removeRequests(Request request) {
        this.requests.remove(request);
        request.setCustomer(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Customer)) {
            return false;
        }
        return id != null && id.equals(((Customer) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Customer{" +
            "id=" + getId() +
            ", nationalId='" + getNationalId() + "'" +
            ", passportNo='" + getPassportNo() + "'" +
            ", countryId=" + getCountryId() +
            ", mobileNo='" + getMobileNo() + "'" +
            ", address='" + getAddress() + "'" +
            ", fullName='" + getFullName() + "'" +
            ", tradeLicense='" + getTradeLicense() + "'" +
            "}";
    }
}
