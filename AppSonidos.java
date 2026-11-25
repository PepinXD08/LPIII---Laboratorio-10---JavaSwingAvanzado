package lab10;

import javax.swing.*;
import javax.sound.sampled.*;
import java.awt.*;
import java.io.File;

public class AppSonidos extends JFrame {
    public AppSonidos() {
        setTitle("Reproductor de Efectos de Sonido");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JLabel titulo = new JLabel("Selecciona un efecto de sonido", SwingConstants.CENTER);

        titulo.setFont(new Font("Arial", Font.BOLD, 16));

        JPanel panelBotones = new JPanel(new GridLayout(1, 3, 10, 10));

        JButton btnAplausos = new JButton("Aplausos");
        JButton btnCampana = new JButton("Campana");
        JButton btnExplosion = new JButton("Explosión");

        panelBotones.add(btnAplausos);
        panelBotones.add(btnCampana);
        panelBotones.add(btnExplosion);

        add(titulo, BorderLayout.NORTH);
        add(panelBotones, BorderLayout.CENTER);

        btnAplausos.addActionListener(e -> reproducirSonido("aplausos.wav"));
        btnCampana.addActionListener(e -> reproducirSonido("campana.wav"));
        btnExplosion.addActionListener(e -> reproducirSonido("explosion.wav"));
    }

    private void reproducirSonido(String archivo) {
        try {
            File sonido = new File("sonidos/" + archivo);

            AudioInputStream audio = AudioSystem.getAudioInputStream(sonido);

            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();

            clip.addLineListener(evento -> {
                if (evento.getType() == LineEvent.Type.STOP) {
                    clip.close();
                }
            });

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "No se pudo reproducir el sonido: " + archivo + "\nVerifique que el archivo WAV exista.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AppSonidos().setVisible(true);
        });
    }
}