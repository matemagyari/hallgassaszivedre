package home.hallgassaszivedre.infrastructure.acl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.google.appengine.repackaged.com.google.common.collect.Lists;
import com.google.appengine.repackaged.com.google.common.collect.Sets;

public class Package {

	private final List<Package> children = Lists.newArrayList();
	private final Set<PackageReference> ownPackageReferences;
	private final String name;

	public Package(String name, Set<PackageReference> packageReferences) {
		this.name = name;
		this.ownPackageReferences = Sets.newHashSet((packageReferences));
	}

	public Package(String name) {
		this(name, new HashSet<PackageReference>());
	}

	private PackageReference getReference() {
		return new PackageReference(name);
	}

	void insert(Package aPackage) {
		if (notUnderMe(aPackage)) {
			throw new RuntimeException(aPackage + " is not under " + this);
		}
		if (isDirectChildOfMe(aPackage)) {
			children.add(aPackage);
		} else {
			insertIndirectChild(aPackage);
		}

	}

	private void insertIndirectChild(Package aPackage) {
		String relativeNameOfDirectChild = aPackage.firstPartOfRelativeNameTo(this);
		Package directChild = getChild(relativeNameOfDirectChild);
		if (directChild == null) {
			directChild = new Package(this.name + "." + relativeNameOfDirectChild);
			children.add(directChild);
		}
		directChild.insert(aPackage);
	}

	private String firstPartOfRelativeNameTo(Package parentPackage) {
		String relativeName = this.name.replaceFirst(parentPackage.name + ".", "");
		return relativeName.split("\\.", 2)[0];
	}

	private boolean isDirectChildOfMe(Package aPackage) {
		String subName = aPackage.name.replaceFirst(this.name + ".", "");
		return !subName.contains(".");
	}

	private boolean notUnderMe(Package aPackage) {
		return !aPackage.name.startsWith(this.name + ".");
	}

	Set<Cycle> detectCyclesBelow() {

		Set<Cycle> cycles = Sets.newHashSet();

		for (Package child : children) {
			cycles.addAll(child.detectCyclesBelow());
			
			if (child.explicitlyRefersTo(this) && this.explicitlyRefersTo(child)) {
				cycles.add(new Cycle(this.getReference(), child.getReference()));
			}
		}

		cycles.addAll(detectCyclesBetweenChildren());
		return cycles;
	}

	private Set<Cycle> detectCyclesBetweenChildren() {
		Set<Cycle> cyclesAmongChildren = Sets.newHashSet();
		for (Package child : children) {
			
			List<Cycle> cycles = child.detectCycles(children, new ArrayList<PackageReference>(), new ArrayList<Cycle>());
			cyclesAmongChildren.addAll(cycles);
		}
		return cyclesAmongChildren;
	}

	private List<Cycle> detectCycles(List<Package> relevantPackages, List<PackageReference> traversedPackages, List<Cycle> foundCycles) {

		if (traversedPackages.contains(this.getReference())) {
			foundCycles.add(new Cycle(traversedPackages));

			return foundCycles;
		}
		for (Package relevantPackage : relevantPackages) {
			if (this.refersTo(relevantPackage)) {

				List<PackageReference> updatedTraversedPackages = Lists.newArrayList(traversedPackages);
				updatedTraversedPackages.add(this.getReference());

				List<Cycle> cycles = relevantPackage.detectCycles(relevantPackages, updatedTraversedPackages, foundCycles);
				foundCycles.addAll(cycles);
			}
		}

		return foundCycles;
	}

	private boolean refersTo(Package aPackage) {
		if (this.equals(aPackage)) {
			return false;
		}

		for (PackageReference reference : this.accumulatedPackageReferences()) {
			if (aPackage.pointsInside(reference)) {
				return true;
			}
		}
		return false;
	}

	private boolean pointsInside(PackageReference reference) {
		return reference.startsWith(name);
	}

	private boolean explicitlyRefersTo(Package aPackage) {
		for (PackageReference reference : this.ownPackageReferences) {
			if (reference.equals(aPackage.getReference())) {	
				return true;
			}
		}
		return false;
	}
	public Set<PackageReference> accumulatedPackageReferences() {
		Set<PackageReference> packageReferences = Sets.newHashSet();
		for (Package child : children) {
			packageReferences.addAll(child.accumulatedPackageReferences());
		}
		packageReferences.addAll(ownPackageReferences);

		return packageReferences;
	}

	private Package getChild(String relativeName) {
		for (Package child : children) {
			if (child.name.equals(this.name + "." + relativeName)) {
				return child;
			}
		}
		return null;
	}
	
	List<Package> flatten() {
		List<Package> flattenedHierarchy = Lists.newArrayList();
		flattenedHierarchy = Lists.newArrayList(this);
		
		for (Package child : children) {
			flattenedHierarchy.addAll(child.flatten());
			
		}
		return flattenedHierarchy;
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Package)) {
			return false;
		}
		Package castOther = (Package) other;
		return new EqualsBuilder().append(name, castOther.name).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(name).hashCode();
	}

	@Override
	public String toString() {
		return this.name;
	}

}
