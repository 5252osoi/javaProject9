package academyManager;

public class TeacherVO {
	private int idx;
	private String name;
	private int age;
	private String phone;
	private String address;
	private int curri1;
	private int curri2;
	private int pay;
	
	int getIdx() {
		return idx;
	}
	void setIdx(int idx) {
		this.idx = idx;
	}
	String getName() {
		return name;
	}
	void setName(String name) {
		this.name = name;
	}
	int getAge() {
		return age;
	}
	void setAge(int age) {
		this.age = age;
	}
	String getPhone() {
		return phone;
	}
	void setPhone(String phone) {
		this.phone = phone;
	}
	String getAddress() {
		return address;
	}
	void setAddress(String address) {
		this.address = address;
	}
	int getCurri1() {
		return curri1;
	}
	void setCurri1(int curri1) {
		this.curri1 = curri1;
	}
	int getCurri2() {
		return curri2;
	}
	void setCurri2(int curri2) {
		this.curri2 = curri2;
	}
	int getPay() {
		return pay;
	}
	void setPay(int pay) {
		this.pay = pay;
	}
	
	
	@Override
	public String toString() {
		return "TeacherVO [idx=" + idx + ", name=" + name + ", age=" + age + ", phone=" + phone + ", address=" + address
				+ ", curri1=" + curri1 + ", curri2=" + curri2 + ", pay=" + pay + "]";
	}
	
	
}
