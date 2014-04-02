package org.interfaces;

/**
 * Created with IntelliJ IDEA.
 * User: chris
 * Date: 02/04/2014
 * Time: 14:58
 * To change this template use File | Settings | File Templates.
 */
public interface HasHeader
{
    void addHeader(String key, String value);
    void removeHeader(String key);
}
