import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Date;

import com.mysql.jdbc.Driver;

public class Main extends JFrame {
    static final String DB_URL = "jdbc:mysql://localhost:3306/shoes_store?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    static final String ShoesIdQuery = "SELECT Shoes_ID FROM Shoes";
    static ArrayList<Integer> shoesId = new ArrayList<Integer>();
    static ArrayList<Integer> grandtotal = new ArrayList<Integer>();
    private JLabel jLabelTitle;
    private JTextField txtGrandTotal;
    private JLabel jLabelPurchaseId;
    private JTextField txtSales_ID;
    private JLabel jLabelTanggal;
    private JTextField txtSales_Date;
    private JLabel jLabelKodeVendor;
    private JTextField txtCustomer_ID;
    private JLabel jLabelVendorRef;
    private JTextField txtCustomer_RefNo;
    private JButton btnView;
    private JTextField txtViewCustomer_Name;
    private JLabel jLabelShoesID;
    static JComboBox jComboBoxShoesID;
    private JLabel jLabelNamaSepatu;
    private JTextField txtShoes_Name;
    private JLabel jLabelQuantity;
    private JTextField txtQuantity;
    private JLabel jLabelPrice;
    private JTextField txtPrice;
    private JButton btnTambahItem;
    private JButton btnHapusItem;
    private JLabel jLabelSalesDetail;
    private JTable jTableSalesDetail;
    private JScrollPane jScrollPane;
    private DefaultTableModel model;
    private JButton btnSimpan;
    private JButton btnKeluar;

    public Main() {
        initComponents();
    }

