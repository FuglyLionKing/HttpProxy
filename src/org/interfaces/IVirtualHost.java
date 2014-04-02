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
    void setRepartitionMethod();

    //workers
    String[] getWorker();

    //headers
    String[] getHeaders();

    //incoming
    String getIncoming();
    void setIncoming(String incoming);

    //Add (incoming)
    String getAddIncoming();
    void setAddIncoming(String add);

    //outcoming
    String getOutcoming();
    void setOutcoming(String outcoming);

    String getOutcomingAdd();
    void setOutcomingAdd(String add);
}
