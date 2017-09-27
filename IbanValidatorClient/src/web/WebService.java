/**
 * WebService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package web;

public interface WebService extends javax.xml.rpc.Service {
    public java.lang.String getWebAddress();

    public web.Web getWeb() throws javax.xml.rpc.ServiceException;

    public web.Web getWeb(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
