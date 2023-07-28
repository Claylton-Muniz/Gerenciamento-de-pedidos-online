package Ufca.pedidos.teste;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import Ufca.pedidos.dados.Clientes;
import Ufca.pedidos.dados.Compras;
import Ufca.pedidos.repo.RepoCliente;

public class Menu implements InterMenu {

    public boolean aberto;
    public boolean logado;

    public Scanner scan = new Scanner(System.in);
    public Scanner text = new Scanner(System.in);

    RepoCliente c = new RepoCliente();

    public static String repet(int quant, String carac) {
        String r = "";
        for (int i = 0; i < quant; i++)
            r += carac;

        return r;

    }

    public Menu() {
        this.aberto = false;
    }

    public void lerOpcao() {
            
        int op = 0;
        
        try {
            op = scan.nextInt();
        } catch (InputMismatchException e) {
            System.out.print("Erro: Entrada inválida. \nDigite um número inteiro válido: ");
            // Consumir a entrada inválida
            scan.next(); 

            // Solicitar uma nova entrada válida
            while (!scan.hasNextInt()) {
                System.out.print("Erro: Entrada inválida. \nDigite um número inteiro válido: ");
                scan.next(); // Consumir a entrada inválida
            }
            op = scan.nextInt();
        } finally {
            escolha(op);
        }

    }

    public void edit() {
        limpar();
        System.out.printf(
                "+%s+\n" +
                        "| [1] Adcionar compra |\n" +
                        "| [2]  Editar compra  |\n" +
                        "| [3]  Excluir compra |\n" +
                        "| [4] Editar usuário  |\n" +
                        "| [5] Excluir usuário |\n" +
                        "| [6]      Sair       |\n" +
                        "+%s+\n",
                repet(21, "-"), repet(21, "-"));
        System.out.print("Escolha uma opção: ");

    }

    public void user(int opc, int i) {

        switch (opc) {

            case 1:
                float preco = 0;
                int dias = 0;

                System.out.print("Loja: ");
                String loja = text.nextLine();
                System.out.print("Produto: ");
                String prod =  text.nextLine();

                try {
                    System.out.print("Preço: R$");
                    preco = scan.nextFloat();
                } catch (InputMismatchException e) {
                    System.out.print("Erro: Entrada inválida. \nDigite um preço válido: R$");
                    // Consumir a entrada inválida
                    scan.next(); 

                    // Solicitar uma nova entrada válida
                    while (!scan.hasNextFloat()) {
                        System.out.print("Erro: Entrada inválida. \nDigite um um preço válido: R$");
                        scan.next(); // Consumir a entrada inválida
                    }
                    preco = scan.nextFloat();
                }

                try {
                    System.out.print("Tempo(dias): ");
                    dias = scan.nextInt();
                } catch (InputMismatchException e) {
                    System.out.print("Erro: Entrada inválida. \nDigite um tempo válido: ");
                    // Consumir a entrada inválida
                    scan.next(); 

                    // Solicitar uma nova entrada válida
                    while (!scan.hasNextFloat()) {
                        System.out.print("Erro: Entrada inválida. \nDigite um tempo válido: ");
                        scan.next(); // Consumir a entrada inválida
                    }
                    preco = scan.nextFloat();
                }

                Compras compra = new Compras(loja, prod, preco, dias);
                c.getClientes(i).setCompras(compra);
                
                break;

            case 2:
                int n = 0;
                System.out.println(n + " - cancelar");
                for (Compras comp : c.getClientes(i).getCompras()) {
                    n++;
                    System.out.print(n + " - " + comp.getProduto() + " - R$" + 
                    String.format("%.2f", comp.getPreco()) + "\n");
                }
                System.out.print("Editar protudo: ");
                int p = scan.nextInt();

                if (p != 0) {
                    System.out.print("Loja: ");
                    c.getClientes(i).getComprasEsp(p - 1).setLoja(text.nextLine());
                    System.out.print("Produto: ");
                    c.getClientes(i).getComprasEsp(p - 1).setProduto(text.nextLine());
                    System.out.print("Preço: R$");
                    c.getClientes(i).getComprasEsp(p - 1).setPreco(scan.nextFloat());
                    System.out.print("Tempo(dias): ");
                    c.getClientes(i).getComprasEsp(p - 1).setTempDias(scan.nextInt());
                }
                
                break;
            
            case 3:
                int num = 0;
                System.out.println(num + " - cancelar");
                for (Compras comp : c.getClientes(i).getCompras()) {
                    num++;
                    System.out.println(num + " - " + comp.getProduto() + " - R$" + 
                    String.format("%.2f", comp.getPreco()));
                }
                System.out.print("Editar protudo: ");
                int pos = scan.nextInt();

                if (pos != 0) {
                    c.getClientes(i).removeCompras(pos - 1);
                }
                
                break;
            
            case 4:
                System.out.print("Nome: ");
                c.getClientes(i).setNome(text.nextLine());
                System.out.print("Senha: ");
                c.getClientes(i).setSenha(text.nextLine());

                break;

            case 5: 
                c.removeClientes(i);
                getLogado(false);
                break;

            case 6:
                getLogado(false);
                break;

            default:
                break;
        }

    }

