package Ufca.pedidos.repo;

import java.util.ArrayList;
import Ufca.pedidos.dados.Clientes;

public class RepoCliente {

    private ArrayList<Clientes> clientes = new ArrayList<Clientes>();

    public void addClientes(Clientes cliente) {
        clientes.add(cliente);
    }

    public void removeClientes(int id) {
        clientes.remove(id);
    }
    
    public Clientes getClientes(int id) {
        return clientes.get(id);
    }

    public int numClientes() {
        return this.clientes.size();

    }

}
