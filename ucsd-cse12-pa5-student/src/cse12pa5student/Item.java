package cse12pa5student;

public class Item {

	public final String name;
	public final int priceInCents;
	public final int weightInGrams;
	
	public Item(String name, int priceInCents, int weightInGrams) {
		if(priceInCents < 0) { throw new IllegalArgumentException("Negative price"); }
		if(weightInGrams < 0) { throw new IllegalArgumentException("Negative weightInGrams"); }
		this.name = name;
		this.priceInCents = priceInCents;
		this.weightInGrams = weightInGrams;
	}	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (!name.equals(other.name))
			return false;
		if (priceInCents != other.priceInCents)
			return false;
		if (weightInGrams != other.weightInGrams)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Item [name=" + name + ", priceInCents=" + priceInCents + ", weightInGrams=" + weightInGrams + "]";
	}
	
	
}
