package ec.edu.epn.guiaquito.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Interest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Basic
    @Column(name = "name", length = 100)
    private String name;

    @Basic
    @Column(name = "description", length = 250)
    private String description;

    @Basic
    @Column(name = "longitude", precision = 17)
    private Double longitude;

    @Basic
    @Column(name = "latitude", precision = 17)
    private Double latitude;

    @Basic
    @Column(name = "rating", scale = 1, precision = 2)
    private BigDecimal rating;

    @ManyToOne
    @JoinColumn(name = "interest_type_id", insertable = false, updatable = false)
    private InterestType interestType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Interest interest = (Interest) o;

        if (id != null ? !id.equals(interest.id) : interest.id != null) return false;
        if (name != null ? !name.equals(interest.name) : interest.name != null) return false;
        if (description != null ? !description.equals(interest.description) : interest.description != null)
            return false;
        if (longitude != null ? !longitude.equals(interest.longitude) : interest.longitude != null) return false;
        if (latitude != null ? !latitude.equals(interest.latitude) : interest.latitude != null) return false;
        if (rating != null ? !rating.equals(interest.rating) : interest.rating != null) return false;
        if (interestType != null ? !interestType.equals(interest.interestType) : interest.interestType != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (longitude != null ? longitude.hashCode() : 0);
        result = 31 * result + (latitude != null ? latitude.hashCode() : 0);
        result = 31 * result + (rating != null ? rating.hashCode() : 0);
        result = 31 * result + (interestType != null ? interestType.hashCode() : 0);
        return result;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    public InterestType getInterestType() {
        return interestType;
    }

    public void setInterestType(InterestType interestType) {
        this.interestType = interestType;
    }
}
