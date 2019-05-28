package ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class TambahUI extends JFrame {

    private Container contentPane;
    private JPanel panelForm;
    private JPanel panelButton;
    private JLabel lblId;
    private JLabel lblNama;
    private JLabel lblBrand;
    private JLabel lblSpek;
    private JLabel lblHarga;
    private JTextField txtId;
    private JTextField txtNama;
    private JTextField txtBrand;
    private JTextField txtSpek;
    private JTextField txtHarga;
    private JButton btnSimpan;
    private JButton btnBatal;
    private MainUI parent;

    public TambahUI(MainUI parent) {
        this.parent = parent;
        initUI();
    }

    private void initUI() {
        setTitle("Tambah Data Baru");

        contentPane = getContentPane();

        lblNama = new JLabel("NAMA");
        lblBrand = new JLabel("BRAND");
        lblSpek = new JLabel("SPEK");
        lblHarga = new JLabel("HARGA");
        txtNama = new JTextField();
        txtBrand = new JTextField();
        txtSpek = new JTextField();
        txtHarga = new JTextField();
        panelForm = new JPanel();
        panelForm.setLayout(new GridLayout(9, 8));
        panelForm.add(lblNama);
        panelForm.add(txtNama);
        panelForm.add(lblBrand);
        panelForm.add(txtBrand);
        panelForm.add(lblSpek);
        panelForm.add(txtSpek);
        panelForm.add(lblHarga);
        panelForm.add(txtHarga);
        contentPane.add(panelForm, BorderLayout.CENTER);

        btnSimpan = new JButton("Simpan");
        btnBatal = new JButton("Batal");
        panelButton = new JPanel();
        panelButton.setLayout(new GridLayout(1, 2));
        panelButton.add(btnSimpan);
        panelButton.add(btnBatal);
        contentPane.add(panelButton, BorderLayout.SOUTH);

        pack();

        btnBatal.addActionListener(new BtnBatalClick());
        btnSimpan.addActionListener(new BtnSimpanClick());
    }

    // ---- events

    private class BtnSimpanClick implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            String nama, brand, spek;
            int harga;
            nama = txtNama.getText();
            brand = txtBrand.getText();
            spek = txtSpek.getText();
            harga = Integer.valueOf(txtHarga.getText());
            String query = "insert into smartphone " + "(nama,brand,spesifikasi,harga) values('" + nama + "','" + brand
                    + "','" + spek + "','" + harga + "')";
            try {
                MainUI.koneksi.update(query);
                JOptionPane.showMessageDialog(null, "Data telah tersimpan");
                setVisible(false);
                parent.refreshTable();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Query error. Gagal Simpan Data");
            }
        }
    }

    private class BtnBatalClick implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            setVisible(false);
        }
    }

}