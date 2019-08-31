import java.net.*;
import java.rmi.registry.Registry;

/**
 *
 * @author Germano / Thiago
 */
public class CalcServidor {
    public static void main (String args[]){
        try {
            InetAddress IP = InetAddress.getLocalHost();
            System.setProperty("java.rmi.server.hostname", IP.getHostAddress());

            Registry r = java.rmi.registry.LocateRegistry.createRegistry(1099);
            r.rebind("Calc", new CalcRMI());
            r.rebind("SortEmArquivo", new FileHandler());
            System.out.println("Servidor online em " + IP.getHostAddress() + ":1099");         
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
