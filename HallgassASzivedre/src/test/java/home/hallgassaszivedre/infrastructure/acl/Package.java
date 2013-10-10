package home.hallgassaszivedre.infrastructure.acl;

import java.util.List;
import java.util.Set;

import com.google.appengine.repackaged.com.google.common.collect.Lists;
import com.google.appengine.repackaged.com.google.common.collect.Sets;

public class Package {

	private final List<Package> children = Lists.newArrayList();
	private final Set<PackageReference> ownPackageReferences = Sets
			.newHashSet();
	private final String name;

	public Package(String name, Set<PackageReference> packageReferences) {
		this.name = name;
		this.ownPackageReferences.addAll(packageReferences);
	}

	Set<PackageReference> getPackageReferences() {
		Set<PackageReference> packageReferences = Sets.newHashSet();
		for (Package child : children) {
			packageReferences.addAll(child.getPackageReferences());
		}
		packageReferences.addAll(ownPackageReferences);
		return packageReferences;
	}

	boolean checkCycles() {

		Set<PackageReference> referredPackages = getPackageReferences();
		if (referredPackages.isEmpty()) {
			return false;
		}

		for (Package child : children) {
			if (child.checkCycles()) {
				return true;
			}
		}

		for (Package child : children) {
			List<Package> siblings = Lists.newArrayList(children);
			siblings.remove(child);

			for (Package sibling : siblings) {
				if (sibling.isReferredBy(child)) {
					return true;
				}
			}
		}

		return false;
	}

	boolean isReferredBy(Package aPackage) {
		for (PackageReference packageReference : aPackage
				.getPackageReferences()) {
			if (packageReference.startsWith(name)) {
				return true;
			}
		}
		return false;
	}

	void insert(Package aPackage) {
		if (!aPackage.name.startsWith(this.name)) {
			throw new RuntimeException(aPackage + " is not under " + this);
		}
		if (isLeafPackage(aPackage)) {
			children.add(aPackage);
		} else {
			String[] split = aPackage.name.split(".", 2);
			Package subPackage = getSubPackage(split[0]);
			if (subPackage == null) {
				children.add(aPackage);
			} else {
				subPackage.insert(aPackage);
			}
		}

	}

	private Package getSubPackage(String relativeName) {
		for (Package child : children) {
			if (child.name.equals(this.name + "." + relativeName)) {
				return child;
			}
		}
		return null;
	}

	private boolean isLeafPackage(Package aPackage) {
		String subName = aPackage.name.replaceFirst(this.name, "");
		return subName.contains(".");
	}

	@Override
	public String toString() {
		return this.name;
	}
}
