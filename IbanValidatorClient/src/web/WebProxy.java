package web;

public class WebProxy implements web.Web {
  private String _endpoint = null;
  private web.Web web = null;
  
  public WebProxy() {
    _initWebProxy();
  }
  
  public WebProxy(String endpoint) {
    _endpoint = endpoint;
    _initWebProxy();
  }
  
  private void _initWebProxy() {
    try {
      web = (new web.WebServiceLocator()).getWeb();
      if (web != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)web)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)web)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (web != null)
      ((javax.xml.rpc.Stub)web)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public web.Web getWeb() {
    if (web == null)
      _initWebProxy();
    return web;
  }
  
  public boolean validator(java.lang.String iban) throws java.rmi.RemoteException{
    if (web == null)
      _initWebProxy();
    return web.validator(iban);
  }
  
  
}