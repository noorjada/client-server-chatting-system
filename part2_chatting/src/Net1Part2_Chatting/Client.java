/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Net1Part2_Chatting;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static java.awt.SystemColor.text;
import static java.util.Collections.list;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 *
 * @author USER
 */
public class Client extends javax.swing.JFrame {

    static ArrayList<String[]> list = new ArrayList<>();


    /**
     * Creates new form Client
     */
    DatagramSocket socket;
    String userName;
    String localIp;
    int localPort;
    String remotIp;
    int remotPort;
    InetAddress remot_IPAddress;
    byte[] S_buffer;
    DatagramPacket sendpacket;
    byte[] R_buffer;
    DatagramPacket receive_packet;
    boolean conn = false;
    boolean logedin = false;
    String currentStatus = "Active"; // Initial status

    public Client() throws FileNotFoundException {
        readFromFile();
        initComponents();
        Remot_IP1.setVisible(false);
        Remot_Port1.setVisible(false);
        textPaneArea.setEditable(false);
        Remot_IP.setEditable(false);
        Remot_Port.setEditable(false);
        inArea.setForeground(Color.GRAY);
        inArea.setText("enter text here");
        userName = "";
        localIp = "";
        localPort = 0;
        remotIp = "";
        remotPort = 0;
        R_buffer = new byte[50];
        receive_packet = new DatagramPacket(R_buffer, R_buffer.length);

        // Add key and mouse listeners to update the status
        inArea.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                updateStatus("Active");
            }

            @Override
            public void keyPressed(KeyEvent e) {
                updateStatus("Active");
            }

            @Override
            public void keyReleased(KeyEvent e) {
                updateStatus("Active");
            }
        });

        inArea.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                updateStatus("Active");
            }

            @Override
            public void mousePressed(MouseEvent e) {
                updateStatus("Active");
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                updateStatus("Active");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                updateStatus("Active");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                updateStatus("Away");
            }
        });
    }


    private void updateStatus(String newStatus) {
        currentStatus = newStatus;
        try {
            dataToServer.writeUTF("set_status:" + newStatus);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    private void performBeforeClosingAction() {
        String tokenz = "";
        for (String[] element : list) {
            tokenz += element[0] + " " + element[1] + "\n";
        }

        try (FileOutputStream outToFile = new FileOutputStream("loginrecords.txt");) {
            outToFile.write((tokenz).getBytes());
            outToFile.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ServerFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ServerFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public static void readFromFile() throws FileNotFoundException {
        if (list.isEmpty()) {
            File file = new File("loginrecords.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String[] tokens = scanner.nextLine().split(" ");
                list.add(tokens);
            }
            scanner.close();
        }
    }


    public String getLastLogin(String username) {

        LocalDateTime currentDateTime = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd|HH:mm:ss");

        String currentDate = currentDateTime.format(formatter);
        for (String[] element : list) {
            if (element[0].equalsIgnoreCase(username)) {
                String date = new String(element[1]);
                element[1] = currentDate;
                return date;
            }
        }
        return null;


    }


    void Export(String chat) {

    }




    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        Timer timer;
        final int[] hours = {0};
        final int[] minutes = {0};
        final int[] seconds = {0};
        jPanel1 = new javax.swing.JPanel();
        txtLastLogin = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        username = new javax.swing.JTextField();
        login = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        inArea = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        serIp = new javax.swing.JTextField();
        serPort = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Local_IP = new javax.swing.JTextField();
        Local_Port = new javax.swing.JTextField();
        Remot_IP = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        Remot_Port = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        online_user = new javax.swing.JList<>();
        jLabel9 = new javax.swing.JLabel();
        send = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        status = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        textPaneArea = new javax.swing.JTextPane();
        sendall = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        pass = new javax.swing.JTextField();
        Remot_IP1 = new javax.swing.JTextField();
        Remot_Port1 = new javax.swing.JTextField();
        statusComboBox = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        export= new javax.swing.JButton();
        LoginTimerApp timerApp;


        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1015, 523));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(117, 115, 255));
        jPanel1.setMaximumSize(new java.awt.Dimension(1200, 479));
        jPanel1.setMinimumSize(new java.awt.Dimension(1200, 479));
        jPanel1.setLayout(null);


        txtLastLogin.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jPanel1.add(txtLastLogin);
        txtLastLogin.setBounds(220, 450, 350, 40);

        jPanel1.add(export);
       export.setBounds(400, 220, 70, 40);
       export.setText("export");
        export.setBackground(new java.awt.Color(197, 103, 255));






        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Username :");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(6, 7, 90, 27);

        username.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        username.setToolTipText("");
        username.setPreferredSize(new java.awt.Dimension(7, 28));
        username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameActionPerformed(evt);
            }
        });
        jPanel1.add(username);
        username.setBounds(130, 10, 190, 28);

        login.setBackground(new java.awt.Color(197, 103, 255));
        login.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        login.setText("Login");
        login.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, null, new java.awt.Color(204, 204, 204)));
        login.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginActionPerformed(evt);


            }
        });
        jPanel1.add(login);
        login.setBounds(110, 440, 77, 31);

        jButton2.setBackground(new java.awt.Color(197, 103, 255));
        jButton2.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jButton2.setText("Logout");
        jButton2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, null, new java.awt.Color(204, 204, 204)));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);
        jButton2.setBounds(10, 440, 77, 31);




        // Create label to display the timer
        JLabel timerLabel = new JLabel("Time: 00:00:00");
        timerLabel.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        timerLabel.setBounds(150, 380, 150, 30);
        jPanel1.add(timerLabel);


        ActionListener timerListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seconds[0]++;
                if (seconds[0] == 60) {
                    seconds[0] = 0;
                    minutes[0]++;
                }
                if (minutes[0] == 60) {
                    minutes[0] = 0;
                    hours[0]++;
                }
                // Update the timer label
                timerLabel.setText(String.format("Time: %02d:%02d:%02d", hours[0], minutes[0], seconds[0]));
            }
        };

