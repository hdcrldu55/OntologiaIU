package ec.edu.epn.guiaquito.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Basic
    @Column(name = "facebook_id")
    private Long facebookId;

    @Basic
    @Column(name = "first_name", length = 100)
    private String firstName;

    @Basic
    @Column(name = "last_name", length = 100)
    private String lastName;

    @Basic
    @Column(name = "birthday")
    private Date birthday;

    @Basic
    @Column(name = "email", length = 100)
    private String email;

    @Basic
    @Column(name = "facebook_token", length = 255)
    private String facebookToken;

    @Override
    public String toString() {
        return "id: " + id + "\tfacebookId: " + facebookId + "\tfirstName:" + firstName + "\tlastName" + lastName + "\tbirthday" + birthday + "\temail" + email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (facebookId != null ? !facebookId.equals(user.facebookId) : user.facebookId != null) return false;
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
        if (birthday != null ? !birthday.equals(user.birthday) : user.birthday != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (facebookId != null ? facebookId.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(Long facebookId) {
        this.facebookId = facebookId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFacebookToken() {
        return facebookToken;
    }

    public void setFacebookToken(String facebookToken) {
        this.facebookToken = facebookToken;
    }
}
