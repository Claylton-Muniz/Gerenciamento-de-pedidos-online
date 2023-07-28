package Ufca.pedidos.teste;

public class Main {

    public static void main(String[] args) {

        Menu menu = new Menu();

        menu.abrir();
        do {
            menu.aberto();
            menu.lerOpcao();
            menu.pausar();
            menu.limpar();
        } while (menu.setAberto());

        System.out.println("Tchau!!");

    }

}