// Initialize timer (fires every 1 second)
        timer = new Timer(1000, timerListener);

        // ActionListener for Login button
        login.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed( java.awt.event.ActionEvent e) {
                timer.start();
            }
        });

        // ActionListener for Logout button
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed( java.awt.event.ActionEvent e) {
                timer.stop();
            }
        });








        inArea.setColumns(20);
        inArea.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        inArea.setLineWrap(true);
        inArea.setRows(5);
        inArea.setWrapStyleWord(true);
        inArea.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inAreaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                inAreaFocusLost(evt);
            }
        });
        jScrollPane2.setViewportView(inArea);







        jPanel1.add(jScrollPane2);
        jScrollPane2.setBounds(490, 320, 310, 160);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("TCP Server Port :");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(0, 130, 130, 27);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Avilable Interfaces :");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(0, 170, 150, 24);

        serIp.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        serIp.setToolTipText("");
        serIp.setPreferredSize(new java.awt.Dimension(7, 28));
        serIp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                serIpActionPerformed(evt);
            }
        });
        jPanel1.add(serIp);
        serIp.setBounds(130, 90, 190, 30);

        serPort.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        serPort.setToolTipText("");
        serPort.setPreferredSize(new java.awt.Dimension(7, 28));
        serPort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                serPortActionPerformed(evt);
            }
        });
        jPanel1.add(serPort);
        serPort.setBounds(130, 130, 190, 30);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("TCP Server IP :");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(0, 90, 110, 24);

        jComboBox1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Wifi", "Ethernet:169.254.49.56", "Loopback Pseudo-Interface 1:127.0.0.1", "Item 4" }));
        jPanel1.add(jComboBox1);
        jComboBox1.setBounds(160, 170, 303, 30);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("Local Port :");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(10, 250, 100, 27);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("Local IP :");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(10, 220, 80, 24);

        Local_IP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Local_IP.setToolTipText("");
        Local_IP.setPreferredSize(new java.awt.Dimension(7, 28));
        Local_IP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Local_IPActionPerformed(evt);
            }
        });
        jPanel1.add(Local_IP);
        Local_IP.setBounds(130, 210, 190, 30);

        Local_Port.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Local_Port.setToolTipText("");
        Local_Port.setPreferredSize(new java.awt.Dimension(7, 28));
        Local_Port.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Local_PortActionPerformed(evt);
            }
        });
        jPanel1.add(Local_Port);
        Local_Port.setBounds(130, 250, 190, 30);

        Remot_IP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Remot_IP.setToolTipText("");
        Remot_IP.setPreferredSize(new java.awt.Dimension(7, 28));
        Remot_IP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Remot_IPActionPerformed(evt);
            }
        });
        jPanel1.add(Remot_IP);
        Remot_IP.setBounds(130, 290, 190, 28);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setText("Remote IP :");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(10, 290, 100, 24);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setText("Remote Port :");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(0, 330, 100, 27);

        Remot_Port.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Remot_Port.setToolTipText("");
        Remot_Port.setPreferredSize(new java.awt.Dimension(7, 28));
        Remot_Port.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Remot_PortActionPerformed(evt);
            }
        });
        jPanel1.add(Remot_Port);
        Remot_Port.setBounds(130, 330, 190, 30);

        online_user.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                online_userKeyPressed(evt);
            }
        });
        online_user.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                online_userValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(online_user);

        jPanel1.add(jScrollPane3);
        jScrollPane3.setBounds(820, 50, 190, 420);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Online Users :");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(860, 20, 110, 24);

        send.setBackground(new java.awt.Color(197, 103, 255));
        send.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        send.setText("Send");
        send.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, null, new java.awt.Color(204, 204, 204)));
        send.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendActionPerformed(evt);
            }
        });
        jPanel1.add(send);
        send.setBounds(400, 440, 70, 39);


        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setText("  Status :");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(390, 20, 60, 27);

        status.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        status.setToolTipText("");
        status.setPreferredSize(new java.awt.Dimension(7, 28));
        status.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statusActionPerformed(evt);
            }
        });

        jPanel1.add(status);
        status.setBounds(400, 50, 390, 28);

        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        textPaneArea.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        textPaneArea.setFocusCycleRoot(false);
        jScrollPane4.setViewportView(textPaneArea);




        export.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                // Specify the file name directly
                String fileName = "export_Chat.txt"; // Specify the file name and path here

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                    // Write the content of the text area to the file
                    writer.write(textPaneArea.getText());
                    // Show success message
                    JOptionPane.showMessageDialog(null, "File exported successfully to: " + fileName);
                } catch (IOException ex) {
                    // Show error message in case of failure
                    JOptionPane.showMessageDialog(null, "Export error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });









        jPanel1.add(jScrollPane4);
        jScrollPane4.setBounds(490, 110, 300, 190);

        sendall.setBackground(new java.awt.Color(197, 103, 255));
        sendall.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        sendall.setText("Send all");
        sendall.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, null, new java.awt.Color(204, 204, 204)));
        sendall.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        sendall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendallActionPerformed(evt);
            }
        });
        jPanel1.add(sendall);
        sendall.setBounds(400, 380, 70, 39);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("password :");
        jPanel1.add(jLabel11);
        jLabel11.setBounds(6, 43, 90, 27);


        pass.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        pass.setToolTipText("");
        pass.setPreferredSize(new java.awt.Dimension(7, 28));
        jPanel1.add(pass);
        pass.setBounds(130, 50, 190, 28);


        Remot_IP1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Remot_IP1.setToolTipText("");
        Remot_IP1.setPreferredSize(new java.awt.Dimension(7, 28));
        Remot_IP1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Remot_IP1ActionPerformed(evt);
            }
        });

        jPanel1.add(Remot_IP1);
        Remot_IP1.setBounds(270, 290, 41, 23);

        Remot_Port1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Remot_Port1.setToolTipText("");
        Remot_Port1.setPreferredSize(new java.awt.Dimension(7, 28));
        Remot_Port1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Remot_Port1ActionPerformed(evt);
            }
        });
        jPanel1.add(Remot_Port1);
        Remot_Port1.setBounds(270, 330, 41, 23);

        statusComboBox.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        statusComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Active", "Busy", "Away" }));
        statusComboBox.setSelectedIndex(-1);
        statusComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                statusComboBoxItemStateChanged(evt);
            }
        });
        statusComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statusComboBoxActionPerformed(evt);
            }
        });
        jPanel1.add(statusComboBox);
        statusComboBox.setBounds(130, 380, 190, 30);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setText("Status:");
        jPanel1.add(jLabel12);
        jLabel12.setBounds(10, 380, 50, 20);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setText("Write you'r msg:");
        jPanel1.add(jLabel13);
        jLabel13.setBounds(380, 310, 100, 40);

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setText("chat:");
        jPanel1.add(jLabel14);
        jLabel14.setBounds(410, 110, 80, 20);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 1020, 490);

        pack();
    }// </editor-fold>//GEN-END:initComponents

        
    
    DefaultListModel dlm;
    DataInputStream dataFromServer;
    DataInputStream DataInputStream;
    DataOutputStream dataToServer;
    Socket serverSocket;
    Read r;
    receive channel;
    boolean t = false;
    boolean j = false;
    private void loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginActionPerformed
        // TODO add your handling code here:
        if (username.getText().equals("") || serIp.getText().equals("") || Local_IP.getText().equals("")
                || Local_Port.getText().equals("") || serPort.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "You should enter the following (TCP Port&IP, local Port&IP and your name)");
        } else if (!logedin) {
          
            userName = username.getText().trim();
            String TcpIP = serIp.getText();
            int TcpPort = Integer.valueOf(serPort.getText().trim());
            String localIP = Local_IP.getText();
            localPort = Integer.valueOf(Local_Port.getText().trim());
            conn = true;
            localIp = Local_IP.getText();
            localPort = Integer.parseInt(Local_Port.getText());
            try {
                  
                socket = new DatagramSocket(localPort);
            } catch (SocketException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                File f=new File("accounts.txt");
                   BufferedReader freader=new BufferedReader(new FileReader(f)); 
               	String s1;

                    while((s1 = freader.readLine()) != null) {
                          s1=s1.toUpperCase();
            String[] st = s1.split(" ");
          
     	    if(st[0].equals(username.getText().toUpperCase()) && st[1].equals(pass.getText().toUpperCase())){
                 serverSocket = new Socket(InetAddress.getByName(TcpIP), TcpPort, InetAddress.getByName(localIP), localPort);
                dataFromServer = new DataInputStream(serverSocket.getInputStream());
                dataToServer = new DataOutputStream(serverSocket.getOutputStream());
                dataToServer.writeUTF(userName);
                String s;
          
                DataInputStream = new DataInputStream(serverSocket.getInputStream());
                s = DataInputStream.readUTF();
                if (s.equals("founded")) {
                    JOptionPane.showMessageDialog(null, "You are already login!\n", "ERROR", JOptionPane.ERROR_MESSAGE);
       
                } else if (s.equals("accept")) {
                    dlm = new DefaultListModel();
                    online_user.setModel(dlm);
                    r = new Read(userName);
                    r.start();
                     updateStatus("Active");
                }
             
                j = true;
              
                channel = new receive(this);
                channel.start();
                t = true;
                JOptionPane.showMessageDialog(null, "You are loged in successfully");
                logedin = true;
                   
                txtLastLogin.setVisible(true);
                String date=getLastLogin(username.getText());
                if(date!=null){
                    txtLastLogin.setText("Last Login: "+date);
                }
                else{
                    System.out.println("null");
                }
                break;
            }
            
            }
                    if(logedin==false){
                        JOptionPane.showMessageDialog(null, "invalid login information, either user name or password");
                    }
             
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "The local port is used");
            }
        } else {
            JOptionPane.showMessageDialog(null, "You are already loged in");
        }
    }//GEN-LAST:event_loginActionPerformed


    class Read extends Thread {

        String userName;

        public Read(String userName) {
            this.userName = userName;

        }

        public void run() {
            while (j) {
                try {
                    String inputData = dataFromServer.readUTF();
                    if (inputData.equals("logout")) {
                        break;
                    }
                    if (inputData.contains("add to list")) {
                        inputData = inputData.substring(11);
                        dlm.clear();
                        StringTokenizer st = new StringTokenizer(inputData, "&?");
                        while (st.hasMoreTokens()) {
                            String line = st.nextToken();
                            String[] tokens = line.split(",");
                            
                                String element = new String(tokens[0]+","+tokens[2] + "," + tokens[1]+ "," + tokens[3]); // Include the status
                              
                                dlm.addElement(element);
                            
                        }
                    }
                } catch (IOException ex) {

                }

            }
        }
    }

    private void serIpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_serIpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_serIpActionPerformed

    private void serPortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_serPortActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_serPortActionPerformed

    private void Local_IPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Local_IPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Local_IPActionPerformed

    private void Local_PortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Local_PortActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Local_PortActionPerformed

    private void Remot_IPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Remot_IPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Remot_IPActionPerformed

    private void Remot_PortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Remot_PortActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Remot_PortActionPerformed

    private void sendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendActionPerformed
        // TODO add your handling code here:
        try {
            if (!conn) {
                JOptionPane.showMessageDialog(null, "You can't send, pleace Login first");
            } else if (Remot_IP.getText().equals("") || Remot_Port.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "You should select a user from the online user list");
            } else if (inArea.getText().equals("") || inArea.getText().equals("enter text here")) {
                JOptionPane.showMessageDialog(null, "You can't send empty message");
            } else {
                userName = username.getText();
                remotIp = Remot_IP.getText();
                remotPort = Integer.parseInt(Remot_Port.getText());
                remot_IPAddress = InetAddress.getByName(remotIp);
                String msg = inArea.getText();
                inArea.setText("");
                StyledDocument doc = textPaneArea.getStyledDocument();
                Style style = textPaneArea.addStyle("", null);
                StyleConstants.setForeground(style, Color.RED);
                StyleConstants.setBackground(style, Color.white);
          LocalDateTime now = LocalDateTime.now();
                String s1 = "["+now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a"))+"] ME: " + msg + " From " + localPort + "\n";
                doc.insertString(doc.getLength(), s1, style);
                msg = userName + ": " + msg;
                S_buffer = msg.getBytes();
                sendpacket = new DatagramPacket(S_buffer, S_buffer.length, remot_IPAddress, remotPort);
                socket.send(sendpacket);
            }
        } catch (UnknownHostException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | BadLocationException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_sendActionPerformed

    private void statusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_statusActionPerformed

    private void inAreaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inAreaFocusGained
        // TODO add your handling code here:
        if (inArea.getText().equals("enter text here")) {
            inArea.setText("");
            inArea.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_inAreaFocusGained

    private void inAreaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inAreaFocusLost
        // TODO add your handling code here:
        if (inArea.getText().isEmpty()) {
            inArea.setForeground(Color.GRAY);
            inArea.setText("enter text here");
        }
    }//GEN-LAST:event_inAreaFocusLost

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (logedin) {
            JOptionPane.showMessageDialog(null, "You are loged out successfully");
            logedin = false;
            t = false;
            j = false;

            try {
                String localIp1 = Local_IP.getText();
                int localPort1 = Integer.parseInt(Local_Port.getText());
                InetAddress remot_IPAddress1 = InetAddress.getByName(localIp1);
                String msg = "logout";
                S_buffer = msg.getBytes();
                sendpacket = new DatagramPacket(S_buffer, S_buffer.length, remot_IPAddress1, localPort1);
                socket.send(sendpacket);
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
            String s = "logout";
            try {
                dataToServer.writeUTF(s);

            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
            dlm.clear();

            try {
                socket.close();
                serverSocket.close();
                DataInputStream.close();
                dataFromServer.close();
                dataToServer.close();

            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            JOptionPane.showMessageDialog(null, "You are already loged out");
        }
        Remot_Port1.setText("");

        Remot_IP1.setText("");

        Local_Port.setText("");

        Local_IP.setText("");

        serPort.setText("");

        serIp.setText("");

        pass.setText("");

        username.setText("");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
    // Perform cleanup before closing the window
        performBeforeClosingAction();
    }//GEN-LAST:event_formWindowClosing

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
       // Perform cleanup before closing the window
        performBeforeClosingAction();
    }//GEN-LAST:event_formWindowClosed

    private void online_userValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_online_userValueChanged
        // TODO add your handling code here:
        try {
            int x = online_user.getModel().getSize();
            if (!evt.getValueIsAdjusting() && x != 0) {
                String s = (online_user.getSelectedValue().toString());
                String[] tokens = s.split(",");
                Remot_IP.setText(tokens[1]);
                Remot_Port.setText(tokens[2]);
            }
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_online_userValueChanged

       private void inAreaKeyReleased(java.awt.event.KeyEvent evt) {
        updateStatus("Active");
    }

    private void inAreaMouseClicked(java.awt.event.MouseEvent evt) {
        updateStatus("Active");
    }

    private void inAreaMousePressed(java.awt.event.MouseEvent evt) {
        updateStatus("Active");
    }

    private void inAreaMouseReleased(java.awt.event.MouseEvent evt) {
        updateStatus("Active");
    }

    private void inAreaMouseEntered(java.awt.event.MouseEvent evt) {
        updateStatus("Active");
    }

    private void inAreaMouseExited(java.awt.event.MouseEvent evt) {
        updateStatus("Away");
    }
    
    
    
    
    
    
    private void sendallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendallActionPerformed
        // TODO add your handling code here:
        try {
            if (!conn) {
                JOptionPane.showMessageDialog(null, "You can't send, pleace Login first");
            }
            else if (inArea.getText().equals("") || inArea.getText().equals("enter text here")) {
                JOptionPane.showMessageDialog(null, "You can't send empty message");
            }
         
               else {
                 int x = online_user.getModel().getSize();
                 
                if (x != 0) {
                

                for(int index=0;index<x;index++) {
                    
                    ListModel model = online_user.getModel();
                 
                String s = (model.getElementAt(index).toString());
               
                String[] tokens = s.split(",");
                
                  Remot_IP1.setText(tokens[1]);
                  
                Remot_Port1.setText(tokens[2]);
                
                 userName = username.getText();
                remotIp = Remot_IP1.getText();
                  if(!tokens[2].equals(Local_Port.getText())){
                     remotPort = Integer.parseInt(Remot_Port1.getText());
                remot_IPAddress = InetAddress.getByName(remotIp);
                String msg = inArea.getText();
                
                StyledDocument doc = textPaneArea.getStyledDocument();
                Style style = textPaneArea.addStyle("", null);
                StyleConstants.setForeground(style, Color.RED);
                StyleConstants.setBackground(style, Color.white);
                                 LocalDateTime now = LocalDateTime.now();
                String s1 = "["+now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a"))+"] ME: " + msg + " From " + localPort +" to " + tokens[2]+"\n";
                doc.insertString(doc.getLength(), s1, style);
                msg = userName + ": " + msg;
                S_buffer = msg.getBytes();
                sendpacket = new DatagramPacket(S_buffer, S_buffer.length, remot_IPAddress, remotPort);
                socket.send(sendpacket);
                }
                else{
                    
                }
               
                }
                 inArea.setText("");
            }
        }
        }
               
     
            
        
        catch (Exception ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }//GEN-LAST:event_sendallActionPerformed

    private void Remot_Port1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Remot_Port1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Remot_Port1ActionPerformed

    private void Remot_IP1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Remot_IP1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Remot_IP1ActionPerformed

    private void usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameActionPerformed

    private void online_userKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_online_userKeyPressed
       updateStatus("Active");
    }//GEN-LAST:event_online_userKeyPressed

    
    private void statusComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusComboBoxActionPerformed
      String newStatus = (String) statusComboBox.getSelectedItem();
    updateStatus(newStatus);
    
    }//GEN-LAST:event_statusComboBoxActionPerformed

    private void statusComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_statusComboBoxItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_statusComboBoxItemStateChanged
       private Color getRandomColor() {
        // Generate a random color
        int r = (int) (Math.random() * 256);
        int g = (int) (Math.random() * 256);
        int b = (int) (Math.random() * 256);
        return new Color(r, g, b);
    }

    private HashMap<Integer, Color> userColorMap = new HashMap<>();
 private Color getUserColor(int username) {
        // Check if the user already has a color assigned
        if (!userColorMap.containsKey(username)) {
            // Assign a random color for the user
            Color randomColor = getRandomColor();
            userColorMap.put(username, randomColor);
        }
        return userColorMap.get(username);}
 
    void receive() {
        try {
            if (t) {
                StyledDocument doc = textPaneArea.getStyledDocument();
                Style style = textPaneArea.addStyle("", null);
                socket.receive(receive_packet);
                String msg = new String(R_buffer, 0, receive_packet.getLength());
                if (msg.equals("logout")) {
                    return;
                }
                InetAddress S_IPAddress = receive_packet.getAddress();
                int Sport = receive_packet.getPort();
                StyleConstants.setForeground(style, getUserColor(Sport));
                StyleConstants.setBackground(style, Color.white);

                LocalDateTime now = LocalDateTime.now();
                String s1 ="["+now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a"))+"]"+ msg + " From " + Sport + "\n";

                doc.insertString(doc.getLength(), s1, style);
                String s = S_IPAddress.getHostAddress();
                status.setText("Received From IP= " + s + ", port: " + Sport);
            }
        } catch (IOException | BadLocationException ex) {

        }
    }

  /*  private void performBeforeClosingAction() {
        // Save the login records to the file
        String tokenz = "";
        for (String[] element : list) {
            tokenz += element[0] + " " + element[1] + "\n";
        }

        try (FileOutputStream outToFile = new FileOutputStream("loginrecords.txt");) {
            outToFile.write((tokenz).getBytes());
            outToFile.close();

            // Notify the server that the user is logging out
            if (logedin) {
                dataToServer.writeUTF("logout");
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ServerFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ServerFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public String getLastLogin(String username) {
        // Get the last login time for the given username
        for (String[] element : list) {
            if (element[0].equalsIgnoreCase(username)) {
                return element[1];
            }
        }
        return null;
    }
    */
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new Client().setVisible(true);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Local_IP;
    private javax.swing.JTextField Local_Port;
    private javax.swing.JTextField Remot_IP;
    private javax.swing.JTextField Remot_IP1;
    private javax.swing.JTextField Remot_Port;
    private javax.swing.JTextField Remot_Port1;
    private javax.swing.JTextArea inArea;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JButton login;
    private javax.swing.JList<String> online_user;
    private javax.swing.JTextField pass;
    private javax.swing.JButton send;
    private javax.swing.JButton sendall;
    private javax.swing.JTextField serIp;
    private javax.swing.JTextField serPort;
    private javax.swing.JTextField status;
    private javax.swing.JComboBox<String> statusComboBox;
    private javax.swing.JTextPane textPaneArea;
    private javax.swing.JLabel txtLastLogin;
    private javax.swing.JButton export;
    private javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables

}