    private void initComponents() {

        Object headers[]={"ID","Nama Sepatu","Quantity", "Price", "SubTotal"};

        setTitle("Shoes Store");
        jLabelTitle = new JLabel();
        txtGrandTotal = new JTextField();
        jLabelPurchaseId = new JLabel();
        txtSales_ID = new JTextField();
        jLabelTanggal = new JLabel();
        txtSales_Date = new JTextField();
        jLabelKodeVendor = new JLabel();
        txtCustomer_ID = new JTextField();
        jLabelVendorRef = new JLabel();
        txtCustomer_RefNo = new JTextField();
        btnView = new JButton();
        txtViewCustomer_Name = new JTextField();
        jLabelShoesID = new JLabel();
        jComboBoxShoesID = new JComboBox(shoesId.toArray());
        jLabelNamaSepatu = new JLabel();
        txtShoes_Name = new JTextField();
        jLabelQuantity = new JLabel();
        txtQuantity = new JTextField();
        jLabelPrice = new JLabel();
        txtPrice = new JTextField();
        btnTambahItem = new JButton();
        btnHapusItem = new JButton();
        jLabelSalesDetail = new JLabel();
        jScrollPane = new JScrollPane();
        jTableSalesDetail = new JTable();
        String data[][] = {};
        String col[] = {"ID","Nama Sepatu", "Quantity", "Price", "SubTotal"};
        model = new DefaultTableModel(data, col);
        jTableSalesDetail.setModel(model);
        jScrollPane.setViewportView(jTableSalesDetail);
        btnSimpan = new JButton();
        btnKeluar = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jLabelTitle.setText("Shoes Store");
        txtGrandTotal.setText("Grand Total");
        jLabelPurchaseId.setText("Purchase ID");
        jLabelTanggal.setText("Tanggal");
        jLabelKodeVendor.setText("Kode Vendor");
        jLabelVendorRef.setText("Vendor RefNo");
        jLabelShoesID.setText("Shoes_ID");
        jLabelNamaSepatu.setText("Nama Sepatu");
        jLabelQuantity.setText("Quantity");
        jLabelPrice.setText("Price");
        jLabelSalesDetail.setText("Sales Detail");

        btnView.setText("View");
        btnView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnViewActionPerformed(e, txtCustomer_ID.getText());
            }
        });

        btnTambahItem.setText("Tambah Item");
        btnTambahItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnTambahActionPerformed(e);
            }
        });

        btnHapusItem.setText("Hapus Item");
        btnHapusItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnHapusActionPerformed(e);
            }
        });

        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnSimpanActionPerfomed(e);
            }
        });

        btnKeluar.setText("Keluar");
        btnKeluar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnKeluarActionPerformed(e);
            }
        });


        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup()
                                        .addGroup(layout.createParallelGroup()
                                                .addGroup(GroupLayout.Alignment.CENTER, layout.createSequentialGroup()
                                                        .addComponent(jLabelTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                )
                                                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                        .addComponent(txtGrandTotal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                )
                                                .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                        .addComponent(jLabelPurchaseId, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(txtSales_ID, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                                )
                                                .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                        .addComponent(jLabelTanggal, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(txtSales_Date, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                                )
                                                .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                        .addComponent(jLabelKodeVendor, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(txtCustomer_ID, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(btnView)
                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(txtViewCustomer_Name, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                                )
                                                .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                        .addComponent(jLabelVendorRef, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(txtCustomer_RefNo, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                                )
                                                .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                        .addComponent(jLabelShoesID, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabelNamaSepatu, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabelQuantity, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabelPrice, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                                )
                                                .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                        .addComponent(jComboBoxShoesID, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(txtShoes_Name, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(txtQuantity, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(txtPrice, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btnTambahItem, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btnHapusItem, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                                                )
                                                .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                        .addComponent(jLabelSalesDetail, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                                )
                                                .addGroup(GroupLayout.Alignment.CENTER, layout.createSequentialGroup()
                                                        .addComponent(jScrollPane, GroupLayout.PREFERRED_SIZE, 552, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btnSimpan, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btnKeluar, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                                                )
                                        )
                                )
                        )
        );
        layout.setVerticalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addComponent(jLabelTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                )
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addComponent(txtGrandTotal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                )
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabelPurchaseId, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtSales_ID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                )
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabelTanggal, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtSales_Date, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                )
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabelKodeVendor, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtCustomer_ID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnView, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtViewCustomer_Name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                )
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabelVendorRef, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtCustomer_RefNo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                )
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabelShoesID, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabelNamaSepatu, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabelQuantity, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabelPrice, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                                )
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jComboBoxShoesID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtShoes_Name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtQuantity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnTambahItem, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnHapusItem, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                )
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabelSalesDetail, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                                )
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addComponent(jScrollPane, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnSimpan, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnKeluar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                )
                        )
        );

        pack();
    }

    private void btnViewActionPerformed(ActionEvent e, String Customer_ID) {
        try(Connection conn = DriverManager.getConnection(DB_URL, "root", "");
            Statement stmt = conn.createStatement();
        ) {
            String sql = "SELECT Customer_Name FROM Customer WHERE Customer_ID = " + Customer_ID;
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            if (!rs.isBeforeFirst() ) {
                txtViewCustomer_Name.setText("");
                JOptionPane.showMessageDialog(null, "Customer Belum Terdaftar");
            }
            while (rs.next()) {
                System.out.println("Customer name: " + rs.getString("Customer_Name"));
                String customerName = rs.getString("Customer_Name");
                txtViewCustomer_Name.setText(customerName);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    private void btnTambahActionPerformed(ActionEvent e) {
        if (!txtShoes_Name.getText().isEmpty() && !txtQuantity.getText().isEmpty() && !txtPrice.getText().isEmpty()) {
            int shoesId = Integer.parseInt(String.valueOf(jComboBoxShoesID.getSelectedItem()));
            int quantity = Integer.parseInt(txtQuantity.getText());
            int price = Integer.parseInt(txtPrice.getText());
            int subtotal = quantity * price;


            model.addRow(new Object[]{
                    shoesId,
                    txtShoes_Name.getText(),
                    quantity,
                    price,
                    subtotal
            });


            grandtotal.add(subtotal);
            getGrandTotal();
            txtGrandTotal.setText(String.valueOf(getGrandTotal()));
        } else {
            JOptionPane.showMessageDialog(null, "Mohon isi semua data");
        }
    }

    private Integer getGrandTotal() {
        Integer sum = 0;
        for (Integer item: grandtotal) {
            sum += item;
        }
        System.out.println("Grand Total: " + sum);

        return sum;
    }

    private void btnHapusActionPerformed(ActionEvent e) {
        jComboBoxShoesID.setSelectedIndex(0);
        txtShoes_Name.setText("");
        txtQuantity.setText("");
        txtPrice.setText("");
    }

    private void btnSimpanActionPerfomed(ActionEvent e) {
        insertSales();
        insertSalesDetail();
    }

    private void btnKeluarActionPerformed(ActionEvent e) {
        System.exit(0);
    }

    private void insertSales() {
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        Date salesDate = new Date();
        try {
            salesDate =  df.parse(txtSales_Date.getText());

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        int salesId = Integer.parseInt(txtSales_ID.getText());
        java.sql.Date dateDB = new java.sql.Date(salesDate.getTime());
        int customerId = Integer.parseInt(txtCustomer_ID.getText());
        String customerRefNo = txtCustomer_RefNo.getText();

        System.out.println("date: " + salesDate);
        System.out.println("date DB: " + dateDB);

        String sql = "INSERT INTO Sales (Sales_ID, Sales_Date, Customer_ID, Customer_RefNo) VALUES (" +
                salesId + "," + "'" + dateDB + "'" + "," + customerId + "," + "\"" + customerRefNo + "\"" + ")";
        try(Connection conn = DriverManager.getConnection(DB_URL, "root", "");
            Statement stmt = conn.createStatement();
        ) {
            System.out.println(sql);
            stmt.executeUpdate(sql);
            System.out.println("Success insert data to Table Sales");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    private void insertSalesDetail() {
        int salesId = Integer.parseInt(txtSales_ID.getText());
        int shoesId = Integer.parseInt(String.valueOf(jComboBoxShoesID.getSelectedItem()));
        int quantity = Integer.parseInt(txtQuantity.getText());
        int price = Integer.parseInt(txtPrice.getText());

        String sql = "INSERT INTO Sales_Detail (Sales_ID, Shoes_ID, Quantity, Price) VALUES (" +
                salesId + "," + shoesId + "," + quantity + "," + price + ")";
        try(Connection conn = DriverManager.getConnection(DB_URL, "root", "");
            Statement stmt = conn.createStatement();
        ) {
            System.out.println(sql);
            stmt.executeUpdate(sql);
            System.out.println("Success insert data to Table Sales_Detail");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        updateStock(quantity, shoesId);
    }

    private void updateStock(int quantity, int shoesId) {
        String sql = "UPDATE Shoes SET Stock = Stock - " + quantity + " WHERE Shoes_ID = " + shoesId;
        try(Connection conn = DriverManager.getConnection(DB_URL, "root", "");
            Statement stmt = conn.createStatement();
        ) {
            System.out.println(sql);
            stmt.executeUpdate(sql);
            System.out.println("Success update stock");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public static void main(String args[]) {
        Connection con = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, "root", "");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(ShoesIdQuery);
            System.out.println("Connect Database Succesfully...");
            while(rs.next()) {
                System.out.println("Shoes ID: " + rs.getInt("Shoes_ID"));
                int shoesID = rs.getInt("Shoes_ID");
                shoesId.add(shoesID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
}