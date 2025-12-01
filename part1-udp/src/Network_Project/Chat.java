/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network_Project;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.File;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.io.IOException;
import java.util.logging.SimpleFormatter;


public class Chat extends javax.swing.JFrame {
    
    private DefaultListModel<String> messageListModel=new DefaultListModel<String>();
    private DefaultListModel<MessageTimer> archivedMessagesListModel=new DefaultListModel();
////////////////
    private static final Logger logger = Logger.getLogger(Chat.class.getName());
    private static FileHandler fileHandler;
////////////////////////
    Thread t ;
    DatagramSocket Socket;
    String SendMessage;
    class P2PCon implements Runnable
    {
        @Override
        public void run() {
            Server(); 
        }
    }
    public Chat() {
        initComponents();
        messageList.setCellRenderer(new CustomMessageListCellRenderer());
        archiveList.setCellRenderer(new CustomMessageListCellRenderer());
        recover.setVisible(false);
        archLabel.setVisible(false);
        archiveList.setVisible(false);
        jScrollPane4.setVisible(false);

        if(jComboBox1.getSelectedItem() == "Wi-Fi")
        {
            try {
                LocalIP.setText(InetAddress.getLocalHost().getHostAddress().toString());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }   
    ///////////// // Set up logging
        // Set up logging
        try {
            fileHandler = new FileHandler("chat_log.txt", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
            logger.setLevel(Level.ALL);
        } catch (IOException e) {
            e.printStackTrace();
            logActivity("Error setting up log file: " + e.getMessage());
        }
    }
    void Client() {
    try {
        String[] ipdest = RemoteIP.getText().split("\\.");
        byte[] IP_other_device = {(byte) Integer.parseInt(ipdest[0]), (byte) Integer.parseInt(ipdest[1]), (byte) Integer.parseInt(ipdest[2]), (byte) Integer.parseInt(ipdest[3])};
        InetAddress IPDest = InetAddress.getByAddress(IP_other_device);

        LocalDateTime now = LocalDateTime.now();
        SendMessage = " [" + now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "] :  " + SendMessageTextField.getText();
        byte[] SendData = SendMessage.getBytes();
        DatagramPacket SendPacket = new DatagramPacket(SendData, SendData.length, IPDest, Integer.parseInt(RemotePort.getText()));
        Socket.send(SendPacket);
        String senmsg = "Me " + SendMessage + " from " + Socket.getLocalPort() + "\n";
//writeLogEntry(senmsg);//////////////////////////////
        SwingUtilities.invokeLater(() -> {
            
            messageListModel.addElement(senmsg);
            Status.setText("Send to: " + SendPacket.getAddress().getHostAddress() + ", Port: " + SendPacket.getPort());
             logActivity("Sent message: " + SendMessage);
        });
    } catch (java.lang.NumberFormatException e) {
        JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Please enter all fields correctly", "WARNING", JOptionPane.WARNING_MESSAGE);
         logActivity("Error: " + e.getMessage());
    } catch (java.lang.IllegalArgumentException e) {
        JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Please enter remote port correctly", "WARNING", JOptionPane.WARNING_MESSAGE);
         logActivity("Error: " + e.getMessage());
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
        JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Please enter IP address correctly", "WARNING", JOptionPane.WARNING_MESSAGE);
        logActivity("Error: " + e.getMessage());
    } catch (java.lang.NullPointerException e) {
        JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Please start listening before sending a message", "WARNING", JOptionPane.WARNING_MESSAGE);
        logActivity("Error: " + e.getMessage());
    } catch (Exception e) {
        e.printStackTrace();
        logActivity("Error: " + e.getMessage());
    }
}
void Server() {
    try {
        while (true) {
            byte[] ReceiveData = new byte[65536];
            DatagramPacket ReceivePacket = new DatagramPacket(ReceiveData, ReceiveData.length);
            Socket.receive(ReceivePacket); // from other client
            String ReceiveMsg = new String(ReceivePacket.getData());
            String sert = "Rem " + ReceiveMsg.trim() + " from " + ReceivePacket.getPort() + "\n";

            SwingUtilities.invokeLater(() -> {
                messageListModel.addElement(sert);
                Status.setText("Received from: " + ReceivePacket.getAddress().getHostAddress() + ", Port: " + ReceivePacket.getPort());
              logActivity("Received message: " + ReceiveMsg.trim());
              
              
            });
        }
    } catch (java.lang.NumberFormatException e) {
        JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Please enter correct local port", "WARNING", JOptionPane.WARNING_MESSAGE);
      logActivity("Error: " + e.getMessage()); } 
    catch (java.net.BindException e) {
        JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Port already used, please choose a different one", "WARNING", JOptionPane.WARNING_MESSAGE);
     logActivity("Error: " + e.getMessage());
    } catch (Exception e) {
        e.printStackTrace();
         logActivity("Error: " + e.getMessage());
    }   
    /////////
} private static void logActivity(String message) {
        logger.info(message);
    }
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        TCPserverIP = new javax.swing.JTextField();
        TCPserverPort = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        LocalIP = new javax.swing.JTextField();
        LocalPort = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        RemoteIP = new javax.swing.JTextField();
        RemotePort = new javax.swing.JTextField();
        Send = new javax.swing.JButton();
        TestButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        SendMessageTextField = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        Status = new javax.swing.JTextField();
        archLabel = new javax.swing.JLabel();
        StartListing = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        messageList = new javax.swing.JList<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        archiveList = new javax.swing.JList<>();
        jButton3 = new javax.swing.JButton();
        recover = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Client Chat");
        setBackground(new java.awt.Color(255, 102, 0));
        setMinimumSize(new java.awt.Dimension(1000, 400));
        setResizable(false);

        jLabel2.setText("TCP Server IP:");

        TCPserverIP.setBackground(new java.awt.Color(255, 255, 204));
        TCPserverIP.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TCPserverIP.setEnabled(false);
        TCPserverIP.setPreferredSize(new java.awt.Dimension(7, 24));
        TCPserverIP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TCPserverIPActionPerformed(evt);
            }
        });

        TCPserverPort.setBackground(new java.awt.Color(255, 255, 204));
        TCPserverPort.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TCPserverPort.setEnabled(false);
        TCPserverPort.setPreferredSize(new java.awt.Dimension(7, 24));
        TCPserverPort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TCPserverPortActionPerformed(evt);
            }
        });

        jLabel3.setText("TCP Server Port :");

        jLabel4.setBackground(new java.awt.Color(153, 153, 255));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Avalible Interface");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Wi-Fi", "Ethernet", "Loopback pseudo-Interface" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel5.setText("Local IP:");

        jLabel6.setText("Local Port:");

        LocalIP.setBackground(new java.awt.Color(255, 255, 204));
        LocalIP.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        LocalIP.setPreferredSize(new java.awt.Dimension(7, 24));
        LocalIP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LocalIPActionPerformed(evt);
            }
        });

        LocalPort.setBackground(new java.awt.Color(255, 255, 204));
        LocalPort.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        LocalPort.setPreferredSize(new java.awt.Dimension(7, 24));
        LocalPort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LocalPortActionPerformed(evt);
            }
        });

        jLabel7.setText("Remote Port:");

        jLabel8.setText("Remote IP:");

        RemoteIP.setBackground(new java.awt.Color(255, 255, 204));
        RemoteIP.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        RemoteIP.setPreferredSize(new java.awt.Dimension(7, 24));

        RemotePort.setBackground(new java.awt.Color(255, 255, 204));
        RemotePort.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        RemotePort.setPreferredSize(new java.awt.Dimension(7, 24));
        RemotePort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemotePortActionPerformed(evt);
            }
        });

        Send.setBackground(new java.awt.Color(204, 255, 255));
        Send.setText("Send");
        Send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SendActionPerformed(evt);
            }
        });

        TestButton.setBackground(new java.awt.Color(204, 255, 255));
        TestButton.setText("Test Button");
        TestButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TestButtonActionPerformed(evt);
            }
        });

        SendMessageTextField.setBackground(new java.awt.Color(204, 255, 204));
        SendMessageTextField.setColumns(20);
        SendMessageTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        SendMessageTextField.setRows(5);
        SendMessageTextField.setText("Enter Message Here ");
        jScrollPane2.setViewportView(SendMessageTextField);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Status:");

        Status.setEditable(false);
        Status.setBackground(new java.awt.Color(255, 255, 255));
        Status.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Status.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StatusActionPerformed(evt);
            }
        });

        archLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        archLabel.setText("Archived Messages");

        StartListing.setBackground(new java.awt.Color(204, 255, 255));
        StartListing.setText("Start Listing");
        StartListing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StartListingActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(204, 255, 255));
        jButton1.setText("Delete Massage");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(204, 255, 255));
        jButton2.setText("Delete Conversation");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        messageList.setBackground(new java.awt.Color(204, 255, 204));
        messageList.setBorder(null);
        messageList.setModel(messageListModel);
        jScrollPane1.setViewportView(messageList);

        archiveList.setBackground(new java.awt.Color(204, 255, 204));
        archiveList.setModel(archivedMessagesListModel);
        jScrollPane4.setViewportView(archiveList);

        jButton3.setBackground(new java.awt.Color(204, 255, 255));
        jButton3.setText("Archive");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        recover.setBackground(new java.awt.Color(204, 255, 255));
        recover.setText("Recover");
        recover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recoverActionPerformed(evt);
            }
        });

        jLabel12.setAlignmentY(0.0F);
        jLabel12.setMaximumSize(new java.awt.Dimension(1500, 1500));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setText(" your message");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Chat");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(LocalIP, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)
                                .addComponent(LocalPort, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(23, 23, 23)
                                .addComponent(RemoteIP, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(RemotePort, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(TestButton, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Send, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(486, 486, 486)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(TCPserverPort, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                        .addComponent(recover)
                        .addGap(51, 51, 51))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)
                        .addComponent(TCPserverIP, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(130, 130, 130)
                        .addComponent(archLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(506, 506, 506)
                        .addComponent(StartListing, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(Status, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(TCPserverIP, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(archLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel2)))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel1)
                        .addGap(8, 8, 8)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(TCPserverPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(LocalIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(LocalPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(RemoteIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(RemotePort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TestButton, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Send, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3)
                            .addComponent(recover))))
                .addGap(10, 10, 10)
                .addComponent(StartListing, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(Status, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void TCPserverPortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TCPserverPortActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TCPserverPortActionPerformed

    private void LocalPortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LocalPortActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LocalPortActionPerformed

    private void RemotePortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemotePortActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RemotePortActionPerformed

    private void TCPserverIPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TCPserverIPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TCPserverIPActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
       if(jComboBox1.getSelectedItem().equals("Loopback pseudo-Interface"))
       {
           LocalIP.setText("127.0.0.1");
           RemoteIP.setText("127.0.0.1");
       }
       else if(jComboBox1.getSelectedItem().equals("Wi-Fi"))
        {
            try {
                RemoteIP.setText("");
                LocalIP.setText(InetAddress.getLocalHost().getHostAddress().toString());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
       else
       {
           LocalIP.setText("");
           RemoteIP.setText("");
       }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void StatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StatusActionPerformed
       logActivity("Status field updated");
    }//GEN-LAST:event_StatusActionPerformed

    private void SendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SendActionPerformed
    
           if (!SendMessageTextField.getText().isEmpty()) {
        Client();
        SendMessageTextField.setText("");
    } else {
        JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Please enter a message to send", "WARNING",
                JOptionPane.WARNING_MESSAGE);
        logActivity("Send button pressed with empty message");
    }
        
         
    }//GEN-LAST:event_SendActionPerformed

    private void TestButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TestButtonActionPerformed
        SendMessageTextField.setText("Hello");
        Client();
        SendMessageTextField.setText("");
         logActivity("Test button clicked");
    }//GEN-LAST:event_TestButtonActionPerformed

    private void StartListingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StartListingActionPerformed
       if (Socket == null) {
        try {
            Socket = new DatagramSocket(Integer.parseInt(LocalPort.getText()));
            P2PCon conn = new P2PCon();
            t = new Thread(conn);
            t.start();
            logActivity("Started listening on port: " + LocalPort.getText());
        } catch (java.lang.NumberFormatException e) {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Please enter correct local port", "WARNING",
                    JOptionPane.WARNING_MESSAGE);
            logActivity("Error: " + e.getMessage());
        } catch (java.net.BindException e) {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),
                    "Port already used, please choose a different one", "WARNING", JOptionPane.WARNING_MESSAGE);
            logActivity("Error: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            logActivity("Error: " + e.getMessage());
        }
    } else {
        try {
            Socket.close();
            t.interrupt();
            Socket = new DatagramSocket(Integer.parseInt(LocalPort.getText()));
            P2PCon conn = new P2PCon();
            t = new Thread(conn);
            t.start();
            logActivity("Restarted listening on port: " + LocalPort.getText());
        } catch (java.lang.NumberFormatException e) {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Please enter correct local port", "WARNING",
                    JOptionPane.WARNING_MESSAGE);
            logActivity("Error: " + e.getMessage());
        } catch (java.net.BindException e) {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),
                    "Port already used, please choose a different one", "WARNING", JOptionPane.WARNING_MESSAGE);
            logActivity("Error: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            logActivity("Error: " + e.getMessage());
        }
        
    }

        
        
        
        
        
        
        
        
        /*      if (Socket == null) {
        try {
            Socket = new DatagramSocket(Integer.parseInt(LocalPort.getText()));
            P2PCon conn = new P2PCon();
            t = new Thread(conn);
            t.start();
            logActivity("Started listening on port: " + LocalPort.getText());
        } catch (java.lang.NumberFormatException e) {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Please enter correct local port", "WARNING",
                    JOptionPane.WARNING_MESSAGE);
            logActivity("Error: " + e.getMessage());
        } catch (java.net.BindException e) {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),
                    "Port already used, please choose a different one", "WARNING", JOptionPane.WARNING_MESSAGE);
            logActivity("Error: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            logActivity("Error: " + e.getMessage());
        }
    } else {
        try {
            Socket.close();
            t.interrupt();
            Socket = new DatagramSocket(Integer.parseInt(LocalPort.getText()));
            P2PCon conn = new P2PCon();
            t = new Thread(conn);
            t.start();
            logActivity("Restarted listening on port: " + LocalPort.getText());
        } catch (java.lang.NumberFormatException e) {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Please enter correct local port", "WARNING",
                    JOptionPane.WARNING_MESSAGE);
            logActivity("Error: " + e.getMessage());
        } catch (java.net.BindException e) {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),
                    "Port already used, please choose a different one", "WARNING", JOptionPane.WARNING_MESSAGE);
            logActivity("Error: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            logActivity("Error: " + e.getMessage());
        }
    }
 */
            
            
        
        
        
      /*  try{
            if(Socket == null)
            {
                Socket =new DatagramSocket(Integer.parseInt(LocalPort.getText()));
                P2PCon conn =new P2PCon();
                t =new Thread(conn);
                t.start();
            }
            else
            {
                Socket.close();
               // t.stop();
                t.interrupt();
                Socket =new DatagramSocket(Integer.parseInt(LocalPort.getText()));
                P2PCon conn =new P2PCon();
                t =new Thread(conn);
                t.start();
            }
        }catch(java.lang.NumberFormatException e)
        {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Please enter Local IP and local port correctly","WARNING", JOptionPane.WARNING_MESSAGE);
        }catch(java.lang.IllegalArgumentException e)
        {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Please enter correct local port ","WARNING", JOptionPane.WARNING_MESSAGE);
        }catch(Exception e)
        {
            e.printStackTrace();
        }*/
            
    }//GEN-LAST:event_StartListingActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         
         try {
        // Get the selected index
        int selectedIndex = messageList.getSelectedIndex();
        // Remove the selected message from the list
        if (selectedIndex != -1) {
            MessageTimer deletedMessage=new MessageTimer(messageListModel.get(selectedIndex));
            deletedMessage.startTimer();
            archivedMessagesListModel.addElement(deletedMessage);
            messageListModel.remove(selectedIndex);
            logActivity("Message deleted from the conversation: " + deletedMessage);
         
         }
    } catch (Exception ex) {
        ex.printStackTrace();
        logActivity("Message deleted from the conversation ");
    }
  
    
      
    
             
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        for (int i = 0; i < messageListModel.size(); i++) {
            String element = messageListModel.getElementAt(i);
            MessageTimer deletedMessage=new MessageTimer(element);
            deletedMessage.startTimer();
            archivedMessagesListModel.addElement(deletedMessage);
        }
       messageListModel.clear();
       Status.setText("");
       logActivity("Conversation deleted");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if(archLabel.isVisible()){
        recover.setVisible(false);
        archLabel.setVisible(false);
        archiveList.setVisible(false);
        jScrollPane4.setVisible(false);
        }
        else{
        recover.setVisible(true);
        archLabel.setVisible(true);
        archiveList.setVisible(true);
        jScrollPane4.setVisible(true);
          int selectedIndex = messageList.getSelectedIndex();
    if (selectedIndex != -1) {
        String selectedMessage = (String) messageListModel.get(selectedIndex);
        archivedMessagesListModel.addElement(new MessageTimer(selectedMessage));
        messageListModel.remove(selectedIndex);
         logActivity("Message archived: " + selectedMessage);
        
    }
        }
        
  
    }//GEN-LAST:event_jButton3ActionPerformed

    private void recoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recoverActionPerformed
  try {
        // Get the selected index
        int selectedIndex = archiveList.getSelectedIndex();
        // Remove the selected message from the list
        if (selectedIndex != -1) {
            // Get the selected archived message
            String selectedMessage = archivedMessagesListModel.get(selectedIndex).toString();
            addSortedElement(messageListModel,selectedMessage);
            archivedMessagesListModel.remove(selectedIndex);
               logActivity("Message recovered from archive");
         
        }
    } catch (Exception ex) {
        ex.printStackTrace();
        logActivity("Message recovered from archive");
        
    }       


    }//GEN-LAST:event_recoverActionPerformed

    private void LocalIPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LocalIPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LocalIPActionPerformed

    
    public static void addSortedElement(DefaultListModel<String> model, String element) throws ParseException {
        model.addElement(element);
        sortModel(model);
    }

    private static void sortModel(DefaultListModel<String> model) {
        int size = model.size();
        String[] elements = new String[size];
        for (int i = 0; i < size; i++) {
            elements[i] = model.getElementAt(i);
        }
        java.util.Arrays.sort(elements, (msg1, msg2) -> {
            try {
                Date date1 = getMessageDate(msg1);
                Date date2 = getMessageDate(msg2);
                return date1.compareTo(date2);
            } catch (Exception e) {
                e.printStackTrace();
                return 0; // Handle parsing exception
            }
        });
        model.clear();
        for (String element : elements) {
            model.addElement(element);
        }
    }

    private static Date getMessageDate(String message) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int startIndex = message.indexOf("[") + 1;
        int endIndex = message.indexOf("]");
        String dateString = message.substring(startIndex, endIndex);
        return dateFormat.parse(dateString);
    }
    
    

     private class CustomMessageListCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            // Check if the message starts with "Me" (sent) or "Rem" (received)
            String message = value.toString();
            if (message.startsWith("Me")) {
                // Set color to blue for sent messages
                c.setForeground(Color.BLUE);
            } else if (message.startsWith("Rem")) {
                // Set color to red for received messages
                c.setForeground(Color.RED);
            }

            return c;
        }
    }

    // Custom class to hold both the message and its associated timer
    private class MessageTimer {
        private String message;
        private Timer timer;

        public MessageTimer(String message) {
            this.message = message;
            
            // Create a new timer with a delay of 2 minutes (120,000 milliseconds)
            timer = new Timer(120000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Remove the message from the list when the timer's action event is triggered
                    archivedMessagesListModel.removeElement(MessageTimer.this);
                }
            });
        }

        // Method to start the timer
        public void startTimer() {
            timer.start();
        }

        // Method to stop the timer
        public void stopTimer() {
            timer.stop();
        }

        // Override toString to return the message
        @Override
        public String toString() {
            return message;
        }
    }
    
    class SortedDateListModel extends DefaultListModel<String> {

    @Override
    public void addElement(String element) {
        super.addElement(element);
        sortElements();
    }

    private void sortElements() {
        int size = getSize();
        String[] elements = new String[size];
        for (int i = 0; i < size; i++) {
            elements[i] = getElementAt(i);
        }
        java.util.Arrays.sort(elements, (msg1, msg2) -> {
            try {
                Date date1 = getMessageDate(msg1);
                Date date2 = getMessageDate(msg2);
                return date1.compareTo(date2);
            } catch (Exception e) {
                e.printStackTrace();
                return 0; // Handle parsing exception
            }
        });
        clear();
        for (String element : elements) {
            addElement(element);
        }
    }
    
    private Date getMessageDate(String message) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("[yyyy-MM-dd hh:mm:ss a]");
        int startIndex = message.indexOf("[") + 1;
        int endIndex = message.indexOf("]");
        String dateString = message.substring(startIndex, endIndex);
        return dateFormat.parse(dateString);
    }
}
    
       /////////////////////////
   
    
   /*
    private void writeLogEntry(String logEntry) {
    try {
        // Create the log file if it doesn't exist
        File logFile = new File("chat_log.txt");
        if (!logFile.exists()) {
            logFile.createNewFile();
        }

        // Write the log entry to the file
        FileWriter fileWriter = new FileWriter(logFile, true); // true for appending
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        bufferedWriter.write("[" + now.format(formatter) + "] " + logEntry);
        bufferedWriter.newLine();
        bufferedWriter.close();
    } catch (IOException e) {
        e.printStackTrace();
    }*/

    
    
  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField LocalIP;
    private javax.swing.JTextField LocalPort;
    private javax.swing.JTextField RemoteIP;
    private javax.swing.JTextField RemotePort;
    private javax.swing.JButton Send;
    private javax.swing.JTextArea SendMessageTextField;
    private javax.swing.JButton StartListing;
    private javax.swing.JTextField Status;
    private javax.swing.JTextField TCPserverIP;
    private javax.swing.JTextField TCPserverPort;
    private javax.swing.JButton TestButton;
    private javax.swing.JLabel archLabel;
    private javax.swing.JList<MessageTimer> archiveList;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JList<String> messageList;
    private javax.swing.JButton recover;
    // End of variables declaration//GEN-END:variables
}
