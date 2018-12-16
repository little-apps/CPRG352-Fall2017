package dataaccess;

import domainmodel.PasswordChangeRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class PasswordChangeRequestDB {

    public int insert(PasswordChangeRequest passwordChangeRequest) throws PasswordChangeRequestException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            trans.begin();
            em.persist(passwordChangeRequest);
            trans.commit();
            return 1;
        } catch (Exception ex) {
            trans.rollback();
            Logger.getLogger(PasswordChangeRequestDB.class.getName()).log(Level.SEVERE, "Cannot insert " + passwordChangeRequest.toString(), ex);
            throw new PasswordChangeRequestException("Error inserting password change request");
        } finally {
            em.close();
        }
    }

    public int update(PasswordChangeRequest passwordChangeRequest) throws PasswordChangeRequestException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            trans.begin();
            em.merge(passwordChangeRequest);
            trans.commit();
            return 1;
        } catch (Exception ex) {
            trans.rollback();
            Logger.getLogger(PasswordChangeRequestDB.class.getName()).log(Level.SEVERE, "Cannot update " + passwordChangeRequest.toString(), ex);
            throw new PasswordChangeRequestException("Error updating password change request");
        } finally {
            em.close();
        }
    }

    public List<PasswordChangeRequest> getAll() throws PasswordChangeRequestException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            List<PasswordChangeRequest> passwordChangeRequests = em.createNamedQuery("PasswordChangeRequest.findAll", PasswordChangeRequest.class).getResultList();
            return passwordChangeRequests;
        } catch (Exception ex) {
            Logger.getLogger(PasswordChangeRequestDB.class.getName()).log(Level.SEVERE, "Cannot read password change request", ex);
            throw new PasswordChangeRequestException("Error getting password change request");
        } finally {
            em.close();
        }
    }

    /**
     * Get a single user by their username.
     *
     * @param id The unique id.
     * @return A User object if found, null otherwise.
     * @throws NotesDBException
     */
    public PasswordChangeRequest getId(String id) throws PasswordChangeRequestException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            PasswordChangeRequest passwordChangeRequest = em.find(PasswordChangeRequest.class, id);
            return passwordChangeRequest;
        } catch (Exception ex) {
            Logger.getLogger(PasswordChangeRequestDB.class.getName()).log(Level.SEVERE, "Cannot read password change request", ex);
            throw new PasswordChangeRequestException("Error getting password change request");
        } finally {
            em.close();
        }
    }

    public int delete(PasswordChangeRequest passwordChangeRequest) throws PasswordChangeRequestException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            trans.begin();
            em.remove(em.merge(passwordChangeRequest));
            trans.commit();
            return 1;
        } catch (Exception ex) {
            trans.rollback();
            Logger.getLogger(PasswordChangeRequestDB.class.getName()).log(Level.SEVERE, "Cannot delete " + passwordChangeRequest.toString(), ex);
            throw new PasswordChangeRequestException("Error deleting password change request");
        } finally {
            em.close();
        }
    }
}
