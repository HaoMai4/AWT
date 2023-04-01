package onThiGiuaKi;

import java.util.ArrayList;
import java.util.List;

public class quanLySach {
	private List<SACH> ls;

	public quanLySach() {
		ls = new ArrayList<SACH>();
	}

	public List<SACH> getLs() {
		return ls;
	}

	public boolean themSach(SACH s) {
		if (!ls.contains(s)) {
			return ls.add(s);
		} else
			return false;
	}

	public boolean xoaSach(SACH s) {
		return ls.remove(s);
	}

	public SACH timSach(String s) {
		for (SACH sach : ls) {
			if (sach.getMaSach().equals(s)) {
				return sach;
			}
		}

		return null;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
