package home.hallgassaszivedre.infrastructure.acl;

import org.apache.commons.lang3.builder.HashCodeBuilder;

public class PackageReference implements Comparable<PackageReference> {

    private final String name;

    public PackageReference(String name) {
        this.name = name;
    }

    public boolean startsWith(String str) {
        return name.startsWith(str);
    }

    public boolean notUnderMe(PackageReference reference) {
        return !reference.name.startsWith(this.name + ".");
    }
    
    public String relativeNameTo(PackageReference reference) {
        return this.name.replaceFirst(reference.name + ".", "");
    }
    

    public boolean isDirectChildOfMe(PackageReference reference) {
        return !reference.relativeNameTo(this).contains(".");
    }

    public String firstPartOfRelativeNameTo(PackageReference reference) {
        return this.relativeNameTo(reference).split("\\.", 2)[0];
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof PackageReference)) {
            return false;
        }
        PackageReference castOther = (PackageReference) other;

        return name.equals(castOther.name);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(name).hashCode();
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(PackageReference that) {
        return name.compareTo(that.name);
    }


}
