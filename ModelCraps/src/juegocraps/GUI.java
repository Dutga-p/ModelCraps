package juegocraps;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is used as view craps class
 * @autor Carlos Felipe Montoya carlos.felipe.montoya@correounivalle.edu.co
 * @version v.1.0.0 date:21/03/2023
 */
public class GUI extends JFrame {
    public static final String MENSAJE_INICIO ="Bienvenido a Craps"
            + "\nOprime el boton Lanzar para iniciar el juego "
            + "\nSi tu tiro de salida es 7 u 11 ganas con Natural"
            + "\nSi tu tiro de salida es 2, 3 u 12 pierdes con Craps"
            + "\nSi sacas cualquier otro valor establecerás el Punto"
            + "\nEstado en punto podras seguir lanzando los dados"
            + "\npero ahora ganaraés si sacas nuevamente el valor del Punto"
            + "\nsin que previamente hayas sacado 7";
    ;

    private Header headerProject;
    private JLabel dado1, dado2;
    private JPanel panelDados,panelResultados;
    private ImageIcon imageDado;
    private JButton lanzar;
    private JTextArea mensajesSalida,resultadosDados;
    private Escucha escucha;
    private JSeparator separator;
    private ModelCraps modelCraps;


    /**
     * Constructor of GUI class
     */
    public GUI(){
        initGUI();

        //Default JFrame configuration
        this.setTitle("Game Craps");
        this.setSize(200,100);
        this.pack();
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * This method is used to set up the default JComponent Configuration,
     * create Listener and control Objects used for the GUI class
     */
    private void initGUI() {
        //Set up JFrame Container's Layout
        //Create Listener Object or Control Object

        escucha = new Escucha();
        modelCraps = new ModelCraps();
        //Set up JComponents
        headerProject = new Header("Game Craps Table", Color.BLACK);
        this.add(headerProject,BorderLayout.NORTH);

        imageDado = new ImageIcon(getClass().getResource("/resources/Dice.jpg"));
        dado1 = new JLabel(imageDado);
        dado2 = new JLabel(imageDado);

        lanzar = new JButton("lanzar");
        lanzar.addActionListener(escucha);

        panelDados = new JPanel();
        panelDados.setPreferredSize(new Dimension(500,280));
        panelDados.setBorder(BorderFactory.createTitledBorder("Tus dados"));
        panelDados.add(dado1);
        panelDados.add(dado2);
        panelDados.add(lanzar);
        this.add(panelDados,BorderLayout.CENTER);

        mensajesSalida = new JTextArea(7,31);
        mensajesSalida.setText(MENSAJE_INICIO);
        mensajesSalida.setEditable(false);
        //mensajesSalida.setBorder(BorderFactory.createTitledBorder("Que debes hacer "));
        JScrollPane scrollPane = new JScrollPane(mensajesSalida);
        panelResultados = new JPanel();
        panelResultados.setBorder(BorderFactory.createTitledBorder("Que debes hacer "));
        panelResultados.add(scrollPane);
        panelResultados.setPreferredSize(new Dimension(370,180));

        this.add(panelResultados,BorderLayout.EAST);

        resultadosDados = new JTextArea(10,31);
        resultadosDados.setEditable(false);

        separator = new JSeparator();
        separator.setPreferredSize(new Dimension(240,10));
        separator.setBackground(Color.BLUE);
    }

    /**
     * Main process of the Java program
     * @param args Object used in order to send input data from command line when
     *             the program is executed by console.
     */
    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            GUI miProjectGUI = new GUI();
        });
    }

    /**
     * inner class that extends an Adapter Class or implements Listeners used by GUI class
     */
    private class Escucha implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            modelCraps.calcularTiro();
            int [] caras = modelCraps.getCaras();
            imageDado = new ImageIcon(getClass().getResource("/resources/"+caras[0]+".png"));
            dado1.setIcon(imageDado);
            imageDado = new ImageIcon(getClass().getResource("/resources/"+caras[1]+".png"));
            dado2.setIcon(imageDado);

            modelCraps.determinarJuego();

            panelResultados.removeAll();
            panelResultados.setBorder(BorderFactory.createTitledBorder("Resultados "));
            panelResultados.add(resultadosDados);
            panelResultados.add(separator);
            panelResultados.add(mensajesSalida);
            resultadosDados.setText(modelCraps.getEstadoToString()[0]);
            mensajesSalida.setRows(4);
            mensajesSalida.setText(modelCraps.getEstadoToString()[1]);
            revalidate();
            repaint();
            //resultados.setText(modelCraps.getEstadoToString());
        }
    }
}
