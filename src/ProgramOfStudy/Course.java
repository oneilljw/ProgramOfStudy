package ProgramOfStudy;

public class Course 
{
	private int semester;
	private String department;
	private int number;
	private int creditHours;
	private String title;
	
	public Course(int semester, String department, int number, int creditHours, String title)
	{
		this.semester = semester;
		this.department = department;
		this.number = number;
		this.creditHours = creditHours;
		this.title = title;
	}

	/**
	 * @return the semester
	 */
	int getSemester() {
		return semester;
	}

	/**
	 * @return the department
	 */
	String getDepartment() {
		return department;
	}

	/**
	 * @return the number
	 */
	int getNumber() {
		return number;
	}

	/**
	 * @return the creditHours
	 */
	int getCreditHours() {
		return creditHours;
	}

	/**
	 * @return the title
	 */
	String getTitle() {
		return title;
	}

	/**
	 * @param semester the semester to set
	 */
	void setSemester(int semester) {
		this.semester = semester;
	}

	/**
	 * @param department the department to set
	 */
	void setDepartment(String department) {
		this.department = department;
	}

	/**
	 * @param number the number to set
	 */
	void setNumber(int number) {
		this.number = number;
	}

	/**
	 * @param creditHours the creditHours to set
	 */
	void setCreditHours(int creditHours) {
		this.creditHours = creditHours;
	}

	/**
	 * @param title the title to set
	 */
	void setTitle(String title) {
		this.title = title;
	}
	
	
}
