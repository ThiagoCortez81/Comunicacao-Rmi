import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Scanner;

public interface FileInterface extends Remote {

    public String leitor(String path, boolean order) throws IOException, RemoteException;

    public void escritor(String path, String textString) throws IOException, RemoteException;

    public String bubbleSort(int v[]) throws RemoteException;

    public String generateRandom(int maxNums) throws RemoteException;

}