    public void escolha(int op) {

        Boolean login = false;

        switch (op) {
            case 1:

                System.out.print("Nome: ");
                String nome = text.nextLine();
                System.out.print("Email: ");
                String email = text.nextLine();
                System.out.print("Senha: ");
                String senha =  text.nextLine();

                Clientes cliente = new Clientes(nome, email, senha);
                c.addClientes(cliente);

                System.out.printf("%s\nCriando user...\n%s\n", repet(24, "="), repet(24, "="));
                break;

            case 2:

                System.out.print("Email: ");
                String user = text.nextLine();
                System.out.print("Senha: ");
                String pass = text.nextLine();

                for (int i = 0; i < c.numClientes(); i++) {
                    if(user.equals(c.getClientes(i).getEmail()) &&
                    pass.equals(c.getClientes(i).getSenha())) {

                        getLogado(true);

                        System.out.println("Welcome! " + c.getClientes(i).getNome());
                        pausar();

                        while (logado()) {
                            edit();

                            int opc = 0;

                            try {
                                opc = scan.nextInt();
                            } catch (InputMismatchException e) {
                                System.out.print("Erro: Entrada inválida. \nDigite um número inteiro válido: ");
                                // Consumir a entrada inválida
                                scan.next(); 

                                // Solicitar uma nova entrada válida
                                while (!scan.hasNextInt()) {
                                    System.out.print("Erro: Entrada inválida. \nDigite um número inteiro válido: ");
                                    scan.next(); // Consumir a entrada inválida
                                }
                                opc = scan.nextInt();
                            } finally {
                                user(opc, i);
                            }

                        }

                        login = true;

                    }
                }

                if (!login) {
                    System.out.println("Usuário ou senha incorreto!");
                }

                System.out.printf("%s\nDelogando user...\n%s\n", repet(24, "="), repet(24, "="));
                break;

            case 3:
                fechar();
                break;

            default:
                break;
        }

    }

    public void getAberto(Boolean op) {
        this.aberto = op;

    }

    public Boolean setAberto() {
        return this.aberto;

    }

    public void getLogado(boolean val) {
        this.logado = val;
    }

    @Override
    public void aberto() {
        System.out.printf(
                "+%s+\n" +
                        "| [1] Criar um usuário |\n" +
                        "| [2] Logar no usuário |\n" +
                        "| [3]      Sair        |\n" +
                        "+%s+\n",
                repet(22, "-"), repet(22, "-"));
        System.out.print("Escolha uma opção: ");

    }

    @Override
    public void abrir() {
        getAberto(true);

    }

    @Override
    public void pausar() {
        try {
            new ProcessBuilder("cmd", "/c", "pause").inheritIO().start().waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void limpar() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void fechar() {
        getAberto(false);

    }

    @Override
    public Boolean logado() {
        return this.logado;

    }

}
