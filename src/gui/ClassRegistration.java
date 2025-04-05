package gui;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import java.util.HashMap;
import modal.ConnectionClass;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ClassRegistration extends javax.swing.JFrame {

    private HashMap<String, String> teacherMap = new HashMap<>();
    private HashMap<String, String> subjectMap = new HashMap<>();

    public ClassRegistration() {
        initComponents();
        loadTeachers();
        loadSubjects();
        loadClass();
        loadTeacher();
    }

    private void loadTeachers() {
        try {
            ResultSet teacherResult = ConnectionClass.executeSearch("SELECT * FROM `teacher`");

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (teacherResult.next()) {
                vector.add(teacherResult.getString("teacher.first_name") + " " + teacherResult.getString("teacher.last_name"));
                teacherMap.put(teacherResult.getString("teacher.first_name") + " " + teacherResult.getString("teacher.last_name"), teacherResult.getString("tno"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox1.setModel(model);

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    private void loadSubjects() {
        try {
            ResultSet subjectResult = ConnectionClass.executeSearch("SELECT * FROM `subjects`");

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (subjectResult.next()) {
                vector.add(subjectResult.getString("description"));
                subjectMap.put(subjectResult.getString("description"), subjectResult.getString("subno"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox2.setModel(model);

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    private void loadClass() {
        try {
            ResultSet classResult = ConnectionClass.executeSearch("SELECT * FROM `class` INNER JOIN `teacher` "
                    + "ON `teacher`.`tno`=`class`.`teacher_tno` INNER JOIN `subjects` ON `subjects`.`subno`=`class`.`subjects_subno`");

            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);

            while (classResult.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(classResult.getString("classno"));
                vector.add(classResult.getString("timeslot"));
                vector.add(classResult.getString("teacher.first_name") + " " + classResult.getString("teacher.last_name"));
                vector.add(classResult.getString("subjects.description"));

                dtm.addRow(vector);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadTeacher() {
        try {
            ResultSet teacherResult = ConnectionClass.executeSearch("SELECT * FROM `teacher`");
            DefaultTableModel dtm = (DefaultTableModel) jTable2.getModel();
            dtm.setRowCount(0);

            while (teacherResult.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(teacherResult.getString("tno"));
                vector.add(teacherResult.getString("first_name"));
                vector.add(teacherResult.getString("last_name"));
                vector.add(teacherResult.getString("subjects"));

                dtm.addRow(vector);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        add = new javax.swing.JButton();
        update = new javax.swing.JButton();
        clear = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));
        jPanel1.setPreferredSize(new java.awt.Dimension(1019, 543));

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Class Registration");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Teacher Name");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Class Number");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Time Slot");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Subject Name");

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });

        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField2KeyPressed(evt);
            }
        });

        jComboBox1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox1KeyPressed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Class Number", "Time Slot", "Teacher Name", "Subject Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        add.setBackground(new java.awt.Color(0, 0, 0));
        add.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        add.setForeground(new java.awt.Color(255, 255, 255));
        add.setText("Add Class");
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });

        update.setBackground(new java.awt.Color(255, 255, 153));
        update.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        update.setText("Update Class");
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });

        clear.setBackground(new java.awt.Color(153, 153, 153));
        clear.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        clear.setForeground(new java.awt.Color(255, 255, 255));
        clear.setText("Clear All");
        clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Teacher Number", "First Name", "Last Name", "Subjects"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(323, 323, 323)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jTextField2)
                        .addComponent(add, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(update, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                        .addComponent(clear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addGap(46, 46, 46))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(update)
                        .addGap(18, 18, 18)
                        .addComponent(clear))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        try {

            String classNo = jTextField1.getText();
            String timeSlot = jTextField2.getText();
            String teacherNo = (String) jComboBox1.getSelectedItem();
            String subjectNo = (String) jComboBox2.getSelectedItem();

            if (classNo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter the class Number", "warning", JOptionPane.WARNING_MESSAGE);
            } else if (timeSlot.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter the Time slot", "warning", JOptionPane.WARNING_MESSAGE);
            } else if (teacherNo.equals(teacherNo)) {
                JOptionPane.showMessageDialog(this, "Please select the Teacher", "warning", JOptionPane.WARNING_MESSAGE);
            } else if (subjectNo.equals(subjectNo)) {
                JOptionPane.showMessageDialog(this, "Please select the Subject", "warning", JOptionPane.WARNING_MESSAGE);

            } else {

                ResultSet resultSet = ConnectionClass.executeSearch("SELECT * FROM `class` WHERE `classno` = '" + classNo + "'");

                if (resultSet.next()) {
                    JOptionPane.showMessageDialog(this, "This class already registered", "warning", JOptionPane.WARNING_MESSAGE);
                } else {

                    ConnectionClass.executeIUD("INSERT INTO `class`(`classno`,`timeslot`,`teacher_tno`,`subjects_subno`) "
                            + "VALUES ('" + classNo + "','" + timeSlot + "','" + teacherMap.get(teacherNo) + "','" + subjectMap.get(subjectNo) + "')");
                    JOptionPane.showMessageDialog(this, "Class Added Successfully", "success", JOptionPane.INFORMATION_MESSAGE);
                }

                loadClass();
                reset();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_addActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int tableRow = jTable1.getSelectedRow();

        String classNo = String.valueOf(jTable1.getValueAt(tableRow, 0));
        jTextField1.setText(classNo);
        jTextField1.setEditable(false);

        String timeSlot = String.valueOf(jTable1.getValueAt(tableRow, 1));
        jTextField2.setText(timeSlot);

        String teacherName = String.valueOf(jTable1.getValueAt(tableRow, 2));
        jComboBox1.setSelectedItem(teacherName);

        String subjectName = String.valueOf(jTable1.getValueAt(tableRow, 3));
        jComboBox2.setSelectedItem(subjectName);
    }//GEN-LAST:event_jTable1MouseClicked

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed

        int selectedRow = jTable1.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a Row", "warning", JOptionPane.WARNING_MESSAGE);
        } else {

            String classNumber = jTextField1.getText();
            jTextField1.setEditable(true);

            String timeSlot = jTextField2.getText();
            String teacherName = String.valueOf(jComboBox1.getSelectedItem());
            String subjectName = String.valueOf(jComboBox2.getSelectedItem());

            if (timeSlot.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter the Subject Time Slot", "warning", JOptionPane.WARNING_MESSAGE);
            } else if (teacherName.equals(teacherName)) {
                JOptionPane.showMessageDialog(this, "Please enter the Teacher Name  ", "warning", JOptionPane.WARNING_MESSAGE);
            } else if (subjectName.equals(subjectName)) {
                JOptionPane.showMessageDialog(this, "Please enter the Class Name  ", "warning", JOptionPane.WARNING_MESSAGE);
            } else {

                try {

                    ResultSet subjectResult = ConnectionClass.executeSearch("SELECT * FROM `class` WHERE `classno`='" + classNumber + "'");

                    boolean canUpdate = false;

                    if (subjectResult.next()) {

                        if (!subjectResult.getString("classno").equals(classNumber)) {
                            JOptionPane.showMessageDialog(this, "This class already exist", "Warning", JOptionPane.WARNING_MESSAGE);
                        } else {
                            canUpdate = true;
                        }
                    } else {
                        canUpdate = true;
                    }

                    if (canUpdate) {
                        ConnectionClass.executeIUD("UPDATE `class` SET `timeslot`='" + timeSlot + "', `teacher_tno`='" + teacherMap.get(teacherName) + "',"
                                + "`subjects_subno`='" + subjectMap.get(subjectName) + "' WHERE `classno`='" + classNumber + "'");

                        JOptionPane.showMessageDialog(this, "Class Updated Successfully", "success", JOptionPane.INFORMATION_MESSAGE);
                        
                        loadClass();
                        reset();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        }
    }//GEN-LAST:event_updateActionPerformed

    private void clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionPerformed
        reset();
    }//GEN-LAST:event_clearActionPerformed

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        if (evt.getKeyCode() == 10) {
            jTextField2.grabFocus();
        }
    }//GEN-LAST:event_jTextField1KeyPressed

    private void jTextField2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyPressed
        if (evt.getKeyCode() == 10) {
            jComboBox1.grabFocus();
        }
    }//GEN-LAST:event_jTextField2KeyPressed

    private void jComboBox1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox1KeyPressed
        if (evt.getKeyCode() == 10) {
            jComboBox2.grabFocus();
        }
    }//GEN-LAST:event_jComboBox1KeyPressed

    public static void main(String args[]) {

        FlatMacDarkLaf.setup();

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClassRegistration().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add;
    private javax.swing.JButton clear;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JButton update;
    // End of variables declaration//GEN-END:variables

    private void reset() {
        jTextField1.setText("");
        jTextField2.setText("");
        jComboBox1.setSelectedIndex(0);
        jComboBox2.setSelectedIndex(0);
        jTextField1.setEditable(true);
        jTable1.clearSelection();
    }
}
