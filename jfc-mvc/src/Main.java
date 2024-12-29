import java.awt.*;
import java.util.List;
import javax.swing.*;
import org.apache.ibatis.session.SqlSession;
import model.MyBatisUtil;
import model.UserMapper;
import view.UserView;
import controller.UserController;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Aplikasi Swing dengan MyBatis");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);
            frame.setLayout(new BorderLayout());

            JLabel statusLabel = new JLabel("Tekan tombol untuk memulai proses", JLabel.CENTER);
            JButton startButton = new JButton("Mulai");
            JProgressBar progressBar = new JProgressBar(0, 100);
            progressBar.setValue(0);
            progressBar.setStringPainted(true);

            // Tambahkan komponen ke frame
            frame.add(statusLabel, BorderLayout.NORTH);
            frame.add(progressBar, BorderLayout.CENTER);
            frame.add(startButton, BorderLayout.SOUTH);

            // Aksi tombol "Mulai"
            startButton.addActionListener(e -> {
                startButton.setEnabled(false);
                statusLabel.setText("Proses sedang berjalan...");

                SwingWorker<Void, Integer> worker = new SwingWorker<Void, Integer>() {
                    @Override
                    protected Void doInBackground() throws Exception {
                        for (int i = 0; i <= 100; i++) {
                            if (isCancelled()) {
                                break;
                            }
                            publish(i);
                            Thread.sleep(50);
                        }
                        return null;
                    }

                    @Override
                    protected void process(List<Integer> chunks) {
                        int latestProgress = chunks.get(chunks.size() - 1);
                        progressBar.setValue(latestProgress);
                    }

                    @Override
                    protected void done() {
                        startButton.setEnabled(true);
                        statusLabel.setText("Proses selesai! Membuka tampilan UserView...");
                        bukaUserView();
                    }
                };
                worker.execute();
            });

            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    private static void bukaUserView() {
        SwingUtilities.invokeLater(() -> {
            try {
                SqlSession session = MyBatisUtil.getSqlSession();
                UserMapper mapper = session.getMapper(UserMapper.class);

                UserView view = new UserView();
                new UserController(view, mapper, session);

                view.setVisible(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog
                (null, "Terjadi kesalahan saat membuka UserView: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
