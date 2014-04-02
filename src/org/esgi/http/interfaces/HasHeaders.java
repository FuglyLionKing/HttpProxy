package org.esgi.http.interfaces;

/**
 * Created with IntelliJ IDEA.
 * User: FuglyLionKing
 * Date: 02/04/14
 * Time: 16:33
 * To change this template use File | Settings | File Templates.
 */
public interface HasHeaders {
    void addHeader(String key, String value);
    void removeHeader(String key);
}
