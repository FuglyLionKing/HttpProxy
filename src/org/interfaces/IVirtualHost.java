package org.interfaces;

/**
 * Created with IntelliJ IDEA.
 * User: chris
 * Date: 02/04/14
 * Time: 08:36
 * To change this template use File | Settings | File Templates.
 */

public interface IVirtualHost
{
    //hosts
    String[] getHosts();

    //host
    String getHost();
    void setHost(String host);

    //lb
    String getRepartitionMethod();
    void setRepartitionMethod(String method);

    //workers
    String[] getWorker();

    //headers
    Object[] getHeaders();

    //incoming
    Object[] getIncoming();

    //add (incoming)
    Object[] getAddIncoming();

    //remove (incoming)
    Object[] getRemoveIncoming();

    //incoming add header
    String getIncomingAddHeader();

    //incoming remove header
    String getIncomingRemoveHeader();

    //outcoming
    Object[] getOutcoming();

    //add (outcoming)
    Object[] getOutcomingAdd();

    //remove (outcoming)
    Object[] getOutcomingRemove();

    //outcoming add header
    String getOutcomingAddHeader();

    //outcoming remove header
    String getOutcomingRemoveHeader();
}
