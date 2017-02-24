package ec.edu.epn.guiaquito.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Path {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Basic
    @Column(name = "longitude", precision = 17)
    private Double longitude;

    @Basic
    @Column(name = "latitude", precision = 17)
    private Double latitude;

    @Basic
    @Column(name = "orientation", precision = 17)
    private Double orientation;

    @Basic
    @Column(name = "date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "sesion_id", insertable = false, updatable = false)
    private Session session;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Path that = (Path) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (longitude != null ? !longitude.equals(that.longitude) : that.longitude != null) return false;
        if (latitude != null ? !latitude.equals(that.latitude) : that.latitude != null) return false;
        if (orientation != null ? !orientation.equals(that.orientation) : that.orientation != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (session != null ? !session.equals(that.session) : that.session != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (longitude != null ? longitude.hashCode() : 0);
        result = 31 * result + (latitude != null ? latitude.hashCode() : 0);
        result = 31 * result + (orientation != null ? orientation.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (session != null ? session.hashCode() : 0);
        return result;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Double getOrientation() {
        return orientation;
    }

    public void setOrientation(Double orientation) {
        this.orientation = orientation;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
