package home.hallgassaszivedre.infrastructure.acl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import jdepend.framework.JDepend;
import jdepend.framework.JavaPackage;

import org.junit.Test;

import com.google.appengine.labs.repackaged.com.google.common.collect.Lists;
import com.google.appengine.repackaged.com.google.common.collect.Sets;

public class DesignTest {
    
    static final String BASE_PACKAGE = "home.hallgassaszivedre";
    
    
    static Package BASE_PACKAGE_;

    
    @SuppressWarnings("unchecked")
    @Test
    public void dddLayersAreIntact() throws IOException {
        
        JDepend jDepend = new JDepend();
        jDepend.addDirectory("./target/classes");
        
        jDepend.addPackage(BASE_PACKAGE);
        Collection<JavaPackage> packages = jDepend.analyze();
        
        PackageStructureBuilder packageStructureBuilder = new PackageStructureBuilder(BASE_PACKAGE);
        Package basePackage = packageStructureBuilder.build(packages);
        // assert
        BASE_PACKAGE_ = basePackage;
        
        
        Set<Cycle> checkCycles = basePackage.detectCyclesBelow();
        for (Cycle cycle : checkCycles) {
            System.err.println(cycle);
        }
        
        List<Cycle> cycles = Lists.newArrayList(checkCycles);
        
        Set<Cycle> allCycles = Sets.newHashSet();
        
		List<Package> flatten = basePackage.flatten();
		
		for (Package aPackage : flatten) {
			Set<Cycle> aCycles = aPackage.detectCyclesBelow();
			System.err.println("Cycles under " + aPackage + " " + aCycles.size());
			for (Cycle cycle : aCycles) {
				System.err.println(cycle);
			}
			allCycles.addAll(aCycles);
		}
		
		//List<Cycle> detectCycles = basePackage.detectCycles(new ArrayList<PackageReference>(), new ArrayList<Cycle>());
		//System.err.println(detectCycles);
    }




}
