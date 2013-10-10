package home.hallgassaszivedre.infrastructure.acl;

import java.util.Collection;
import java.util.Set;

import jdepend.framework.JavaPackage;

import com.google.appengine.repackaged.com.google.common.collect.Sets;

public class JDependBasedPackage extends Package {
	
	private static final String BASE_PACKAGE = "gamesys.poker.member";

    public JDependBasedPackage(JavaPackage javaPackage) {
        super(javaPackage.getName(), getOwnPackageReferences(javaPackage));
    }

    @SuppressWarnings("unchecked")
    private static Set<PackageReference> getOwnPackageReferences(JavaPackage theJavaPackage) {

        Set<PackageReference> packages = Sets.newHashSet();

        Collection<JavaPackage> efferents = theJavaPackage.getEfferents();
        for (JavaPackage javaPackage : efferents) {
            if (javaPackage.getName().startsWith(BASE_PACKAGE)) {
                packages.add(new PackageReference(javaPackage.getName()));
            }
        }

        return packages;
    }

}
