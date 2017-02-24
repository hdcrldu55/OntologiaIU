package ec.edu.epn.guiaquito.entities;

import javax.persistence.*;

@Entity
@Table(name = "interest_type")
public class InterestType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Basic
    @Column(name = "name_en", length = 100)
    private String nameEn;

    @Basic
    @Column(name = "name_es", length = 100)
    private String nameEs;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InterestType that = (InterestType) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (nameEn != null ? !nameEn.equals(that.nameEn) : that.nameEn != null) return false;
        if (nameEs != null ? !nameEs.equals(that.nameEs) : that.nameEs != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nameEn != null ? nameEn.hashCode() : 0);
        result = 31 * result + (nameEs != null ? nameEs.hashCode() : 0);
        return result;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String name) {
        this.nameEn = name;
    }

    public String getNameEs() {
        return nameEs;
    }

    public void setNameEs(String nombre) {
        this.nameEs = nombre;
    }
}
