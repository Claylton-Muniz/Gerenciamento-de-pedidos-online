package Ufca.pedidos.dados;

import java.util.ArrayList;

public class Clientes extends Users {

    private ArrayList<Compras> compras = new ArrayList<Compras>();

    public Clientes(String nome, String email, String senha) {
        super(nome, email, senha);
    }

    public Compras getComprasEsp(int id) {
        return compras.get(id);
    }

    public ArrayList<Compras> getCompras() {
        return compras;
    }

    public void setCompras(Compras compra) {
        compras.add(compra);
    }

    public void removeCompras(int id) {
        compras.remove(id);
    }

    public int numCompras() {
        return compras.size();
    }
    
}
