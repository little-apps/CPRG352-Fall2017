package dataaccess;

import java.io.Serializable;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DBUtil implements Serializable {
    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("NotesPU");

    public static EntityManagerFactory getEmFactory() {
        return emf;
    }
}
