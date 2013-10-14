package home.hallgassaszivedre.infrastructure.acl;

import java.util.Collection;
import java.util.Set;

import jdepend.framework.JavaPackage;

import com.google.appengine.repackaged.com.google.common.collect.Sets;

public class LazyLoadingJDependBasedPackage extends Package {
	
    private JavaPackage javaPackage;

	public LazyLoadingJDependBasedPackage(JavaPackage javaPackage) {
        super(javaPackage.getName());
		this.javaPackage = javaPackage;
    }

    @SuppressWarnings("unchecked")
    protected Set<PackageReference> getOwnPackageReferences() {

        Set<PackageReference> packages = Sets.newHashSet();

        Collection<JavaPackage> efferents = javaPackage.getEfferents();
        for (JavaPackage javaPackage : efferents) {
            if (javaPackage.getName().startsWith(DesignTest.BASE_PACKAGE)) {
                packages.add(new LazyLoadingJDependBasedPackage(javaPackage).getReference());
            }
        }

        return packages;
    }

}
