package home.hallgassaszivedre.infrastructure.acl;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.google.common.collect.Sets;

public class GCycle {
    
    private final Package packageA;
    private final Package packageB;
    
    public GCycle(Package packageA, Package packageB) {
        this.packageA = packageA;
        this.packageB = packageB;
    }

    @Override
    public String toString() {
        return packageA + "<-->" + packageB;
    }
    
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof GCycle)) {
            return false;
        }
        GCycle castOther = (GCycle) other;
        
        return Sets.newHashSet(packageA, packageB).equals(Sets.newHashSet(castOther.packageA, castOther.packageB));
    }

    @Override
    public int hashCode() {
        String hash = packageA.toString()+"_"+packageB.toString();
        return new HashCodeBuilder()
                        .append(hash.length())
                        .hashCode();
    }

}
