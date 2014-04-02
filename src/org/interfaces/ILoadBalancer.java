package org.interfaces;

import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: chris
 * Date: 02/04/2014
 * Time: 14:56
 * To change this template use File | Settings | File Templates.
 */
public interface ILoadBalancer
{
    Socket getConnection();
}
