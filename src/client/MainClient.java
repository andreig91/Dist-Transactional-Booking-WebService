package client;

public class MainClient
{

    public static void main(String[] args) 
    {
       client aClient = new client();
       int id = aClient.start();
       //int custId = aClient.newcustomer(id);
       //aClient.newcustomerid(id, 10);
       aClient.addflight(id,1,1,1);
       aClient.abort(id);
    }
}
