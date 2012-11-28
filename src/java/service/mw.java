package service;

import LockManager.DeadlockException;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.WebServiceRef;
import mw.MwImpl;
import rm.DeadlockException_Exception;
import rm.Rmcar_Service;

@WebService(serviceName = "mw")
public class mw 
{
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/rmcar.jelastic.servint.net/rm/rmcar.wsdl")
    private Rmcar_Service service;
  
    @WebMethod(operationName = "start")
    public int start()
    {
        return new MwImpl().start();
    }
    
    @WebMethod(operationName = "commit")
    public boolean commit(@WebParam(name = "id") int id)
    {
        boolean a = new MwImpl().commit(id);
        boolean b = commit1(id, 0);
        return (a && b);
    }
    
    @WebMethod(operationName = "abort")
    public boolean abort(@WebParam(name = "id") int id)
    {
        boolean a = new MwImpl().abort(id);
        boolean b = abort1(id, 0);
        return (a && b);
    }
    
    @WebMethod(operationName = "newcustomer")
    public int newcustomer(@WebParam(name = "id") int id) throws DeadlockException
    {
        return new MwImpl().newCustomer(id);
    }
    
    @WebMethod(operationName = "newcustomerid")
    public boolean newcustomerid(@WebParam(name = "id") int id, @WebParam(name = "custid") int custid) throws DeadlockException
    {
        return new MwImpl().newCustomer(id, custid);
    }
    
    @WebMethod(operationName = "addflight")
    public boolean addflight(@WebParam(name = "id") int id, @WebParam(name = "num") int num, 
                             @WebParam(name = "seats") int seats, @WebParam(name = "price") int price)
    {
            try 
            {
            return addflight1(id, num, seats, price);
        }
            catch (DeadlockException_Exception ex) 
        {
           return false;
        }
    }

    private boolean addflight1(int id, int num, int seats, int price) throws DeadlockException_Exception {
        rm.Rmcar port = service.getRmcarPort();
        return port.addflight(id, num, seats, price);
    }

    private boolean commit1(int id, int rm) {
        rm.Rmcar port = service.getRmcarPort();
        return port.commit(id, rm);
    }

    private boolean abort1(int id, int rm) {
        rm.Rmcar port = service.getRmcarPort();
        return port.abort(id, rm);
    }

    
}