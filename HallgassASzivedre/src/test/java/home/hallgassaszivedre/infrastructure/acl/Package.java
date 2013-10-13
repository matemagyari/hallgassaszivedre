package home.hallgassaszivedre.infrastructure.acl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.google.appengine.repackaged.com.google.common.collect.Lists;
import com.google.appengine.repackaged.com.google.common.collect.Sets;

public class Package {

    private final List<Package> children = Lists.newArrayList();
    private final Set<PackageReference> ownPackageReferences = Sets.newHashSet();
    private final String name;

    public Package(String name, Set<PackageReference> packageReferences) {
        this.name = name;
        this.ownPackageReferences.addAll(packageReferences);
    }

    public Package(String name) {
        this(name, new HashSet<PackageReference>());
    }

    void insert(Package aPackage) {
        if (notUnderActual(aPackage)) {
            throw new RuntimeException(aPackage + " is not under " + this);
        }
        if (isLeafPackage(aPackage)) {
            children.add(aPackage);
        } else {
            String relativeName = aPackage.name.replaceFirst(this.name + ".", "");
            String relativeNameOfDirectChild = relativeName.split("\\.", 2)[0];
			Package directChild = getChild(relativeNameOfDirectChild);
            if (directChild == null) {
                directChild = new Package(this.name + "." + relativeNameOfDirectChild);
                children.add(directChild);
            }
            directChild.insert(aPackage);
        }

    }

    private boolean isLeafPackage(Package aPackage) {
        String subName = aPackage.name.replaceFirst(this.name + ".", "");
        return !subName.contains(".");
    }
	private boolean notUnderActual(Package aPackage) {
		return !aPackage.name.startsWith(this.name+".");
	}

    Set<Cycle> detectCyclesBelow() {

        Set<Cycle> cycles = Sets.newHashSet();

        for (Package child : children) {
            cycles.addAll(child.detectCyclesBelow());
        }

        Set<Cycle> cyclesBetweenChildren = detectCyclesBetweenChildren();
        cycles.addAll(cyclesBetweenChildren);
        return cycles;
    }

    private Set<Cycle> detectCyclesBetweenChildren() {
        Set<Cycle> cyclesAmongChildren = Sets.newHashSet();
        for (Package child : children) {
            List<Package> siblings = Lists.newArrayList(children);
            siblings.remove(child);
            //siblings.add(this);

            for (Package sibling : siblings) {
                if (sibling.referredByEachOther(child)) {
                    cyclesAmongChildren.add(new Cycle(child, sibling));
                }
            }
        }
        return cyclesAmongChildren;
    }
    

	public List<GCycle> detectCycles(List<Package> mySiblings) {
		Set<PackageReference> packageReferences = this.getPackageReferences();
		
		return null;
	}

    private Set<PackageReference> getPackageReferences() {
        Set<PackageReference> packageReferences = Sets.newHashSet();
        for (Package child : children) {
            packageReferences.addAll(child.getPackageReferences());
        }
        packageReferences.addAll(ownPackageReferences);

        return packageReferences;
    }

    private boolean referredByEachOther(Package that) {
        boolean thisReferToThat = false;
        boolean thatReferToThis = false;
        for (PackageReference packageReference : that.getPackageReferences()) {
            if (packageReference.startsWith(name)) {
                thatReferToThis = true;
                continue;
            }
        }
        for (PackageReference packageReference : this.getPackageReferences()) {
            if (packageReference.startsWith(that.name)) {
                thisReferToThat = true;
                continue;
            }
        }
        return thisReferToThat && thatReferToThis;
    }

    private Package getChild(String relativeName) {
        for (Package child : children) {
            if (child.name.equals(this.name + "." + relativeName)) {
                return child;
            }
        }
        return null;
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
