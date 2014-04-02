package org.utils;

import org.interfaces.IVirtualHost;

/**
 * Created with IntelliJ IDEA.
 * User: chris
 * Date: 02/04/14
 * Time: 10:21
 * To change this template use File | Settings | File Templates.
 */
public class VirtualHost implements IVirtualHost {

    private String[] hosts;
    private String host;
    private String repartitionMethod;
    private String[] workers;
    private Object[] headers;

    private Object[] incomings;
    private Object[] incomingAdd;
    private Object[] incomingRemove;
    private String incomingAddHeader;
    private String incomingRemoveHeader;

    private Object[] outcomings;
    private Object[] outcomingAdd;
    private Object[] outcomingRemove;
    private String outcomingAddHeader;
    private String outcomingRemoveHeader;


    @Override
    public String[] getHosts() {
        return hosts;
    }

    @Override
    public String getHost(){
        return host;
    }

    @Override
    public void setHost(String host){
        this.host = host;
    }

    @Override
    public String getRepartitionMethod() {
        return this.repartitionMethod;
    }

    @Override
    public void setRepartitionMethod(String method) {
        this.repartitionMethod=method;
    }

    @Override
    public String[] getWorker() {
        return this.workers;
    }

    @Override
    public Object[] getHeaders() {
        return this.headers;
    }

    @Override
    public Object[] getIncoming() {
        return this.incomings;
    }

    @Override
    public Object[] getAddIncoming() {
        return this.incomingAdd;
    }

    @Override
    public Object[] getRemoveIncoming() {
        return this.incomingRemove;
    }

    @Override
    public String getIncomingAddHeader() {
        return this.incomingAddHeader;
    }

    @Override
    public String getIncomingRemoveHeader() {
        return this.incomingRemoveHeader;
    }


    @Override
    public Object[] getOutcoming() {
        return this.outcomings;
    }


    @Override
    public Object[] getOutcomingAdd() {
        return this.outcomingAdd;
    }

    @Override
    public Object[] getOutcomingRemove() {
        return this.outcomingRemove;
    }

    @Override
    public String getOutcomingAddHeader() {
        return this.outcomingAddHeader;
    }

    @Override
    public String getOutcomingRemoveHeader() {
        return this.outcomingRemoveHeader;
    }

}
