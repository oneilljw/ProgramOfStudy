package ProgramOfStudy;

public enum Concentration
{
	InformationSystems (0),
	WebDevelopment (1),
	ComputerScience (2),
	Database (3),
	Networks (4),
	SoftwareEngineering (5);
	
	private final int index;
	
	Concentration(int index)
	{
		this.index = index;
	}
	
	public int index() { return index; }
}
