package onThiGiuaKi;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class GUI extends JFrame implements ActionListener {
	private JPanel northPanel = new JPanel();
	private JPanel middlePanel = new JPanel();
	private JPanel southPanel = new JPanel();
	private JTextField txtMaSach = new JTextField(20);
	private JTextField txtTenSach = new JTextField(20);
	private JTextField txtSoTrang = new JTextField(20);
	private JTextField txtNXB = new JTextField(20);
	private JTextField txtTim = new JTextField(10);
	private JComboBox<String> boxTheLoai;
	private JButton btnThem = new JButton("Thêm");
	private JButton btnXoaTrang = new JButton("Xóa trắng");
	private JButton btnXoa = new JButton("Xóa");
	private JButton btnLuu = new JButton("Lưu");
	private JButton btnTim = new JButton("Tìm");
	private JTable table;
	private DefaultTableModel model;
	private quanLySach list = new quanLySach();

	public GUI() {
		// North
		JLabel northLabel = new JLabel("THÔNG TIN SÁCH");
		northLabel.setForeground(Color.blue);
		northLabel.setFont(new Font("Arial", Font.BOLD, 20));
		northPanel.add(northLabel);

		// Middle
		middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.Y_AXIS));

		Box b = Box.createVerticalBox();
		Box b1 = Box.createHorizontalBox();
		Box b2 = Box.createHorizontalBox();
		Box b3 = Box.createHorizontalBox();

		JLabel lblMaSach = new JLabel("Mã sách:");
		JLabel lblTenSach = new JLabel("Tên sách:");
		JLabel lblSoTrang = new JLabel("Số trang:");
		JLabel lblTheLoai = new JLabel("Thể loại:");
		JLabel lblNhaXB = new JLabel("Nhà xuất bản:");
		String[] boxValues = { "Toán", "Tin Học", "Ngữ Văn", "Vật Lý" };
		boxTheLoai = new JComboBox<String>(boxValues);

		lblMaSach.setPreferredSize(lblTenSach.getPreferredSize());
		b1.add(Box.createHorizontalStrut(10));
		b1.add(lblMaSach);
		b1.add(txtMaSach);

		b2.add(Box.createHorizontalStrut(10));
		b2.add(lblTenSach);
		b2.add(txtTenSach);
		b2.add(Box.createHorizontalStrut(10));
		b2.add(lblSoTrang);
		b2.add(txtSoTrang);

		lblTheLoai.setPreferredSize(lblTenSach.getPreferredSize());
		b3.add(Box.createHorizontalStrut(10));
		b3.add(lblTheLoai);
		b3.add(boxTheLoai);
		b3.add(lblNhaXB);
		b3.add(txtNXB);

		b.add(Box.createVerticalStrut(10));
		b.add(b1);
		b.add(Box.createVerticalStrut(10));
		b.add(b2);
		b.add(Box.createVerticalStrut(10));
		b.add(b3);
		b.add(Box.createVerticalStrut(10));

		middlePanel.add(b);

		createTable();

		// South
		JLabel lblTim = new JLabel("Nhập mã sách cần tìm");
		southPanel.add(lblTim);
		southPanel.add(txtTim);
		southPanel.add(btnTim);
		southPanel.add(btnThem);
		southPanel.add(btnXoaTrang);
		southPanel.add(btnXoa);
		southPanel.add(btnLuu);

		// Add panels to Frame
		add(northPanel, BorderLayout.NORTH);
		add(middlePanel, BorderLayout.CENTER);
		add(southPanel, BorderLayout.SOUTH);

		// Table handler
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtMaSach.setText(model.getValueAt(table.getSelectedRow(), 0).toString());
				txtTenSach.setText(model.getValueAt(table.getSelectedRow(), 1).toString());
				txtSoTrang.setText(model.getValueAt(table.getSelectedRow(), 2).toString());
				boxTheLoai.setSelectedItem(model.getValueAt(table.getSelectedRow(), 3).toString());
				txtNXB.setText(model.getValueAt(table.getSelectedRow(), 4).toString());
			}
		});
		// Button handler
		btnThem.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		btnXoa.addActionListener(this);
		btnLuu.addActionListener(this);
		btnTim.addActionListener(this);

		// Load from txt
		loadFromTxt();
		refreshTable();
	}

	private void saveToTxt() {
		File f = new File("./src/data/Sach.txt");

		try {
			FileWriter fw = new FileWriter(f);
			if (f.exists()) {
				for (SACH s : list.getLs()) {
					String st = s.getMaSach() + "," + s.getTenSach() + "," + s.getSoTrang() + "," + s.getTheLoai() + ","
							+ s.getNhaXuatBan();

					fw.write(st);
					fw.write("\n");
				}
			} else {
				f.createNewFile();
			}

			fw.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}

	private void loadFromTxt() {
		File f = new File("./src/data/SACH.txt");

		try {
			if (f.exists()) {
				Scanner sc = new Scanner(f);
				while (sc.hasNextLine()) {
					String line = sc.nextLine();
					String[] data = line.split(",");

					list.themSach(new SACH(data[0], data[1], Integer.parseInt(data[2]), data[3], data[4]));
				}

				sc.close();
			} else {
				f.createNewFile();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}

	private void refreshTable() {
		model.setRowCount(0);
		for (SACH s : list.getLs()) {
			Object[] data = { s.getMaSach(), s.getTenSach(), s.getSoTrang(), s.getTheLoai(), s.getNhaXuatBan() };
			model.addRow(data);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if (o.equals(btnThem)) {
			try {
				boolean isNotTrung = list.themSach(
						new SACH(txtMaSach.getText(), txtTenSach.getText(), Integer.parseInt(txtSoTrang.getText()),
								boxTheLoai.getSelectedItem().toString(), txtNXB.getText()));

				if (!isNotTrung) {
					JOptionPane.showMessageDialog(this, "Đã trùng mã sách");
					return;
				}

				JOptionPane.showMessageDialog(this, "Đã thêm thành công");
				saveToTxt();
				refreshTable();
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(this, e2.getMessage());
			}
		} else if (o.equals(btnXoaTrang)) {
			txtMaSach.setText("");
			txtTenSach.setText("");
			txtSoTrang.setText("");
			txtNXB.setText("");
		} else if (o.equals(btnXoa)) {
			if (table.getSelectedRow() != -1) {
				int selection = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa?", "Xác nhận",
						JOptionPane.YES_NO_OPTION);

				if (selection == JOptionPane.YES_OPTION) {
					list.xoaSach(list.timSach((String) model.getValueAt(table.getSelectedRow(), 0)));
					JOptionPane.showMessageDialog(this, "Đã xóa thành công");
					saveToTxt();
					refreshTable();
				}

			} else {
				JOptionPane.showMessageDialog(this, "Phải chọn một dòng để xóa");
			}
		} else if (o.equals(btnLuu)) {
			if (table.getSelectedRow() != -1) {
				try {
					SACH s = list.timSach(model.getValueAt(table.getSelectedRow(), 0).toString());

					s.setMaSach(txtMaSach.getText());
					s.setTenSach(txtTenSach.getText());
					s.setSoTrang(Integer.parseInt(txtSoTrang.getText()));
					s.setTheLoai(boxTheLoai.getSelectedItem().toString());
					s.setNhaXuatBan(txtNXB.getText());

					JOptionPane.showMessageDialog(this, "Đã sửa thành công");
					saveToTxt();
					refreshTable();
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(this, e2.getMessage());
				}
			} else {
				JOptionPane.showMessageDialog(this, "Phải chọn một dòng để sửa");
			}
		} else if (o.equals(btnTim)) {
			if (!txtTim.getText().equalsIgnoreCase("")) {
				int index = list.getLs().indexOf(list.timSach(txtTim.getText()));

				if (index == -1) {
					JOptionPane.showMessageDialog(this, "Mã sách không hợp lệ");
				} else {
					table.setRowSelectionInterval(index, index);
				}
			} else {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập vào mã sách");
			}
		}
	}

	private void createTable() {
		String[] tableCols = { "Mã sách", "Tên sách", "Số trang", "Thể loại", "Nhà xuất bản" };
		model = new DefaultTableModel(tableCols, 0);
		table = new JTable(model);
		JScrollPane tablePane = new JScrollPane(table);

		middlePanel.add(tablePane);
	}
}
