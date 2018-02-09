package cse12pa5student;

import java.util.List;

public class Counterexample {
	public final List<Item> original, notActuallySorted;

	public Counterexample(List<Item> original, List<Item> notActuallySorted) {
		this.original = original;
		this.notActuallySorted = notActuallySorted;
	}

	@Override
	public String toString() {
		return "CounterExample [original=" + original + ", notActuallySorted=" + notActuallySorted + "]";
	}
	
}
