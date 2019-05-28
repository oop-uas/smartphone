package ui;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.table.*;
import util.*;
import java.sql.*;

public class MainUI extends JFrame {

    public static Koneksi koneksi;

    private Container contentPane;
    private JTable table;
    private JScrollPane scrollPane;
    private JButton btnTambah;
    private JButton btnUbah;
    private JButton btnHapus;
    private JPanel panelButton;
    private DefaultTableModel tableModel;
    private Vector data;
    private Vector columnNames;
    public static TambahUI tambahUI;
    public static EditUI editUI;

    public MainUI() {
        koneksi = new Koneksi();
        tambahUI = new TambahUI(this);
        editUI = new EditUI(this);
        initUI();
        initData();
    }

    private void initData() {
        String query = "select * from smartphone";
        try {
            ResultSet result = koneksi.select(query);
            data = new Vector();
            while (result.next()) {
                Vector temp = new Vector();
                temp.add(result.getInt(1));
                temp.add(result.getString(2));
                temp.add(result.getString(3));
                temp.add(result.getString(4));
                temp.add(result.getInt(5));
                data.add(temp);
            }
            tableModel.setDataVector(data, columnNames);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ada kesalahan query");
        }
    }

    public void refreshTable() {
        initData();
    }

    private void initUI() {
        setTitle("Aplikasi Smartphone");

        contentPane = getContentPane();

        columnNames = new Vector();
        columnNames.add("ID");
        columnNames.add("NAMA");
        columnNames.add("BRAND");
        columnNames.add("SPEK");
        columnNames.add("HARGA");
        data = new Vector();
        tableModel = new DefaultTableModel(data, columnNames);
        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        btnTambah = new JButton("Tambah");
        btnUbah = new JButton("Ubah");
        btnHapus = new JButton("Hapus");
        panelButton = new JPanel();
        panelButton.setLayout(new GridLayout(1, 3));
        panelButton.add(btnTambah);
        panelButton.add(btnUbah);
        panelButton.add(btnHapus);
        contentPane.add(panelButton, BorderLayout.SOUTH);

        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        table.setSelectionModel(new MyCustomSingleSelection());
        btnTambah.addActionListener(new BtnTambahClick());
        btnUbah.addActionListener(new BtnUbahClick());
        btnHapus.addActionListener(new BtnHapusClick());
    }

    private class MyCustomSingleSelection extends DefaultListSelectionModel {
        public MyCustomSingleSelection() {
            setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        }
    }

    // ------- events

    private class BtnHapusClick implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            if (table.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Pilih dulu data yang akan dihapus");
            } else {
                String id = "" + table.getValueAt(table.getSelectedRow(), 0);
                String query = "delete from smartphone " + "where id_smartphone='" + id + "'";
                try {
                    koneksi.update(query);
                    JOptionPane.showMessageDialog(null, "Data dengan id " + id + " telah terhapus");
                    refreshTable();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Error query, data tidak terhapus");
                }
            }
        }
    }

    private class BtnUbahClick implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            if (table.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Pilih dulu data yang akan diubah");
            } else {
                String nama, brand, spek;
                int harga, id;
                id = (int) table.getValueAt(table.getSelectedRow(), 0);
                nama = table.getValueAt(table.getSelectedRow(), 1).toString();
                brand = (String) table.getValueAt(table.getSelectedRow(), 2);
                spek = (String) table.getValueAt(table.getSelectedRow(), 3);
                harga = (int) table.getValueAt(table.getSelectedRow(), 4);
                editUI.tampilkanForm(id, nama, brand, spek, harga);
            }
        }
    }

    private class BtnTambahClick implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            tambahUI.setVisible(true);
        }
    }

}