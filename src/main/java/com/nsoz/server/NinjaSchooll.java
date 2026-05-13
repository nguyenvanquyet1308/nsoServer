//package com.nsoz.server;
//
//import javax.swing.*;
//import java.awt.*;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.URI;
//import java.net.URISyntaxException;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//public class NinjaSchooll extends JFrame {
//
//    public NinjaSchooll() {
//        try {
//            setTitle("Thông Báo");
//            InputStream is = getClass().getClassLoader().getResourceAsStream("icon.png");
//            if (is != null) {
//                byte[] data = new byte[is.available()];
//                is.read(data);
//                ImageIcon img = new ImageIcon(data);
//                setIconImage(img.getImage());
//            }
//
//            setSize(600, 600);
//            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            setLocationRelativeTo(null);
//
//            setLayout(new BorderLayout());
//
//            JPanel buttonPanel = new JPanel();
//            buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
//
//            JButton btnLienHe = new JButton("Liên Hệ Hỗ Trợ");
//            btnLienHe.addActionListener(e -> openWebpage("https://upnhanh.us/ho-tro/"));
//
//            JButton btnDonate = new JButton("Donate");
//            btnDonate.addActionListener(e -> openWebpage("https://upnhanh.us/donate"));
//
//            buttonPanel.add(btnLienHe);
//            buttonPanel.add(btnDonate);
//            add(buttonPanel, BorderLayout.NORTH);
//
//            ArrayList<String> lines = new ArrayList<>();
//            try {
//                URL url = new URL("http://upnhanh.us/thongbao/src.txt");
//                BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
//                String line;
//                while ((line = br.readLine()) != null) {
//                    if (!line.trim().isEmpty()) {
//                        lines.add(line.trim());
//                    }
//                }
//                br.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//                lines.add("Chưa có thông báo!");
//            }
//
//            StringBuilder html = new StringBuilder("<html><body style='font-family:Tahoma; font-weight:bold; font-size:20px; line-height:1.5;'>");
//
//            for (int i = 0; i < lines.size(); i++) {
//                String line = lines.get(i);
//                html.append("<p style='margin-bottom:10px;'>")
//                        .append(line)
//                        .append("</p>");
//            }
//            html.append("</body></html>");
//
//            JTextPane textPane = new JTextPane();
//            textPane.setContentType("text/html");
//            textPane.setText(html.toString());
//            textPane.setEditable(false);
//            textPane.setCaretPosition(0);
//
//            JScrollPane scrollPane = new JScrollPane(textPane);
//            add(scrollPane, BorderLayout.CENTER);
//
//            setVisible(true);
//
//        } catch (IOException ex) {
//            Logger.getLogger(NinjaSchooll.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    private void openWebpage(String uri) {
//        try {
//            Desktop.getDesktop().browse(new URI(uri));
//        } catch (IOException | URISyntaxException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(NinjaSchooll::new);
//    }
//}
