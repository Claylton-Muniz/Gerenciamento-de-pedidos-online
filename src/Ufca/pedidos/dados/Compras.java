package Ufca.pedidos.dados;

public class Compras {

    private String loja;
    private String produto;
    private float preco;
    private int tempDias;
    
    public Compras(String loja, String produto, float preco, int tempDias){
        this.loja = loja;
        this.produto = produto;
        this.preco = preco;
        this.tempDias = tempDias;

    }

    public void setLoja(String loja) {
        this.loja = loja;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public void setTempDias(int tempDias) {
        this.tempDias = tempDias;
    }

    @Override
    public String toString() {
        String r = 
        "Loja: " + this.loja +
        "\nProduto: " + this.produto +
        "\nTempo: " + this.tempDias + " dias\n";

        return r;

    }

}
