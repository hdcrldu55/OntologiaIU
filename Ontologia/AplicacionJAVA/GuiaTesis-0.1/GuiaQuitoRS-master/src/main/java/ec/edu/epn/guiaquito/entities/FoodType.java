package ec.edu.epn.guiaquito.entities;

import javax.persistence.*;

@Entity
@Table(name = "food_type")
public class FoodType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Basic
    @Column(name = "name", length = 100)
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_interest_type_id", insertable = false, updatable = false)
    private UserInterestType userInterestType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FoodType that = (FoodType) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (userInterestType != null ? !userInterestType.equals(that.userInterestType) : that.userInterestType != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (userInterestType != null ? userInterestType.hashCode() : 0);
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

    public UserInterestType getUserInterestType() {
        return userInterestType;
    }

    public void setUserInterestType(UserInterestType userInterestType) {
        this.userInterestType = userInterestType;
    }
}
