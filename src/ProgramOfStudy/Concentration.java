package ProgramOfStudy;

public enum Concentration
{
	InformationSystems (0, "Information Systems"),
	WebDevelopment (1, "Web Development"),
	ComputerScience (2, "Computer Science"),
	Database (3,"Database"),
	Networks (4,"Networks"),
	SoftwareEngineering(5,"Software Engineering");
	
	private final int index;
	private final String englishName;
	
	Concentration(int index, String englishName)
	{
		this.index = index;
		this.englishName = englishName;
	}
	
	public int index() { return index; }
	String englishName() { return englishName; }
}
