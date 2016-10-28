package GameOfThrones;

public class Lannister extends Defensor {

    public Lannister(String nombre, char ID) {
        super(nombre, "Lannister", ID);
        int numLlavesGenerar = 45;
        Llave[] llavesGen = new Llave[numLlavesGenerar];
        int idLlave = 0;
        for (int i = 0; i < numLlavesGenerar; i++) {
            llavesGen[i] = new Llave(idLlave);
            if (idLlave % 2 == 1) {
                i++;
                llavesGen[i] = new Llave(idLlave);
            }
            idLlave++;
        }
        for(int i=0;i<numLlavesGenerar;i++){
            this.llaves.addData(llavesGen[i]);
        }
    }

}
