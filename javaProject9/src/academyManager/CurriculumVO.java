package academyManager;

public class CurriculumVO {
	
	private int idx;
	private String name;
	private String curriculumStart;
	private String curriculumEnd;
	private int fee;
	
	private String newName;

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCurriculumStart() {
		return curriculumStart;
	}

	public void setCurriculumStart(String curriculumStart) {
		this.curriculumStart = curriculumStart;
	}

	public String getCurriculumEnd() {
		return curriculumEnd;
	}

	public void setCurriculumEnd(String curriculumEnd) {
		this.curriculumEnd = curriculumEnd;
	}

	public int getFee() {
		return fee;
	}

	public void setFee(int fee) {
		this.fee = fee;
	}

	public String getNewName() {
		return newName;
	}

	public void setNewName(String newName) {
		this.newName = newName;
	}

	@Override
	public String toString() {
		return "CurriculumVO [idx=" + idx + ", name=" + name + ", curriculumStart=" + curriculumStart
				+ ", curriculumEnd=" + curriculumEnd + ", fee=" + fee + ", newName=" + newName + "]";
	}

	
	
}
