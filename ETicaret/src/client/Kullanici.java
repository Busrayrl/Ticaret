package client;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Listener.kullaniciListener;

import Service.UyeolService;

import Service.GirisyapServices;
import dto.UyeolRequest;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;

public class Kullanici extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Kullanici frame = new Kullanici();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Kullanici() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 0, 160));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(167, 167, 167));
        panel.setBounds(10, 11, 414, 110);
        contentPane.add(panel);
        panel.setLayout(null);

        JButton btn_uye = new JButton("ÜYE OL");
        btn_uye.setFont(new Font("Times New Roman", Font.BOLD, 14));
        btn_uye.setForeground(new Color(74, 74, 74));
        btn_uye.setBackground(new Color(211, 211, 211));
        btn_uye.addActionListener(new UyeolListener().listen()); 
        btn_uye.setBounds(119, 31, 161, 50);
        panel.add(btn_uye);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(167, 167, 167));
        panel_1.setBounds(10, 132, 414, 118);
        contentPane.add(panel_1);
        panel_1.setLayout(null);

        JButton btn_giris = new JButton("GİRİŞ YAP");
        btn_giris.setFont(new Font("Times New Roman", Font.BOLD, 14));
        btn_giris.setForeground(new Color(74, 74, 74));
        btn_giris.setBackground(new Color(211, 211, 211));
        btn_giris.addActionListener(new GirisListener().listen()); 
        btn_giris.setBounds(119, 31, 161, 50);
        panel_1.add(btn_giris);
    }

    private static class UyeolListener {

        private final UyeolService uyeolService = new UyeolService();

        public ActionListener listen() {
            return e -> {

                String name = JOptionPane.showInputDialog("Adınızı giriniz");
                String surname = JOptionPane.showInputDialog("Soyadınızı giriniz");
                String email = JOptionPane.showInputDialog("Emailinizi giriniz");
                String password = JOptionPane.showInputDialog("Şifrenizi giriniz");
                if (email.isEmpty() || password.isEmpty() || name.isEmpty() || surname.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Lütfen tüm alanları doldurunuz");

                } else {
                    try {
                        uyeolService.uyeol(new UyeolRequest.Builder()
                                .email(email)
                                .name(name)
                                .surname(surname)
                                .password(password)
                                .build());
                        new DashBoard("ETicaret Sayfasına Hoşgeldiniz");

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "HATA OLUŞTU");
                    }
                    JOptionPane.showMessageDialog(null, "Kayıt başarılı");
                    JFrame frame = new DashBoard("ETicaret Sayfasına Hoşgeldiniz");

                    frame.setVisible(true);
                }
            };

        }
    }
    

    private static class GirisListener {

        private final GirisyapServices girisyapService = new GirisyapServices();

        public ActionListener listen() {
            return e -> {
            	String email = JOptionPane.showInputDialog("Emailinizi giriniz");
                String password = JOptionPane.showInputDialog("Şifrenizi giriniz");
                try {
					girisyapService.girisyap(email, password);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                JFrame frame = new DashBoard("ETicaret Sayfasına Hoşgeldiniz");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(1500, 1500);
                frame.setVisible(true);;
                
            };

        } 
    }
    }
    
    
    
    
    
    
