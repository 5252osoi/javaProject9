package academyManager;

public class StudentVO {
	private int idx;
	private String name;
	private int	age;
	private String phone;
	private String famphone;
	private String address;
	private String school;
	private int curri1;
	private int curri2;
	private int curri3;
	private int fee;
	
	
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
	String getFamphone() {
		return famphone;
	}
	void setFamphone(String famphone) {
		this.famphone = famphone;
	}
	String getAddress() {
		return address;
	}
	void setAddress(String address) {
		this.address = address;
	}
	String getSchool() {
		return school;
	}
	void setSchool(String school) {
		this.school = school;
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
	int getCurri3() {
		return curri3;
	}
	void setCurri3(int curri3) {
		this.curri3 = curri3;
	}
	int getFee() {
		return fee;
	}
	void setFee(int fee) {
		this.fee = fee;
	}
	
	@Override
	public String toString() {
		return "StudentVO [idx=" + idx + ", name=" + name + ", age=" + age + ", phone=" + phone + ", famphone="
				+ famphone + ", address=" + address + ", school=" + school + ", curri1=" + curri1 + ", curri2=" + curri2
				+ ", curri3=" + curri3 + ", fee=" + fee + "]";
	}
	
	
}
