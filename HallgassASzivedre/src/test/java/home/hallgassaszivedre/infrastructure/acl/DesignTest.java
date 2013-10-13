package home.hallgassaszivedre.infrastructure.acl;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import jdepend.framework.JDepend;
import jdepend.framework.JavaPackage;

import org.junit.Assert;
import org.junit.Test;

import com.google.appengine.labs.repackaged.com.google.common.base.Predicate;
import com.google.appengine.labs.repackaged.com.google.common.collect.Iterables;
import com.google.appengine.labs.repackaged.com.google.common.collect.Lists;
import com.google.appengine.repackaged.com.google.common.collect.Sets;

public class DesignTest {
    
    static final String BASE_PACKAGE = "home.hallgassaszivedre";

    
    @SuppressWarnings("unchecked")
    @Test
    public void dddLayersAreIntact() throws IOException {
        
        JDepend jDepend = new JDepend();
        jDepend.addDirectory("./target/classes");
        
        jDepend.addPackage(BASE_PACKAGE);
        Collection<JavaPackage> packages = jDepend.analyze();
        
        List<JavaPackage> sortedPackages = sortByName(packages);
       
        JavaPackage baseJavaPackage =  sortedPackages.remove(0);
        
        Package basePackage = new JDependBasedPackage(baseJavaPackage);
        
        for (JavaPackage javaPackage : sortedPackages) {
            basePackage.insert(new JDependBasedPackage(javaPackage));
        }
        
        // assert
        
        Set<Cycle> checkCycles = basePackage.detectCyclesBelow();
        for (Cycle cycle : checkCycles) {
            System.err.println(cycle);
        }
        
        List<Cycle> cycles = Lists.newArrayList(checkCycles);
        
        Set<Cycle> allCycles = Sets.newHashSet();
        
		List<Package> flatten = basePackage.flatten();
		
		for (Package aPackage : flatten) {
			Set<Cycle> aCycles = aPackage.detectCyclesBelow();
			System.err.println("\nCycles under " + aPackage + " " + aCycles.size());
			for (Cycle cycle : aCycles) {
				System.err.println(cycle);
			}
			allCycles.addAll(aCycles);
		}
		System.err.println(allCycles.size());
        
    }


    private List<JavaPackage> sortByName(Collection<JavaPackage> packages) {
        Comparator<JavaPackage> comparator = new Comparator<JavaPackage>() {

            @Override
            public int compare(JavaPackage p1, JavaPackage p2) {
                return p1.getName().compareTo(p2.getName());
            }};
        // act
        List<JavaPackage> sortedPackages = Lists.newArrayList(packages);
        Collections.sort(sortedPackages, comparator);
        
        
        
        Predicate<JavaPackage> filter = new Predicate<JavaPackage>() {
            
            @Override
            public boolean apply(JavaPackage input) {
                return input.getName().startsWith(BASE_PACKAGE);
            }
        };
        return Lists.newArrayList(Iterables.filter(sortedPackages, filter));
    }

}
