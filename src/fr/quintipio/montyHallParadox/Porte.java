package fr.quintipio.montyHallParadox;


public class Porte {

    private ObjetCacheEnum objet;

    private int numeroPorte;

    public Porte(int numero) {
        this.numeroPorte = numero;
    }

    public ObjetCacheEnum ouvrirPorte() {
        return objet;
    }


    public ObjetCacheEnum getObjet() {
        return objet;
    }

    public void setObjet(ObjetCacheEnum objet) {
        this.objet = objet;
    }

    public int getNumeroPorte() {
        return numeroPorte;
    }

    public void setNumeroPorte(int numeroPorte) {
        this.numeroPorte = numeroPorte;
    }
}
