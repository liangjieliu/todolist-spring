/*
 * @Id: $Id$
 */
package org.epseelon.utils.dao;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author Alexander Snaps <asnaps@axen.be>
 * @version $Revision$
 * @param <E> The entity handled by this DAO.
 * @param <ID> The type of the id of the entity.
 */
public abstract class ReadOnlyBaseDaoImpl<E, ID extends Serializable>
        implements ReadOnlyBaseDao<E, ID> {

    @PersistenceContext
    protected EntityManager entityManager;
    protected Class<E> entityClass;
    protected String entityName = "";

    /**
     * Constructor for ReadOnlyBaseRepositoryImpl.
     */
    @SuppressWarnings("unchecked")
    public ReadOnlyBaseDaoImpl() {
        Type superclass = getClass().getGenericSuperclass();
        Type type = ((ParameterizedType) superclass).getActualTypeArguments()[0];
        if (type instanceof ParameterizedType) {
            this.entityClass = (Class<E>) ((ParameterizedType) type).getRawType();
        } else {
            this.entityClass = (Class<E>) type;
        }

        this.entityName = getEntityName(entityClass);
    }

    /**
     * {@inheritDoc}
     */
    public E getById(ID id) {
        return getById(id, false);
    }

    /**
     * {@inheritDoc}
     */
    public E getById(ID id, boolean onlyReference) {

        if (onlyReference) {
            return entityManager.getReference(entityClass, id);
        } else {
            return entityManager.find(entityClass, id);
        }
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<E> findAll() {
        Query query = createFindAllQuery(entityName);
        return query.getResultList();
    }

    /**
     * This returns a list a all entities of a specific class sorted in database by: ORDER id ASC <br>
     * Beware, I'm not sure this works vendor independent. As Hibernate is ok with have an Entity's id field referenced
     * by .id, whatever the field's name... Is this part of the specs?
     *
     * @param <V>                The type of the entities to find.
     * @param queriedEntityClass The Class of the entity you querying
     * @return A list of all instances of that entity class persisted
     */
    @SuppressWarnings("unchecked")
    protected <V> List<V> findAll(Class<V> queriedEntityClass) {
        String entityToFind = getEntityName(queriedEntityClass);
        Query allQuery = createFindAllQuery(entityToFind);
        return allQuery.getResultList();
    }

    /**
     * @param query The executed query containing the result.
     * @return The result list with correct type.
     */
    @SuppressWarnings("unchecked")
    protected List<E> getResultList(Query query) {
        return query.getResultList();
    }

    /**
     * @param entityClassToTranslate The class name to translate to an entity name.
     * @return The entity name representing the given class.
     */
    protected String getEntityName(Class<?> entityClassToTranslate) {
        String entityNameToReturn = "";

        Annotation[] annotations = entityClassToTranslate.getAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation instanceof Entity) {
                entityNameToReturn = ((Entity) annotation).name();
                break;
            }
        }

        if (entityNameToReturn.equals("")) {
            entityNameToReturn = entityClassToTranslate.getSimpleName();
        }
        return entityNameToReturn;
    }

    /**
     * @param entityToFind The entity to find all instances for.
     * @return The SQL needed to retrieve all instances.
     */
    private Query createFindAllQuery(String entityToFind) {
        return entityManager.createQuery("select E from " + entityToFind + " E where 1 = 1 order by E.id");
    }
}
