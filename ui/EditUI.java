package ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class EditUI extends JFrame {

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

    public EditUI(MainUI parent) {
        this.parent = parent;
        initUI();
    }

    private void initUI() {
        setTitle("Formulir Ubah Data");

        contentPane = getContentPane();

        lblId = new JLabel("ID");
        lblNama = new JLabel("NAMA");
        lblBrand = new JLabel("BRAND");
        lblSpek = new JLabel("SPEK");
        lblHarga = new JLabel("HARGA");
        txtId = new JTextField();
        txtNama = new JTextField();
        txtBrand = new JTextField();
        txtSpek = new JTextField();
        txtHarga = new JTextField();
        panelForm = new JPanel();
        panelForm.setLayout(new GridLayout(9, 8));
        panelForm.add(lblId);
        panelForm.add(txtId);
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

        txtId.setEditable(false);

        btnBatal.addActionListener(new BtnBatalClick());
        btnSimpan.addActionListener(new BtnSimpanClick());
    }

    public void tampilkanForm(int id, String nama, String brand, String spek, int harga) {
        txtId.setText(String.valueOf(id));
        txtNama.setText(nama);
        txtBrand.setText(brand);
        txtSpek.setText(spek);
        txtHarga.setText(String.valueOf(harga));
        setVisible(true);
    }

    // ----- events

    private class BtnSimpanClick implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            String nama, brand, spek;
            int id, harga;
            id = Integer.valueOf(txtId.getText());
            nama = txtNama.getText();
            brand = txtBrand.getText();
            spek = txtSpek.getText();
            harga = Integer.valueOf(txtHarga.getText());
            String query = "update smartphone set " + "nama='" + nama + "', " + "brand='" + brand + "',"
                    + "spesifikasi='" + spek + "'," + "harga='" + harga + "' " + "where id_smartphone='" + id + "'";
            try {
                MainUI.koneksi.update(query);
                JOptionPane.showMessageDialog(null, "Data telah tersimpan");
                setVisible(false);
                parent.refreshTable();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Kesalahan Query, data gagal disimpan");
            }
        }
    }

    private class BtnBatalClick implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            setVisible(false);
        }
    }

}