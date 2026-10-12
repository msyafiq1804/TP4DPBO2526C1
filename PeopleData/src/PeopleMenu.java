import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class PeopleMenu extends JFrame {
    private JPanel mainPanel;
    private JLabel titleLabel;
    private JTextField idField;
    private JTextField namaField;
    private JTextField tahunLahirField;
    private JComboBox<String> kategoriComboBox;
    private JRadioButton rawatInapRadio;
    private JRadioButton rawatJalanRadio;
    private JButton addUpdateButton;
    private JButton cancelButton;
    private JButton deleteButton;
    private JTable personTable;

    private ButtonGroup statusGroup;
    private ArrayList<Person> listPerson;
    private int selectedIndex = -1;

    public static void main(String[] args) {
        PeopleMenu menu = new PeopleMenu();
        menu.setSize(750, 500);
        menu.setLocationRelativeTo(null);
        menu.setContentPane(menu.mainPanel);
        menu.getContentPane().setBackground(Color.WHITE);
        menu.setVisible(true);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public PeopleMenu() {
        listPerson = new ArrayList<>();
        populateList();
        personTable.setModel(setTable());

        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 20f));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        String[] kategoriData = {"???", "BPJS", "Umum", "Asuransi"};
        kategoriComboBox.setModel(new DefaultComboBoxModel<>(kategoriData));

        statusGroup = new ButtonGroup();
        statusGroup.add(rawatInapRadio);
        statusGroup.add(rawatJalanRadio);
        rawatJalanRadio.setSelected(true);

        deleteButton.setVisible(false);

        addUpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedIndex == -1) insertData();
                else updateData();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null,
                        "Yakin ingin menghapus data pasien ini?",
                        "Konfirmasi Hapus",
                        JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    deleteData();
                }
            }
        });

        personTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                selectedIndex = personTable.getSelectedRow();
                idField.setText(personTable.getModel().getValueAt(selectedIndex, 1).toString());
                namaField.setText(personTable.getModel().getValueAt(selectedIndex, 2).toString());
                tahunLahirField.setText(personTable.getModel().getValueAt(selectedIndex, 3).toString());
                kategoriComboBox.setSelectedItem(personTable.getModel().getValueAt(selectedIndex, 4).toString());

                String curStatus = personTable.getModel().getValueAt(selectedIndex, 5).toString();
                if (curStatus.equals("Rawat Inap")) rawatInapRadio.setSelected(true);
                else rawatJalanRadio.setSelected(true);

                addUpdateButton.setText("Update");
                deleteButton.setVisible(true);
            }
        });
    }

    public void populateList() {
        // 2 Data Awal
        listPerson.add(new Person("PSN001", "Wowo Dodo", 1990, "BPJS", "Rawat Jalan"));
        listPerson.add(new Person("PSN002", "Siti Aisyah", 1985, "Umum", "Rawat Inap"));
        listPerson.add(new Person("PSN003", "Andi Purnomo", 1992, "Asuransi", "Rawat Jalan"));
        listPerson.add(new Person("PSN004", "Cristiano Ronaldo", 1988, "BPJS", "Rawat Inap"));
        listPerson.add(new Person("PSN005", "Bambang Pamungkas", 1975, "Umum", "Rawat Jalan"));
        listPerson.add(new Person("PSN006", "Eli Lestari", 1995, "BPJS", "Rawat Inap"));
        listPerson.add(new Person("PSN007", "Fajar Setiawan", 2001, "Asuransi", "Rawat Jalan"));
        listPerson.add(new Person("PSN008", "Sari Indah", 1982, "Umum", "Rawat Inap"));
    }

    public DefaultTableModel setTable() {
        Object[] cols = {"No", "ID", "Nama", "Tahun Lahir", "Kategori", "Status Rawat"};
        DefaultTableModel tmp = new DefaultTableModel(null, cols);
        for (int i = 0; i < listPerson.size(); i++) {
            Object[] row = {i + 1, listPerson.get(i).getId(), listPerson.get(i).getNama(),
                    listPerson.get(i).getTahunLahir(), listPerson.get(i).getKategori(), listPerson.get(i).getStatusRawat()};
            tmp.addRow(row);
        }
        return tmp;
    }

    public void insertData() {
        try {
            String status = rawatInapRadio.isSelected() ? "Rawat Inap" : "Rawat Jalan";
            listPerson.add(new Person(idField.getText(), namaField.getText(),
                    Integer.parseInt(tahunLahirField.getText()), kategoriComboBox.getSelectedItem().toString(), status));
            personTable.setModel(setTable());
            clearForm();
            JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan!");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Tahun Lahir harus angka!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updateData() {
        try {
            String status = rawatInapRadio.isSelected() ? "Rawat Inap" : "Rawat Jalan";
            listPerson.get(selectedIndex).setId(idField.getText());
            listPerson.get(selectedIndex).setNama(namaField.getText());
            listPerson.get(selectedIndex).setTahunLahir(Integer.parseInt(tahunLahirField.getText()));
            listPerson.get(selectedIndex).setKategori(kategoriComboBox.getSelectedItem().toString());
            listPerson.get(selectedIndex).setStatusRawat(status);

            personTable.setModel(setTable());
            clearForm();
            JOptionPane.showMessageDialog(null, "Data berhasil diubah!");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Tahun Lahir harus angka!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deleteData() {
        listPerson.remove(selectedIndex);
        personTable.setModel(setTable());
        clearForm();
        JOptionPane.showMessageDialog(null, "Data berhasil dihapus!");
    }

    public void clearForm() {
        idField.setText("");
        namaField.setText("");
        tahunLahirField.setText("");
        kategoriComboBox.setSelectedIndex(0);
        rawatJalanRadio.setSelected(true);
        addUpdateButton.setText("Add");
        deleteButton.setVisible(false);
        selectedIndex = -1;
    }
}