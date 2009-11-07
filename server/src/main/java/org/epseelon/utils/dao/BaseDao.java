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
public interface BaseDao<E, ID extends Serializable>
        extends ReadOnlyBaseDao<E, ID> {

    /**
     * Persist (INSERT) the given entity in the database.
     *
     * @param entity The entity to persist.
     */
    void persist(E entity);

    /**
     * Remove (DELETE) the entity from the database.
     *
     * @param entity The entity to delete.
     */
    void remove(E entity);

    /**
     * Persist (UPDATE) the given entity in the database.
     *
     * @param entity The entity to persist.
     * @return the merged entity
     */
    E merge(E entity);
}

