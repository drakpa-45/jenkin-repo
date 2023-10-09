package bt.gov.ditt.dofps.base;

import bt.gov.ditt.dofps.dto.NewTimberDto;
import org.hibernate.Session;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Properties;

/**
 * Created by Pema Drakpa on 1/17/2020.
 */

public abstract class BaseDao {
    protected EntityManager em;

    @Resource(name = "queryProps")
    protected Properties properties;

    @PersistenceContext(name = "default")
    public void setEm(EntityManager em) {
        this.em = em;
    }

    protected Session getCurrentSession() {
        return em.unwrap(Session.class);
    }

    protected Session getReportingSession() {
        return getCurrentSession();
    }

    protected Session getArchivalSession() {
        return getCurrentSession();
    }

    /*protected org.hibernate.Query hibernateQuery(String query, Class dtoClazz) {
        return getCurrentSession().createSQLQuery(query).setResultTransformer(Transformers.aliasToBean(dtoClazz));
    }*/

    protected org.hibernate.Query hibernateReportingQuery(String query, Class dtoClazz) {
        return getReportingSession().createSQLQuery(query).setResultTransformer(Transformers.aliasToBean(dtoClazz));
    }

    protected org.hibernate.Query hibernateArchivalQuery(String query, Class dtoClazz) {
        return getArchivalSession().createSQLQuery(query).setResultTransformer(Transformers.aliasToBean(dtoClazz));
    }

    /*protected org.hibernate.Query hibernateQuery(String query) {
        return getCurrentSession().createSQLQuery(query);
    }*/

    protected org.hibernate.Query hibernateReportingQuery(String query) {
        return getReportingSession().createSQLQuery(query);
    }
    protected void save(Object obj) {
        getCurrentSession().save(obj);
    }

    protected List<NewTimberDto> saveOrUpdate(Object obj) {
        getCurrentSession().saveOrUpdate(obj);
        return null;
    }

    protected org.hibernate.Query hibernateArchivalQuery(String query) {
        return getArchivalSession().createSQLQuery(query);
    }

    protected Query persistenceQuery(String query, Class entityClazz) {
        return em.createNativeQuery(query, entityClazz);
    }

    protected Query persistenceQuery(String query) {
        return em.createNativeQuery(query);
    }

    protected org.hibernate.query.Query sqlQuery(String query, Class dtoClazz) {
        return getCurrentSession()
                .createSQLQuery(query)
                .setResultTransformer(new AliasToBeanResultTransformer(dtoClazz));
    }

    protected org.hibernate.Query hibernateQueryToken(String query, Class dtoClazz) {
        return getCurrentSession().createSQLQuery(query)
                .addScalar("id", StandardBasicTypes.INTEGER)
                .addScalar("access_token", StandardBasicTypes.STRING)
                .addScalar("scope", StandardBasicTypes.STRING)
                .addScalar("token_type", StandardBasicTypes.STRING)
                .addScalar("created_on", StandardBasicTypes.LONG)
                .addScalar("expires_in", StandardBasicTypes.INTEGER)
                .setResultTransformer(Transformers.aliasToBean(dtoClazz));
    }

    protected org.hibernate.query.Query sqlQuery1(String query) {
        return getCurrentSession().createSQLQuery(query);
    }

    protected org.hibernate.Query hibernateQuery(String query, Class dtoClazz) {
        return getCurrentSession().createSQLQuery(query).setResultTransformer(Transformers.aliasToBean(dtoClazz));
    }

    protected org.hibernate.Query hibernateQuery(String query) {
        return getCurrentSession().createSQLQuery(query);
    }

    protected void saveAndUpdate(Object obj) {
        getCurrentSession().saveOrUpdate(obj);
    }

}


