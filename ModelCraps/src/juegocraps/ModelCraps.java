package juegocraps;

/**
 * ModelCraps apply craps rules.
 * estado = 1 Natural winner
 * estado = 2 Craps looser
 * estado = 3 Establish point
 * estado = 4 Point winner
 * estado = 5 Point looser
 * @author David Camilo Ordoñez marin 2226057
 * @version v.1.0.0 date 18/04/2022
 */

public class ModelCraps {
    private Dado dado1,dado2;
    private int tiro, punto, estado,flag,tiroTotal;
    private String[] estadoToString;
    private int[] caras;

    /**
     * Class Constructor
     */
    public ModelCraps(){
        dado1 = new Dado();
        dado2 = new Dado();
        caras = new int[2];
        estadoToString = new String[2];
        flag = 0;
    }

    /**
     * Establish the value according to each dice
     */
    public void calcularTiro(){
        caras[0]=dado1.getCara();
        caras[1]=dado2.getCara();
        tiroTotal = caras[0] + caras[1];
        tiro = caras[0] + caras[1];
    }

    /**
     * Establish game state according to state attribute value.
     * states 1 to 3
     */
    public void determinarJuego(){
        if(flag==0){
            if(tiro == 7 || tiro ==11){
                estado = 1;
            }else{
                if(tiro == 3 || tiro==2||tiro==12){
                    estado = 2;
                }else{
                    estado = 3;
                    punto=tiro;
                    flag = 1;
                }
            }
        }else{
            // ronda Punto
            rondaPunto();
        }
    }

    /**
     * Establish game state according to state attribute value.
     * state 4 and 5
     */
    private void rondaPunto() {
        if(tiro==punto){
            estado = 4;
            flag=0;
        }else{
            if(tiro==7){
                estado=5;
                flag=0;
            }else {
                estado = 6;
            }
        }
    }

    /**
     * Establish messages game state according to state attribute value
     * @return Messages for the View class
     */
    public int getTiro() {
        return tiro;
    }

    public int getPunto() {
        return punto;
    }

    public String[] getEstadoToString() {
        switch (estado){
            case 1: estadoToString[0] = "Tiro de salida = "+ tiro ;
                    estadoToString[1] = "Sacaste natural ganaste!!";
                break;
            case 2: estadoToString[0] = "Tiro de salida = "+ tiro ;
                    estadoToString[1] = "Perdiste sacaste craps :(";
                break;
            case 3: estadoToString[0] = "Tiro de salida = " + tiro + "\n Punto = "+punto;
                    estadoToString[1] = "Estableciste punto en "+punto+", ganaste!! puedes seguir lanzando!!\n"+
                    "pero si sacas 7 antes que "+punto+" Pierdes";
                break;
            case 4: estadoToString[0] = "Tiro de salida = " + punto+"\n Punto = "+punto+"\n Valor del nuevo tiro = "+tiro;
                    estadoToString[1] = "Volviste a sacar "+punto+" ganaste!!";
                break;
            case 5: estadoToString[0] = "Tiro de salida = " + punto+ "\n Punto = "+punto+"\n Valor del nuevo tiro = "+tiro;
                    estadoToString[1] = " Sacaste 7 antes que "+punto+" perdiste";
                break;
            case 6: estadoToString[0] = "Tiro de salida = " + punto+ "\n Punto = "+punto+"\n Valor del nuevo tiro = "+tiro;
                    estadoToString[1] = "Estas en punto, puedes seguir lanzando!!\n"+ "pero si sacas 7 antes que "+punto+" Pierdes";
                 break;
        }
        return estadoToString;
    }

    public int[] getCaras() {
        return caras;
    }
}