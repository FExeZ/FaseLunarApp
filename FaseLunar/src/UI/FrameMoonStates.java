package UI;

import Logic.MoonStage;
import Logic.Phase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FrameMoonStates extends JFrame {
    public static void main(String[] args) {
        JFrame ventana = new JFrame("Fases Lunares");
        ventana.setSize(300, 350);
        ventana.setLocationRelativeTo(null);
        ventana.setResizable(false);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Color celesteClaro = new Color(173, 222, 243); // Valores RGB para un celeste claro

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        JPanel lowPanel = new JPanel();

        topPanel.setBackground(Color.white);
        lowPanel.setBackground(Color.white);

        JPanel containerTop = new JPanel();
        JPanel containerLow = new JPanel();
        containerLow.setBackground(celesteClaro);
        containerTop.setBackground(celesteClaro);

        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        topPanel.setPreferredSize(new Dimension(280, 100));
        lowPanel.setPreferredSize(new Dimension(280, 200));

        containerTop.add(topPanel);
        containerLow.add(lowPanel);

        mainPanel.add(containerTop, BorderLayout.NORTH);
        mainPanel.add(containerLow, BorderLayout.SOUTH);

        // Crea un JLabel para contener el texto "Fecha" y configura la alineación al centro
        JLabel phaseLabelTop = new JLabel("Fecha");
        phaseLabelTop.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel phaseLabelLow = new JLabel("Fase");

        JPanel datePanel = new JPanel();
        datePanel.setBackground(Color.white);
        datePanel.add(new JLabel("Día:"));
        JTextField dayField = new JTextField(2);
        datePanel.add(dayField);

        datePanel.add(new JLabel("Mes:"));
        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        JComboBox<String> monthComboBox = new JComboBox<>(meses);
        datePanel.add(monthComboBox);

        datePanel.add(new JLabel("Año:"));
        JTextField yearField = new JTextField(4);
        datePanel.add(yearField);

        topPanel.add(phaseLabelTop);
        topPanel.add(datePanel);
        lowPanel.setLayout(new BorderLayout());
        lowPanel.add(phaseLabelLow, BorderLayout.NORTH);

        JButton calcularButton = new JButton("Calcular");
        calcularButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        calcularButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Obtener la fecha ingresada
                String day = dayField.getText();
                String month = (String) monthComboBox.getSelectedItem();
                String year = yearField.getText();

                // Crear una fecha a partir de los valores ingresados
                String dateString = day + " " + month + " " + year;
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy", new Locale("es", "ES"));
                Date date;
                try {
                    date = dateFormat.parse(dateString);
                } catch (ParseException ex) {
                    // Manejar la excepción si la fecha no se puede parsear
                    ex.printStackTrace();
                    return;
                }

                // Calcular la fase lunar
                MoonStage moonStage = new MoonStage();
                Phase faseLunar = moonStage.calcularFase(date);
                System.out.println("Nombre "+faseLunar.getName());
                System.out.println("Directorio "+faseLunar.getImagePath());

                // Obtener la ruta de la imagen correspondiente
                String imagePath = faseLunar.getImagePath();

                URL imageURL = getClass().getResource("/Logic/Images/"+imagePath);

                ImageIcon imageIcon = new ImageIcon(imageURL);
                JLabel imageLabel = new JLabel(imageIcon);

                JPanel imagePanel = new JPanel(new BorderLayout());
                imagePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Agrega un espacio alrededor de la imagen
                imagePanel.add(imageLabel, BorderLayout.CENTER);

                lowPanel.removeAll();
                lowPanel.add(phaseLabelLow);
                lowPanel.add(imagePanel);

                lowPanel.revalidate();
                lowPanel.repaint();
            }
        });

        topPanel.add(calcularButton);

        ventana.add(mainPanel);
        ventana.setVisible(true);
    }
}

