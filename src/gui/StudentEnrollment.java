package gui;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import java.util.HashMap;
import modal.ConnectionClass;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class StudentEnrollment extends javax.swing.JFrame {

    private HashMap<String, String> StudentNameMap = new HashMap<>();
    private HashMap<String, String> ClassNameMap = new HashMap<>();

    public StudentEnrollment() {
        
        initComponents();
        studentName();
        className();
        loadClass();
        loadEnrollment();
    }

    private void studentName() {
        try {
            ResultSet studentResult = ConnectionClass.executeSearch("SELECT * FROM `student`");

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (studentResult.next()) {
                vector.add(studentResult.getString("student.first_name") + " " + studentResult.getString("student.last_name"));
                StudentNameMap.put(studentResult.getString("student.first_name") + " " + studentResult.getString("student.last_name"), studentResult.getString("student.sno"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            StNo.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void className() {
        try {
            ResultSet classResult = ConnectionClass.executeSearch("SELECT * FROM `class`");

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (classResult.next()) {
                vector.add(classResult.getString("class.timeslot"));
                ClassNameMap.put(classResult.getString("class.timeslot"), classResult.getString("class.classno"));
            }

//            while (classResult.next()) {
//                vector.add(classResult.getString("subjects.description"));
//                ClassNameMap.put(classResult.getString("subjects.description"), classResult.getString("subjects.classno"));
//            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            ClNa.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
        private void loadClass() {
        try {
            ResultSet teacherResult = ConnectionClass.executeSearch("SELECT * FROM `class` INNER JOIN `subjects` ON"
                    + "`class`.`subjects_subno`=`subjects`.`subno` INNER JOIN `teacher` ON `class`.`teacher_tno`=`teacher`.`tno`");
            
            DefaultTableModel dtm = (DefaultTableModel) jTable2.getModel();
            dtm.setRowCount(0);

            while (teacherResult.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(teacherResult.getString("teacher.first_name") + " " + teacherResult.getString("teacher.last_name"));
                vector.add(teacherResult.getString("subjects.description"));
                vector.add(teacherResult.getString("class.timeSlot"));

                dtm.addRow(vector);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadEnrollment() {
        try {
            ResultSet enrollmentResult = ConnectionClass.executeSearch("SELECT * FROM `enrollment` INNER JOIN `student` "
                    + "ON `enrollment`.`student_sno`=`student`.`sno` INNER JOIN `class` ON `enrollment`.`class_classno`=`class`.`classno`");

            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);

            while (enrollmentResult.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(enrollmentResult.getString("enrollment_id"));
                vector.add(enrollmentResult.getString("student.first_name") + " " + enrollmentResult.getString("student.last_name"));
                vector.add(enrollmentResult.getString("class.timeslot"));

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
        EnNo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        StNo = new javax.swing.JComboBox<>();
        addButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        ClNa = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));
        jPanel1.setPreferredSize(new java.awt.Dimension(1019, 543));

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Student Enrollment");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Student Name");

        EnNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EnNoKeyPressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Enrollment Number");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Time Slot");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Enrollment Number", "Student Name", "Class "
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        StNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                StNoKeyPressed(evt);
            }
        });

        addButton.setBackground(new java.awt.Color(0, 0, 0));
        addButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        addButton.setForeground(new java.awt.Color(255, 255, 255));
        addButton.setText("Add ");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        updateButton.setBackground(new java.awt.Color(255, 255, 153));
        updateButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        updateButton.setText("Update");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        clearButton.setBackground(new java.awt.Color(153, 153, 153));
        clearButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        clearButton.setForeground(new java.awt.Color(255, 255, 255));
        clearButton.setText("Clear");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        ClNa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ClNaKeyPressed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Teacher", "Subject", "Time"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.getTableHeader().setReorderingAllowed(false);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(314, 314, 314))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(EnNo))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(28, 28, 28)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ClNa, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(StNo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(29, 29, 29)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(updateButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(clearButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(EnNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(StNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ClNa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addGap(27, 27, 27))
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
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        reset();
    }//GEN-LAST:event_clearButtonActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int tableRow = jTable1.getSelectedRow();

        String enrollmentNo = String.valueOf(jTable1.getValueAt(tableRow, 0));
        EnNo.setText(enrollmentNo);
        EnNo.setEditable(false);

        String studentName = String.valueOf(jTable1.getValueAt(tableRow, 1));
        StNo.setSelectedItem(studentName);

        String timeSlot = String.valueOf(jTable1.getValueAt(tableRow, 2));
        ClNa.setSelectedItem(timeSlot);
    }//GEN-LAST:event_jTable1MouseClicked

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed

        String entrollmentNo = EnNo.getText();
        String studentName = String.valueOf(StNo.getSelectedItem());
        String className = String.valueOf(ClNa.getSelectedItem());

        if (entrollmentNo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the Entrollment Number", "warning", JOptionPane.WARNING_MESSAGE);
        } else if (studentName.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select the Student Name", "warning", JOptionPane.WARNING_MESSAGE);
        } else if (className.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select the Class", "warning", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                ResultSet resultSet = ConnectionClass.executeSearch("SELECT * FROM `enrollment` WHERE `enrollment_id` = '" + entrollmentNo + "'");

                if (resultSet.next()) {
                    JOptionPane.showMessageDialog(this, "This student  already enrolled this class", "warning", JOptionPane.WARNING_MESSAGE);
                } else {

                    try {
                        ConnectionClass.executeIUD("INSERT INTO `enrollment`(`enrollment_id`,`student_sno`,`class_classno`) "
                                + "VALUES ('" + entrollmentNo + "','" + StudentNameMap.get(studentName) + "','" + ClassNameMap.get(className) + "')");
                        JOptionPane.showMessageDialog(this, "Student Enrolled to the class Successfully", "success", JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception e) {
                       e.printStackTrace();
                    }
                }
                loadEnrollment();
                reset();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }//GEN-LAST:event_addButtonActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        int selectedRow = jTable1.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a Row", "warning", JOptionPane.WARNING_MESSAGE);
        } else {

            String enrollmentNO = EnNo.getText();
            EnNo.setEditable(true);
            String studentName = String.valueOf(StNo.getSelectedItem());
            String className = String.valueOf(ClNa.getSelectedItem());

            if (enrollmentNO.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter the Subject Time Slot", "warning", JOptionPane.WARNING_MESSAGE);
            } else if (studentName.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Please select the Teacher Name  ", "warning", JOptionPane.WARNING_MESSAGE);
            } else if (className.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Please enter the Class Name  ", "warning", JOptionPane.WARNING_MESSAGE);
            } else {

                try {

                    ResultSet enrollmentResult = ConnectionClass.executeSearch("SELECT * FROM `enrollment` WHERE `enrollment_id`='" + enrollmentNO + "'");

                    boolean canUpdate = false;

                    if (enrollmentResult.next()) {

                        if (!enrollmentResult.getString("enrollment_id").equals(enrollmentNO)) {
                            JOptionPane.showMessageDialog(this, "This Student already enrolled", "Warning", JOptionPane.WARNING_MESSAGE);
                        } else {
                            canUpdate = true;
                        }
                    } else {
                        canUpdate = true;
                    }

                    if (canUpdate) {
                        ConnectionClass.executeIUD("UPDATE `enrollment` SET `student_sno`='" + StudentNameMap.get(studentName) + "',"
                                + "`class_classno`='" + ClassNameMap.get(className) + "' WHERE `enrollment_id`='" + enrollmentNO + "'");
                        JOptionPane.showMessageDialog(this, "Student Enrolled the class Update Successfully", "success", JOptionPane.INFORMATION_MESSAGE);

                        loadEnrollment();
                        reset();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        }
    }//GEN-LAST:event_updateButtonActionPerformed

    private void EnNoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EnNoKeyPressed
        if (evt.getKeyCode() == 10) {
            StNo.grabFocus();
        }
    }//GEN-LAST:event_EnNoKeyPressed

    private void StNoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_StNoKeyPressed
        if (evt.getKeyCode() == 10) {
            ClNa.grabFocus();
        }
    }//GEN-LAST:event_StNoKeyPressed

    private void ClNaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ClNaKeyPressed
        if (evt.getKeyCode() == 10) {
            addButton.grabFocus();
        }
    }//GEN-LAST:event_ClNaKeyPressed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable2MouseClicked

//    public static void main(String args[]) {
//
//        FlatMacDarkLaf.setup();
//
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                StudentEnrollment dialog = new StudentEnrollment(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
//    }
    
        public static void main(String args[]) {
        
        FlatMacDarkLaf.setup();

        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StudentRegistration().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ClNa;
    private javax.swing.JTextField EnNo;
    private javax.swing.JComboBox<String> StNo;
    private javax.swing.JButton addButton;
    private javax.swing.JButton clearButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables

    private void reset() {
        EnNo.setText("");
        StNo.setSelectedIndex(0);
        ClNa.setSelectedIndex(0);
        EnNo.setEditable(true);
        jTable1.clearSelection();
    }
}
