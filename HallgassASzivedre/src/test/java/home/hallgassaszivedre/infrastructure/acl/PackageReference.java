package home.hallgassaszivedre.infrastructure.acl;

public class PackageReference {
	
	private final String name;
	

	public PackageReference(String name) {
		this.name = name;
	}


	public boolean startsWith(String str) {
		return name.startsWith(str);
	}

}
