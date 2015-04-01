package ProgramOfStudy;

import java.io.Serializable;

public class StudentCourse extends Course implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4004113882048030689L;
	private char grade;
	
	public StudentCourse(int semester, String department, int number, int creditHours,
							String title, char grade)
	{
		super(semester, department, number, creditHours, title);
		this.grade = grade;
	}
	
	public StudentCourse(Course course)
	{
		super(course.getSemester(), course.getDepartment(), course.getNumber(), 
				course.getCreditHours(), course.getTitle());
		this.grade = 'N';
	}
	
	//getters
	char getGrade() { return grade; }
	
	//setters
	void setGrade(char grade) { this.grade = grade; }
}

