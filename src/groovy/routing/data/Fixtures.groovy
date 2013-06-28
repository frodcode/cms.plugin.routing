package routing.data

import org.hibernate.Session
import org.hibernate.SessionFactory
import org.hibernate.Query

class Fixtures {
    public static def load(def ctx, def defaultHost) {
//        SessionFactory sessionFactory = ctx.'sessionFactory';
//        Session session = sessionFactory.openSession();
//        Query query = session.createSQLQuery("insert into auth_role values (100, 0, 'kecy')");
//        query.executeUpdate()

        return [pages: [], pageTypes: []]
    }
}
