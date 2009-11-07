/*
 * @Id: $Id$
 */
package org.epseelon.utils.dao;

import java.io.Serializable;
import java.util.List;

/**
 * @author Alexander Snaps <asnaps@axen.be>
 * @version $Revision$
 * @param <E> Type of the entity
 * @param <ID> Type of the identifier of the entity.
 */
public interface ReadOnlyBaseDao<E, ID extends Serializable> {
    /**
     * @param id
     *            The unique identification of the entity.
     * @return The entity identified by the id.
     */
    E getById(ID id);

    /**
     * @param id
     *            The unique identification of the entity.
     * @param onlyReference
     *            If true, the returned entity can only be used to reference to, not all fields are retrieved.
     * @return The entity identified by the id.
     */
    E getById(ID id, boolean onlyReference);

    /**
     * @return All entities in the database.
     */
    List<E> findAll();
}
