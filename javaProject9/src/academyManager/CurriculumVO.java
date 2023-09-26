package academyManager;

public class CurriculumVO {
	
	private int idx;
	private String name;
	private String curriculumStart;
	private String curriculumEnd;
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
	String getCurriculumStart() {
		return curriculumStart;
	}
	void setCurriculumStart(String curriculumStart) {
		this.curriculumStart = curriculumStart;
	}
	String getCurriculumEnd() {
		return curriculumEnd;
	}
	void setCurriculumEnd(String curriculumEnd) {
		this.curriculumEnd = curriculumEnd;
	}
	int getFee() {
		return fee;
	}
	void setFee(int fee) {
		this.fee = fee;
	}
	@Override
	public String toString() {
		return "CurriculumVO [idx=" + idx + ", name=" + name + ", curriculumStart=" + curriculumStart
				+ ", curriculumEnd=" + curriculumEnd + ", fee=" + fee + "]";
	}
	
	
	
}
