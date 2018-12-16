package domainmodel;

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
@Table(name = "PasswordChangeRequest")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PasswordChangeRequest.findAll", query = "SELECT p FROM PasswordChangeRequest p")
    , @NamedQuery(name = "PasswordChangeRequest.findById", query = "SELECT p FROM PasswordChangeRequest p WHERE p.id = :id")
    , @NamedQuery(name = "PasswordChangeRequest.findByCreated", query = "SELECT p FROM PasswordChangeRequest p WHERE p.created = :created")})
public class PasswordChangeRequest implements Serializable {

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

    public PasswordChangeRequest() {
    }

    public PasswordChangeRequest(String id) {
        this.id = id;
    }

    public PasswordChangeRequest(String id, Date created) {
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
        if (!(object instanceof PasswordChangeRequest)) {
            return false;
        }
        PasswordChangeRequest other = (PasswordChangeRequest) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "businesslogic.PasswordChangeRequest[ id=" + id + " ]";
    }
    
}
