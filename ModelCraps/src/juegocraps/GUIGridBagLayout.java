package juegocraps;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;

public class GUIGridBagLayout extends JFrame implements MouseListener, MouseMotionListener {
    public static final String MENSAJE_INICIO ="Bienvenido a Craps"
            + "\nOprime el boton Lanzar para iniciar el juego "
            + "\nSi tu tiro de salida es 7 u 11 ganas con Natural"
            + "\nSi tu tiro de salida es 2, 3 u 12 pierdes con Craps"
            + "\nSi sacas cualquier otro valor establecerás el Punto"
            + "\nEstado en punto podras seguir lanzando los dados"
            + "\npero ahora ganaraés si sacas nuevamente el valor del Punto"
            + "\nsin que previamente hayas sacado 7";

    private Header headerProject;
    private JLabel dado1, dado2;
    private JPanel panelDados;
    private ImageIcon imageDado;
    private JButton lanzar,ayuda,salir;
    private JTextArea resultadosSalida,resultadosDados;
    private Escucha escucha;
    private ModelCraps modelCraps;
    private int posX;
    private int posY;

    public GUIGridBagLayout(){
        initGUI();

        //Default JFrame configuration
        this.setTitle("Game Craps");
        this.setUndecorated(true);
        this.setBackground(new Color(255,255,255,0));
        this.pack();
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        ImageIcon imagenFondo = new ImageIcon(getClass().getResource("/resources/Fondo.jpg"));
        JLabel etiquetaFondo = new JLabel(imagenFondo);
        etiquetaFondo.setBounds(0, 0, imagenFondo.getIconWidth(), imagenFondo.getIconHeight());
        Container contenedor = getContentPane();
        contenedor.add(etiquetaFondo);
        contenedor.setLayout(null);

        setSize(670, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        //this.setIconImage("/resources/Fondo.jpg");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addMouseListener(this);
        addMouseMotionListener(this);

    }

    /**
     * This method is used to set up the default JComponent Configuration,
     * create Listener and control Objects used for the GUI class
     */
    private void initGUI() {
        //Set up JFrame Container's Layout

        this.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();


        //Create Listener Object or Control Object

        escucha = new Escucha();
        modelCraps = new ModelCraps();
        //Set up JComponents
        headerProject = new Header("Game Craps Table", Color.BLACK);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        this.add(headerProject,constraints);

        ayuda = new JButton("?");
        ayuda.addActionListener(escucha);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.LINE_START;
        this.add(ayuda,constraints);
        salir = new JButton("Salir");
        salir.addActionListener(escucha);
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.LINE_END;
        this.add(salir,constraints);

        imageDado = new ImageIcon(getClass().getResource("/resources/Dice.jpg"));
        dado1 = new JLabel(imageDado);
        dado2 = new JLabel(imageDado);


        panelDados = new JPanel();
        panelDados.setPreferredSize(new Dimension(500,280));
        panelDados.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE, 5),
                "Tus dados", TitledBorder.CENTER, TitledBorder.TOP, new Font("Arial", Font.BOLD, 25), Color.white));
        panelDados.setBackground(new Color(255,255,255,0));
        panelDados.add(dado1);
        panelDados.add(dado2);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        this.add(panelDados,constraints);

        resultadosDados = new JTextArea(10,15);
        resultadosDados.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.white, 3),
                "Respuestas", TitledBorder.CENTER, TitledBorder.TOP, new Font("Arial", Font.BOLD, 18), Color.white));
        resultadosDados.setForeground(Color.white);
        resultadosDados.setText("Debes lanzar los dados");
        resultadosDados.setBackground(new Color(255,255,255,0));
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.CENTER;
        resultadosDados.setEditable(false);

        this.add(resultadosDados,constraints);

        lanzar = new JButton("lanzar");
        lanzar.addActionListener(escucha);
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.CENTER;
        this.add(lanzar,constraints);

        resultadosSalida = new JTextArea(2,30);
        resultadosSalida.setText("Usa el botón (?) para ver la reglas del juego ");
        resultadosSalida.setEditable(false);
        resultadosSalida.setForeground(Color.white);
        resultadosSalida.setBackground(new Color(255,255,255,0));
        resultadosSalida.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.white, 3),
                "Mensajes", TitledBorder.CENTER, TitledBorder.TOP, new Font("Arial", Font.BOLD, 18), Color.white));
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.CENTER;
        this.add(resultadosSalida,constraints);

    }
    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            GUIGridBagLayout miProjectGUI = new GUIGridBagLayout();
        });
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        posX = e.getX();
        posY = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        posX = 0;
        posY = 0;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int x = e.getXOnScreen() - posX;
        int y = e.getYOnScreen() - posY;
        setLocation(x, y);
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    private class Escucha implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==lanzar){
                modelCraps.calcularTiro();
                int [] caras = modelCraps.getCaras();
                imageDado = new ImageIcon(getClass().getResource("/resources/"+caras[0]+".png"));
                dado1.setIcon(imageDado);
                imageDado = new ImageIcon(getClass().getResource("/resources/"+caras[1]+".png"));
                dado2.setIcon(imageDado);

                modelCraps.determinarJuego();
                resultadosDados.setText(modelCraps.getEstadoToString()[0]);
                resultadosSalida.setRows(4);
                resultadosSalida.setText(modelCraps.getEstadoToString()[1]);
                revalidate();
                repaint();
                //resultados.setText(modelCraps.getEstadoToString());
            }else{
                if(e.getSource()==ayuda){
                    JOptionPane.showMessageDialog(null,MENSAJE_INICIO);
                }else{
                    System.exit(0);
                }
            }
        }
    }
}
