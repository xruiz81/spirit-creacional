package com.truemesh.squiggle;


import com.truemesh.squiggle.output.Output;

/**
 * @author <a href="joe@truemesh.com">Joe Walnes</a>
 * @author Nat Pryce
 */
public class Alias extends Projection implements Matchable {
	private final String alias;
	
    public Alias(Table table) {
        super(table);
        this.alias = table.getAlias();
    }

    public String getAlias() {
        return alias;
    }

    public void write(Output out) {
        out.print(getAlias());
    }

	public int hashCode() {
		final int prime = 31;
		int result = getTable().hashCode();
		result = prime * result + alias.hashCode();
		return result;
	}
	
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;
		if (getClass() != o.getClass())
			return false;
		
		final Alias that = (Alias)o;
		
		return this.alias.equals(that.alias) 
		    && this.getTable().equals(that.getTable()); 
	}
}
