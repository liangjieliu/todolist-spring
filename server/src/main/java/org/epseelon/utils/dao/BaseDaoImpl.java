/*
 * @Id: $Id$
 */
package org.epseelon.utils.dao;

import java.io.Serializable;

/**
 * @author Alexander Snaps <asnaps@axen.be>
 * @version $Revision$
 * @param <E> Type of the entity
 * @param <ID> Type of the identifier of the entity.
 */
public abstract class BaseDaoImpl<E, ID extends Serializable>
        extends ReadOnlyBaseDaoImpl<E,ID> implements BaseDao<E, ID> {

    /**
     * {@inheritDoc}
     */
    public void persist(E entity) {
        entityManager.persist(entity);
    }

    public E merge(E entity) {
        return entityManager.merge(entity);
    }

    /**
     * {@inheritDoc}
     */
    public void remove(E entity) {
        entityManager.remove(entity);
    }


}
