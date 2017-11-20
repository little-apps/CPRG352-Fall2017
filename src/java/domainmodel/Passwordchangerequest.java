package domainmodel;

import domainmodel.User;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 704199
 */
@Entity
@Table(name = "passwordchangerequest")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Passwordchangerequest.findAll", query = "SELECT p FROM Passwordchangerequest p")
    , @NamedQuery(name = "Passwordchangerequest.findById", query = "SELECT p FROM Passwordchangerequest p WHERE p.id = :id")
    , @NamedQuery(name = "Passwordchangerequest.findByCreated", query = "SELECT p FROM Passwordchangerequest p WHERE p.created = :created")})
public class Passwordchangerequest implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @JoinColumn(name = "username", referencedColumnName = "Username")
    @ManyToOne(optional = false)
    private User username;

    public Passwordchangerequest() {
    }

    public Passwordchangerequest(String id) {
        this.id = id;
    }

    public Passwordchangerequest(String id, Date created) {
        this.id = id;
        this.created = created;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public User getUsername() {
        return username;
    }

    public void setUsername(User username) {
        this.username = username;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Passwordchangerequest)) {
            return false;
        }
        Passwordchangerequest other = (Passwordchangerequest) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "businesslogic.Passwordchangerequest[ id=" + id + " ]";
    }
    
}
