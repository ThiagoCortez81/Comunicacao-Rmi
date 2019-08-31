import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import java.rmi.RemoteException;
import java.rmi.server.*;
import java.io.File;

public class FileHandler extends UnicastRemoteObject implements FileInterface, Serializable {

    public FileHandler() throws RemoteException {
    }

    public String leitor(String path, boolean order) throws IOException, RemoteException {
        // Logger
        try {
            String folder = getClientHost().replace(".", "-");
            path = folder + "/" + path;
        } catch (Exception e) {
            path = "commom/" + path;
        }
        try {
            System.out.println("[" + getClientHost() + "] Fez leitura do arquivo " + path);
        } catch (Exception e) {
            System.out.println("Fez leitura do arquivo " + path);
        }

        BufferedReader buffRead = new BufferedReader(new FileReader(path));
        ArrayList<Integer> numeros = new ArrayList<>();
        int i = 0;
        String linha = null;
        String completeText = "";

        while (true) {
            linha = buffRead.readLine();

            if (linha != null && order)
                numeros.add(Integer.parseInt(linha));
            else if (!order && linha != null)
                completeText += linha + "\n";
            else
                break;
            i++;
        }
        buffRead.close();

        if (order) {
            int[] numerosArray = new int[numeros.size()];
            i = 0;

            for (int num : numeros) {
                numerosArray[i] = num;
                i++;
            }
            return this.bubbleSort(numerosArray);
        } else {
            System.out.println(completeText);
            return completeText;
        }
    }

    public void escritor(String path, String textString) throws IOException, RemoteException {
        // Logger
        try {
            String folder = getClientHost().replace(".", "-");
            File newFolder = new File(folder);
            newFolder.mkdir();

            path = folder + "/" + path;

            System.out.println(
                    "[" + getClientHost() + "] Fez escrita do arquivo " + path + " com os valores " + textString);
        } catch (Exception e) {
            System.out.println("Fez escrita do arquivo " + path + " com os valores " + textString);
        }

        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
        buffWrite.write("");
        buffWrite.append(textString);
        buffWrite.close();
    }

    public String bubbleSort(int v[]) {
        for (int i = v.length; i >= 1; i--) {
            for (int j = 1; j < i; j++) {
                if (v[j - 1] > v[j]) {
                    int aux = v[j];
                    v[j] = v[j - 1];
                    v[j - 1] = aux;
                }
            }
        }

        String strReturn = "";
        for (int i = 0; i <= v.length - 1; i++) {
            strReturn += v[i] + ", ";
        }

        // Logger
        try {
            System.out.println("[" + getClientHost() + "] Ordenou a lista em bubbleSort. Saída: " + strReturn);
        } catch (Exception e) {
            System.out.println("Ordenou a lista em bubbleSort. Saída: " + strReturn);
        }

        return strReturn;
    }

    public String generateRandom(int maxNums) throws RemoteException {
        // Logger
        try {
            System.out.println("[" + getClientHost() + "] Gerou numeros randômicos.");
        } catch (Exception e) {
            System.out.println("Gerou numeros randômicos.");
        }

        String strNums = "";
        Random gerador = new Random();

        for (int i = 0; i < maxNums; i++) {
            strNums += (gerador.nextInt(100)) + "\n";
        }

        return strNums;
    }

}