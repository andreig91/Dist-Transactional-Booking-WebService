package client;

import mwservices.DeadlockException_Exception;

public class client 
{
    public int start()
    {
        int id = start1();
        System.out.println("Transaction with id " + id + " was started");
        return id;
    }

    public void commit(int id)
    {
        if(commit1(id))
        {
            System.out.println("Transaction with id " +  id + " was committed");
        }
        else
        {
            System.out.println("Transaction with id " +  id + " could not be committed");
        }
    }

    public void abort(int id)
    {
        if(abort1(id))
        {
            System.out.println("Transaction with id " +  id + " was aborted");
        }
        else
        {
            System.out.println("Transaction with id " +  id + " could not be aborted");
        }
    }

    public int newcustomer(int id)
    {
        try 
        {
            int newId = newcustomer1(id);
            System.out.println("New customer created with id " +  newId);
            return newId;
        } 
        catch (DeadlockException_Exception ex) 
        {
            System.out.println("Transaction was aborted, customer was not created");
            return -1;
        }
    }

    public void newcustomerid(int id, int custid)
    {
        try 
        {
            if(newcustomerid1(id, custid))
            {
                System.out.println("New customer created with id " +  custid);
            }
            else
            {
                System.out.println("Customer created with id " +  custid + " already exists, choose a different id");
            }
        } 
        catch (DeadlockException_Exception ex) 
        {
            System.out.println("Transaction was aborted, customer was not created");
        }
    }
    
    public void addflight(int id, int num, int seats, int price)
    {
        if(addflight1(id, num, seats, price))
        {
            System.out.println("Flight was added");
        }
        else
        {
            System.out.println("Transaction was aborted, flight was not added");
        }
    }

    private static int start1() {
        mwservices.Mw_Service service = new mwservices.Mw_Service();
        mwservices.Mw port = service.getMwPort();
        return port.start();
    }

    private static boolean commit1(int id) {
        mwservices.Mw_Service service = new mwservices.Mw_Service();
        mwservices.Mw port = service.getMwPort();
        return port.commit(id);
    }

    private static boolean abort1(int id) {
        mwservices.Mw_Service service = new mwservices.Mw_Service();
        mwservices.Mw port = service.getMwPort();
        return port.abort(id);
    }

    private static int newcustomer1(int id) throws DeadlockException_Exception {
        mwservices.Mw_Service service = new mwservices.Mw_Service();
        mwservices.Mw port = service.getMwPort();
        return port.newcustomer(id);
    }

    private static boolean newcustomerid1(int id, int custid) throws DeadlockException_Exception {
        mwservices.Mw_Service service = new mwservices.Mw_Service();
        mwservices.Mw port = service.getMwPort();
        return port.newcustomerid(id, custid);
    }

    private static boolean addflight1(int id, int num, int seats, int price) {
        mwservices.Mw_Service service = new mwservices.Mw_Service();
        mwservices.Mw port = service.getMwPort();
        return port.addflight(id, num, seats, price);
    }

}