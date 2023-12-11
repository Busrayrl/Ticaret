package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class DashBoard extends JFrame {
	//kullanıcı giriş yaptıktan sonraki işlemler burada gerçekleşecek.

	private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public DashBoard(String title) {
        super(title);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DashBoard frame = new DashBoard();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public DashBoard() {
        // Ekranın ortasında büyük bir şekilde açılması için
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Ekranı kapattığınızda programın sona ermemesi için
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
    }

}