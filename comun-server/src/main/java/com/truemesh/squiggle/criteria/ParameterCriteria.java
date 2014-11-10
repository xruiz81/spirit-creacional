package com.truemesh.squiggle.criteria;

import java.util.Set;

import com.truemesh.squiggle.Criteria;
import com.truemesh.squiggle.Table;
import com.truemesh.squiggle.output.Output;

/**
 * Class NoCriteria is a Criteria that represents an absent operand in an SQL
 * predicate expression so that one may represent a unary operator (for example,
 * {@link NOT}) using a binary operator derived from a {@link BaseLogicGroup}).
 * 
 * @author <a href="mailto:derek@derekmahar.ca">Derek Mahar</a>
 */
public class ParameterCriteria extends Criteria {
	/**
	 * Writes an empty criteria (single space) to the given output stream.
	 * 
	 * @see com.truemesh.squiggle.Criteria#write(com.truemesh.squiggle.output.Output)
	 */
	private Table table;
	private String columna;
	
	public ParameterCriteria(Table table,String columna) {
		this.columna=columna;
		this.table=table;
	}
	
	public void write(Output out) {
		out.print(table.getAlias()).print('.').print(columna).print("=:").print(columna);
	}

	public void addReferencedTablesTo(Set<Table> tables) {
	}
}
