package onThiGiuaKi;

import java.util.Objects;

public class SACH {
	private String maSach;
	private String tenSach;
	private int soTrang;
	private String theLoai;
	private String nhaXuatBan;

	public SACH(String maSach, String tenSach, int soTrang, String theLoai, String nhaXuatBan) throws Exception {
		setMaSach(maSach);
		setTenSach(tenSach);
		setSoTrang(soTrang);
		setTheLoai(theLoai);
		setNhaXuatBan(nhaXuatBan);
	}

	@Override
	public int hashCode() {
		return Objects.hash(maSach);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SACH other = (SACH) obj;
		return Objects.equals(maSach, other.maSach);
	}

	public String getMaSach() {
		return maSach;
	}

	public String getTenSach() {
		return tenSach;
	}

	public int getSoTrang() {
		return soTrang;
	}

	public String getTheLoai() {
		return theLoai;
	}

	public String getNhaXuatBan() {
		return nhaXuatBan;
	}

	public void setMaSach(String maSach) throws Exception {
		if (!maSach.equalsIgnoreCase("")) {
			this.maSach = maSach;
		} else
			throw new Exception("Mã sách không được rỗng");
	}

	public void setTenSach(String tenSach) throws Exception {
		if (!tenSach.equalsIgnoreCase("")) {
			this.tenSach = tenSach;
		} else
			throw new Exception("Tên sách không được rỗng");
	}

	public void setSoTrang(int soTrang) throws Exception {
		if (soTrang > 0) {
			this.soTrang = soTrang;
		} else
			throw new Exception("Số trang phải lớn hơn 0");
	}

	public void setTheLoai(String theLoai) throws Exception {
		if (!theLoai.equalsIgnoreCase("")) {
			this.theLoai = theLoai;
		} else
			throw new Exception("Thể loại không được rỗng");
	}

	public void setNhaXuatBan(String nhaXuatBan) throws Exception {
		if (!nhaXuatBan.equalsIgnoreCase("")) {
			this.nhaXuatBan = nhaXuatBan;
		} else
			throw new Exception("Nhà xuất bản không được rỗng");
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
