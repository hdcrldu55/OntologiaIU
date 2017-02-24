package ec.edu.epn.guiaquito.entities;

import javax.persistence.*;

@Entity
@Table(name = "user_interest_type")
public class UserInterestType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Basic
    @Column(name = "user_id")
    private Integer userId;

    @ManyToOne
    @JoinColumn(name = "interest_type_id", insertable = false, updatable = false)
    private InterestType interestType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserInterestType that = (UserInterestType) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (interestType != null ? !interestType.equals(that.interestType) : that.interestType != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (interestType != null ? interestType.hashCode() : 0);
        return result;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public InterestType getInterestType() {
        return interestType;
    }

    public void setInterestType(InterestType interestType) {
        this.interestType = interestType;
    }
}
