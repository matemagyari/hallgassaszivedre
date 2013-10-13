package home.hallgassaszivedre.infrastructure.acl;

import java.util.List;

import com.google.appengine.repackaged.com.google.common.collect.Lists;

public class SiblingCycleDetector {

	List<GCycle> detectCycles(List<Package> siblings) {

		List<GCycle> cycles = Lists.newArrayList();

		for (Package sibling : siblings) {

			List<Package> otherSiblings = Lists.newArrayList(siblings);
			otherSiblings.remove(sibling);

			List<GCycle> cyclesFromSibling = detectCyclesStartingFrom(sibling,
					otherSiblings);

			cycles.addAll(cyclesFromSibling);
		}

		return cycles;
	}

	private List<GCycle> detectCyclesStartingFrom(Package sibling,
			List<Package> otherSiblings) {
		
		return sibling.detectCycles(otherSiblings);
	}

}
