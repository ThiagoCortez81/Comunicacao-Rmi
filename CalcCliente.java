
import java.rmi.Naming;
import java.util.Scanner;

import javax.swing.*;

/**
 *
 * @author Germano
 */
public class CalcCliente {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CalcInterface calc = null;
        FileInterface sort = null;
        String serverIp = "rmi://127.0.0.1";

        try {
            calc = (CalcInterface) Naming.lookup(serverIp + "/Calc");
            sort = (FileInterface) Naming.lookup(serverIp + "/SortEmArquivo");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao server remoto. Erro: " + e.getMessage());
            System.out.println("Erro ao conectar ao server remoto.");
        }

        String option = "";
        while (true && option != null) {
            if (calc != null) {
                option = JOptionPane.showInputDialog(null,
                        "Entre com a operação desejada:\n[s] Sort\n[c]Criar anotação\n[e]Editar anotação\n[+] Adição\n[-] Subtração\n[x] Multiplicação\n[/] Divisão");
                int x, y;

                if (option != null) {
                    switch (option) {
                    case "s":
                        try {
                            String arqSort = "meu_arquivo_sort.txt";
                            sort.escritor(arqSort, sort.generateRandom(20));
                            JOptionPane.showMessageDialog(null, "A sequencia eh: " + sort.leitor(arqSort, false));
                            JOptionPane.showMessageDialog(null,
                                    "A sequencia ordenada eh: " + sort.leitor(arqSort, true));
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
                        }
                        break;
                    case "c":
                        String strContentFinal = "";

                        JTextArea taFinal = new JTextArea(20, 20);
                        switch (JOptionPane.showConfirmDialog(null, new JScrollPane(taFinal), "Entre com um texto",
                                JOptionPane.OK_CANCEL_OPTION)) {
                        case JOptionPane.OK_OPTION:
                            String texto = taFinal.getText();
                            try {
                                String nomeArqFinal = JOptionPane.showInputDialog(null, "Entre com o nome do arquivo: ");
                                sort.escritor(nomeArqFinal, texto);
                                JOptionPane.showMessageDialog(null, "Conteudo salvo");
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null, "Erro ao salvar");
                            }
                            break;
                        }
                        break;
                    case "e":
                        String nomeArq = JOptionPane.showInputDialog(null, "Entre com o nome do arquivo: ");
                        String strContent = "";
                        try {
                            strContent = sort.leitor(nomeArq, false);
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Arquivo não existe. ");
                            break;
                        }

                        JTextArea ta = new JTextArea(20, 20);
                        ta.setText(strContent);

                        switch (JOptionPane.showConfirmDialog(null, new JScrollPane(ta), "Entre com um texto",
                                JOptionPane.OK_CANCEL_OPTION)) {
                        case JOptionPane.OK_OPTION:
                            String texto = ta.getText();
                            try {
                                sort.escritor(nomeArq, texto);
                                JOptionPane.showMessageDialog(null, "Conteudo salvo");
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null, "Erro ao salvar");
                            }
                            break;
                        }
                        break;
                    case "+":
                        try {
                            x = Integer.parseInt(JOptionPane.showInputDialog(null, "Entre com o primeiro valor:"));
                            y = Integer.parseInt(JOptionPane.showInputDialog(null, "Entre com o segundo valor:"));

                            try {
                                JOptionPane.showMessageDialog(null, "Resultado eh: " + calc.add(x, y));
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null, "Problema ao calcular.");
                            }
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Insira um número!");
                        }

                        break;
                    case "-":
                        try {
                            x = Integer.parseInt(JOptionPane.showInputDialog(null, "Entre com o primeiro valor:"));
                            y = Integer.parseInt(JOptionPane.showInputDialog(null, "Entre com o segundo valor:"));

                            try {
                                JOptionPane.showMessageDialog(null, "Resultado eh: " + calc.sub(x, y));
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null, "Problema ao calcular.");
                            }
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Insira um número!");
                        }

                        break;
                    case "x":
                        try {
                            x = Integer.parseInt(JOptionPane.showInputDialog(null, "Entre com o primeiro valor:"));
                            y = Integer.parseInt(JOptionPane.showInputDialog(null, "Entre com o segundo valor:"));

                            try {
                                JOptionPane.showMessageDialog(null, "Resultado eh: " + calc.mul(x, y));
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null, "Problema ao calcular.");
                            }
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Insira um número!");
                        }

                        break;
                    case "/":
                        try {
                            x = Integer.parseInt(JOptionPane.showInputDialog(null, "Entre com o primeiro valor:"));
                            y = Integer.parseInt(JOptionPane.showInputDialog(null, "Entre com o segundo valor:"));

                            try {
                                JOptionPane.showMessageDialog(null, "Resultado eh: " + calc.div(x, y));
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null, "Problema ao calcular.");
                            }
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Insira um número!");
                        }

                        break;
                    }
                }
            }
        }

        sc.close();
    }
}
