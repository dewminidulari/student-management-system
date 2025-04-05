package gui;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.lowagie.text.pdf.codec.Base64.InputStream;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modal.ConnectionClass;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class StudentPayment extends javax.swing.JFrame {

    private HashMap<String, String> teacherMap = new HashMap<>();
    private HashMap<String, String> StudentNameMap = new HashMap<>();
    private HashMap<String, String> subjectMap = new HashMap<>();

    public StudentPayment() {
        initComponents();
        generateInvoiceId();
        studentName();
        loadTeachers();
        loadSubjects();
        loadPayment("invoiceId", "ASC");

    }

    private void generateInvoiceId() {
        long id = System.currentTimeMillis();
        jTextField1.setText(String.valueOf(id));
        jTextField1.setEditable(false);
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
            jComboBox1.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }
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
            jComboBox2.setModel(model);

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
            jComboBox4.setModel(model);

        } catch (Exception e) {

            e.printStackTrace();
        }

    }
    private String column1 = "student_sno";
    private String orderby1 = "ASC";
    private String student_sno = "";

    private void loadPayment(String column, String student_sno) {

        student_sno = jTextField2.getText();
        try {
            ResultSet paymentResult = ConnectionClass.executeSearch("SELECT * FROM `invoice` INNER JOIN `student` "
                    + "ON `student`.`sno` = `invoice`.`student_sno` INNER JOIN `teacher` "
                    + "ON `teacher`.`tno`=`invoice`.`teacher_tno` INNER JOIN `subjects` ON `subjects`.`subno`=`invoice`.`subjects_subno` "
                    + "WHERE `student_sno` LIKE '" + student_sno + "%' ORDER BY " + column + " ");

            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);

            while (paymentResult.next()) {
                Vector<String> vector = new Vector<>();

                vector.add(paymentResult.getString("invoiceId"));
                vector.add(paymentResult.getString("student.first_name") + " " + paymentResult.getString("student.last_name"));
                vector.add(paymentResult.getString("teacher.first_name") + " " + paymentResult.getString("teacher.last_name"));
                vector.add(paymentResult.getString("subjects.description"));
                vector.add(paymentResult.getString("month"));
                vector.add(paymentResult.getString("value"));

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
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        jFormattedTextField2 = new javax.swing.JFormattedTextField();
        jLabel10 = new javax.swing.JLabel();
        jFormattedTextField3 = new javax.swing.JFormattedTextField();
        update = new javax.swing.JButton();
        clearField = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        printInvoice1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));
        jPanel1.setPreferredSize(new java.awt.Dimension(1019, 543));

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Student Payment");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Student Name");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Invoice ID");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Month");

        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        jComboBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox2ItemStateChanged(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Subject");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "Januray", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        jComboBox3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox3ItemStateChanged(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Class Fee");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Invoice ID", "Student Name", "Teacher Name", "Subject", "Month", "Value"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Teacher Name");

        jComboBox4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox4ItemStateChanged(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Balance");

        jFormattedTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jFormattedTextField2KeyReleased(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Payment");

        update.setBackground(new java.awt.Color(255, 255, 153));
        update.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        update.setText("Update Payment");
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });

        clearField.setBackground(new java.awt.Color(0, 51, 102));
        clearField.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        clearField.setForeground(new java.awt.Color(255, 255, 255));
        clearField.setText("Clear Field");
        clearField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearFieldActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Search Here SNO:");

        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
        });

        printInvoice1.setBackground(new java.awt.Color(0, 0, 0));
        printInvoice1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        printInvoice1.setForeground(new java.awt.Color(255, 255, 255));
        printInvoice1.setText("Payment");
        printInvoice1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printInvoice1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(357, 357, 357))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel8)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(printInvoice1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(update)
                        .addGap(18, 18, 18)
                        .addComponent(clearField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(14, 14, 14))
                    .addComponent(jFormattedTextField3)
                    .addComponent(jComboBox4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField1)
                    .addComponent(jFormattedTextField1)
                    .addComponent(jFormattedTextField2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField2))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 582, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(72, 72, 72)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFormattedTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(clearField, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(update, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(printInvoice1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
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

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed

    }//GEN-LAST:event_updateActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged

        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            String studentNo = String.valueOf(jComboBox1.getSelectedItem());

            if (studentNo != null) {
                try {

                    ResultSet studentSet = ConnectionClass.executeSearch("SELECT DISTINCT teacher.first_name, teacher.last_name, subjects.description "
                            + "FROM student INNER JOIN enrollment ON student.sno = enrollment.student_sno "
                            + "INNER JOIN class ON class.classno = enrollment.class_classno "
                            + "INNER JOIN teacher ON teacher.tno = class.teacher_tno "
                            + "INNER JOIN subjects ON subjects.subno = class.subjects_subno "
                            + "WHERE student.sno = '" + StudentNameMap.get(studentNo) + "'");

                    Vector<String> teacherVector = new Vector<>();
                    Vector<String> subjectVector = new Vector<>();

                    teacherVector.add("Select");
                    subjectVector.add("Select");

                    while (studentSet.next()) {
                        String teacherName = studentSet.getString("teacher.first_name") + " " + studentSet.getString("teacher.last_name");
                        teacherVector.add(teacherName);

                        String subjectDescription = studentSet.getString("subjects.description");
                        subjectVector.add(subjectDescription);
                    }

                    jComboBox2.setModel(new DefaultComboBoxModel<>(teacherVector));
                    jComboBox4.setModel(new DefaultComboBoxModel<>(subjectVector));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {

                jComboBox2.setModel(new DefaultComboBoxModel<>(new String[]{"Select"}));
                jComboBox4.setModel(new DefaultComboBoxModel<>(new String[]{"Select"}));
                jFormattedTextField1.setText("");
            }
        }


    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jComboBox4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox4ItemStateChanged
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            String subjectName = String.valueOf(jComboBox4.getSelectedItem());

            if (!subjectName.equals("Select")) {
                try {

                    ResultSet subjectResult = ConnectionClass.executeSearch("SELECT `price` FROM `subjects` WHERE `description` = '" + subjectName + "'");

                    if (subjectResult.next()) {

                        jFormattedTextField1.setText(subjectResult.getString("price"));
                    } else {

                        jFormattedTextField1.setText("");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {

                jFormattedTextField1.setText("");
            }
        }
    }//GEN-LAST:event_jComboBox4ItemStateChanged

    private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            String teacherNo = String.valueOf(jComboBox2.getSelectedItem());

            if (teacherNo != null) {
                try {

                    ResultSet resultSet = ConnectionClass.executeSearch("SELECT DISTINCT subjects.description "
                            + "FROM class "
                            + "INNER JOIN teacher ON class.teacher_tno = teacher.tno "
                            + "INNER JOIN subjects ON class.subjects_subno = subjects.subno "
                            + "WHERE teacher.tno = '" + teacherMap.get(teacherNo) + "'"
                    );

                    Vector<String> subjectVector = new Vector<>();
                    subjectVector.add("Select");

                    while (resultSet.next()) {
                        String subjectDescription = resultSet.getString("description");
                        subjectVector.add(subjectDescription);
                    }

                    jComboBox4.setModel(new DefaultComboBoxModel<>(subjectVector));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {

                jComboBox4.setModel(new DefaultComboBoxModel<>(new String[]{"Select"}));
                jFormattedTextField1.setText("");
            }
        }
    }//GEN-LAST:event_jComboBox2ItemStateChanged

    private void jFormattedTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextField2KeyReleased
        payment();
    }//GEN-LAST:event_jFormattedTextField2KeyReleased

    private void clearFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearFieldActionPerformed
        reset();
    }//GEN-LAST:event_clearFieldActionPerformed

    private void jComboBox3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox3ItemStateChanged
        searchByMonth(column, orderby, month);
    }//GEN-LAST:event_jComboBox3ItemStateChanged

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        loadPayment(column, student_sno);
    }//GEN-LAST:event_jTextField2KeyReleased

    private void printInvoice1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printInvoice1ActionPerformed
        try {

            String invoiceID = jTextField1.getText();
            String studentName = String.valueOf(jComboBox1.getSelectedItem());
            String teacherName = String.valueOf(jComboBox2.getSelectedItem());
            String subject = String.valueOf(jComboBox4.getSelectedItem());
            String month = String.valueOf(jComboBox3.getSelectedItem());
            String value = jFormattedTextField2.getText();

            if (studentName.equals("Selecet")) {
                JOptionPane.showMessageDialog(this, "Please select the Student Name", "warning", JOptionPane.WARNING_MESSAGE);
            } else if (teacherName.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Please select the Teacher Name", "warning", JOptionPane.WARNING_MESSAGE);
            } else if (subject.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Please select the Subject", "warning", JOptionPane.WARNING_MESSAGE);
            } else if (month.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Please select the Month", "warning", JOptionPane.WARNING_MESSAGE);
            } else if (value.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter the paid amount", "warning", JOptionPane.WARNING_MESSAGE);
            } else {

                ResultSet paymentResultSet = ConnectionClass.executeSearch("SELECT * FROM `invoice` WHERE `student_sno` = '" + StudentNameMap.get(studentName) + "' AND `month` = '" + month + "'");

                if (paymentResultSet.next()) {
                    JOptionPane.showMessageDialog(this, "This Student  already paid for this month", "warning", JOptionPane.WARNING_MESSAGE);
                } else {

                    ConnectionClass.executeIUD("INSERT INTO `invoice`(`invoiceId`,`student_sno`,`teacher_tno`,`subjects_subno`,`month`,`value`) "
                            + "VALUES('" + invoiceID + "','" + StudentNameMap.get(studentName) + "','" + teacherMap.get(teacherName) + "','"
                            + subjectMap.get(subject) + "','" + month + "','" + value + "')");

                    JOptionPane.showMessageDialog(this, "Successfully added to the invoice", "warning", JOptionPane.WARNING_MESSAGE);

//                  
                    String path = "src//report//adyapana.jasper";

                    HashMap<String, Object> parameters = new HashMap<>();

                    parameters.put("Parameter1", jFormattedTextField1.getText());
                    parameters.put("Parameter2", jFormattedTextField2.getText());
                    parameters.put("Parameter3", jFormattedTextField3.getText());

                    JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable1.getModel());
                    JasperPrint jasperPrint = JasperFillManager.fillReport(path, parameters, dataSource);
                    JasperViewer.viewReport(jasperPrint, false);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_printInvoice1ActionPerformed

    public static void main(String args[]) {

        FlatMacDarkLaf.setup();

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StudentPayment().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clearField;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JFormattedTextField jFormattedTextField2;
    private javax.swing.JFormattedTextField jFormattedTextField3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JButton printInvoice1;
    private javax.swing.JButton update;
    // End of variables declaration//GEN-END:variables

    private void payment() {
        try {
            String totalAmountStr = jFormattedTextField1.getText();
            String paidAmountStr = jFormattedTextField2.getText();

            if (totalAmountStr.isEmpty() || paidAmountStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter all required amounts", "Warning", JOptionPane.WARNING_MESSAGE);

            } else {

                double totalAmount = Double.parseDouble(totalAmountStr);
                double paidAmount = Double.parseDouble(paidAmountStr);

                double balance = totalAmount - paidAmount;

                jFormattedTextField3.setText(String.valueOf(balance));
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void reset() {
        jTextField1.setText("");
        jComboBox1.setSelectedIndex(0);
        jComboBox2.setSelectedIndex(0);
        jComboBox3.setSelectedIndex(0);
        jComboBox4.setSelectedIndex(0);
        jFormattedTextField1.setText("");
        jFormattedTextField2.setText("");
        jFormattedTextField3.setText("");
        jTextField1.setEditable(true);
        jTable1.clearSelection();
        generateInvoiceId();
    }

    private String column = "month";
    private String orderby = "ASC";
    private String month = "";

    private void searchByMonth(String column, String orderby, String month) {

        month = (String) jComboBox3.getSelectedItem();

        if (month.equals("Select")) {
            loadPayment(column, orderby);
        } else {
            try {
                ResultSet paymentResult1 = ConnectionClass.executeSearch("SELECT * FROM `invoice` INNER JOIN `student` "
                        + "ON `student`.`sno` = `invoice`.`student_sno` INNER JOIN `teacher` "
                        + "ON `teacher`.`tno`=`invoice`.`teacher_tno` INNER JOIN `subjects` ON `subjects`.`subno`=`invoice`.`subjects_subno` "
                        + "WHERE `month` LIKE '" + month + "%' ORDER BY " + column + " ");

                DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
                dtm.setRowCount(0);

                while (paymentResult1.next()) {
                    Vector<String> vector = new Vector<>();
                    vector.add(paymentResult1.getString("invoiceId"));
                    vector.add(paymentResult1.getString("student.first_name") + " " + paymentResult1.getString("student.last_name"));
                    vector.add(paymentResult1.getString("teacher.first_name") + " " + paymentResult1.getString("teacher.last_name"));
                    vector.add(paymentResult1.getString("subjects.description"));
                    vector.add(paymentResult1.getString("month"));
                    vector.add(paymentResult1.getString("value"));

                    dtm.addRow(vector);

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
