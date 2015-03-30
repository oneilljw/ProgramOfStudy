package ProgramOfStudy;

public class StudentCourse extends Course
{
	private char grade;
	
	public StudentCourse(int semester, String department, int number, int creditHours,
							String title, char grade)
	{
		super(semester, department, number, creditHours, title);
		this.grade = grade;
	}
	
	//getters
	char getGrade() { return grade; }
	
	//setters
	void setGrade(char grade) { this.grade = grade; }
}

