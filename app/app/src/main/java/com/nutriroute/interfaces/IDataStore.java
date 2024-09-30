package com.nutriroute.interfaces;

import com.nutriroute.models.GenericUser;
import com.nutriroute.models.Menu;
import com.nutriroute.models.Request;
import com.nutriroute.models.Restaurant;

import java.util.Collection;

/**
 * This is the interface for data store. The type parameterization can be disregarded if not using
 * the parameterized base classes.
 * T is the id type.
 */
public interface IDataStore<T extends Comparable<T>> {

    /**
     * These are the get methods for the different types of objects.
     * Should be O(1) time complexity
     * @typeparam T the type of the id, if applicable. The returned object
     * parameterization is not guaranteed to be T.
     *
     * Example use: ClassOrSuperclass = IDataStoreImpl.getUser(someId);
     *
     * @param id
     * @return
     */
    GenericUser<T> getUser(T id);
    Restaurant getRestaurant(String id);
    Menu getMenu(String id);
    Request<T> getRequest(T id);

    /**
     * These are the create/modify methods for the different types of objects.
     * NOTE: It creates a new object if the object does not exist in the database, so check
     * if the object exists before calling this method, if this behavior is not desired.
     *
     * This is a wrapper call around the DB and LRU cache update methods.
     * It updates both the LRU cache entry (if it exists) and the database entry.
     *
     * Example use: IDataStoreImpl.setUser(user, id);
     *
     * @typeparam T the type of the id, if applicable.
     * @param user the user object
     * @param id the id of the user
     *
     * @return
     */
    boolean setUser(GenericUser<T> user, T id);
    boolean setRestaurant(Restaurant restaurant);
    boolean setMenu(Menu menu);
    boolean setRequest(Request<T> request, T id);

    // TODO: DELETE METHODS


    //!!!!!!!!!!!!! ONLY USE THE BELOW METHODS IF YOU KNOW WHAT THEY DO */

    /**
     * This is the method to force the loading of the entire set of objects of a given
     * type from the database. This is useful for when the app starts.
     * O(n).
     * Once the objects are loaded from the database, the callback is called.
     * @param objClass the objects you want to load
     * @param callback the callback to call when the objects are loaded
     * @param u optional user type if it is a user class
     */
    void _loadObjFromDB(Class<?> objClass, Runnable callback);


    /**
     * These are the standard 3 C-UD operations.
     * @param objClass the objects you want to load
     * @param callback the callback to call when the objects are loaded
     * @param u optional user type if it is a user class
     */
    boolean _addObjectToDB(Object obj, String id);

    boolean _updateObjectToDB(Object obj, String id);

    boolean _deleteObjectFromDB(Object obj, String id);


    int isPendingUpdate();

    Collection<Restaurant> getRestaurants();
}
