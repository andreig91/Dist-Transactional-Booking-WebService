package client;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MainClient
{

    public static void main(String[] args) 
    {
       client aClient = new client();
       int id = aClient.start();
       //int custId = aClient.newcustomer(id);
       //aClient.newcustomerid(id, 10);
       aClient.addflight(id,1,1,1);
       int id2 = aClient.start();
       aClient.addflight(id2, 1, 1, 1);
        try {
            Thread.sleep(10000);
            //aClient.abort(id);
        } catch (InterruptedException ex) {
            Logger.getLogger(MainClient.class.getName()).log(Level.SEVERE, null, ex);
        }
       aClient.commit(id);
       //aClient.commit(id2);
    }
}
