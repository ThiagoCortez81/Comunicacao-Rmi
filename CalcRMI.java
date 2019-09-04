
import java.rmi.RemoteException;
import java.rmi.server.*;

/**
 *
 * @author Germano / Thiago
 */
public class CalcRMI extends UnicastRemoteObject implements CalcInterface {

    public CalcRMI() throws RemoteException {
        int a, b;
    }

    @Override
    public int add(int x, int y) throws RemoteException {
        try {
            System.out.println("[" + getClientHost() + "] realizou soma de " + x + " + " + y);
        } catch (Exception e) {
            System.out.println("Realizou soma de " + x + " + " + y);
        }
        return x + y;
    }

    @Override
    public int sub(int x, int y) {
        try {
            System.out.println("[" + getClientHost() + "] realizou subtração de " + x + " - " + y);
        } catch (Exception e) {
            System.out.println("Realizou subtração de " + x + " - " + y);
        }
        return x - y;
    }

    @Override
    public int mul(int x, int y) {
        try {
            System.out.println("[" + getClientHost() + "] realizou multiplicação de " + x + " x " + y);
        } catch (Exception e) {
            System.out.println("Realizou multiplicação de " + x + " x " + y);
        }
        return x * y;
    }

    @Override
    public int div(int x, int y) {
        try {
            System.out.println("[" + getClientHost() + "] realizou divisão de " + x + " / " + y);
        } catch (Exception e) {
            System.out.println("Realizou divisão de " + x + " / " + y);
        }
        return x / y;
    }
}
