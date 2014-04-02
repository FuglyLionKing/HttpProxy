package org.esgi.config;

/**
 * Created with IntelliJ IDEA.
 * User: chris
 * Date: 02/04/2014
 * Time: 12:17
 * To change this template use File | Settings | File Templates.
 */
public class Header
{
    public HeaderLine incoming;
    public HeaderLine outgoing;

    @Override
    public String toString(){
        return "[Incoming= "+incoming.toString()+" Outgoing= "+outgoing.toString()+"]";
    }
}
