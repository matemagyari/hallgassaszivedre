package home.hallgassaszivedre.infrastructure.acl;

import java.util.Collection;
import java.util.Set;

import jdepend.framework.JavaPackage;

import com.google.appengine.repackaged.com.google.common.collect.Sets;

public class JDependBasedPackage extends Package {
	
    private Set<PackageReference> ownPackageReferences;

	public JDependBasedPackage(JavaPackage javaPackage) {
        super(javaPackage.getName());
        this.ownPackageReferences =  createOwnPackageReferences(javaPackage);
    }
	
	@Override
	protected Set<PackageReference> getOwnPackageReferences() {
		return ownPackageReferences;
	}

    @SuppressWarnings("unchecked")
    protected static Set<PackageReference> createOwnPackageReferences(JavaPackage theJavaPackage) {

        Set<PackageReference> packages = Sets.newHashSet();

        Collection<JavaPackage> efferents = theJavaPackage.getEfferents();
        for (JavaPackage javaPackage : efferents) {
            if (javaPackage.getName().startsWith(DesignTest.BASE_PACKAGE)) {
                packages.add(new PackageReference(javaPackage.getName()));
            }
        }

        return packages;
    }

}
