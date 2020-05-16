package compiler.semantic.symbol;

import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.symbol.SymbolBase;
import es.uned.lsi.compiler.semantic.type.TypeIF;

/**
 * Class for SymbolConstant.
 */

// TODO: Student work
//       Include properties to characterize constants

public class SymbolConstant
    extends SymbolBase
{
	private int value;
    
    /**
     * Constructor for SymbolConstant.
     * @param scope The declaration scope.
     * @param name The symbol name.
     * @param type The symbol type.
     */
    public SymbolConstant (ScopeIF scope,
                           String name,
                           TypeIF type)
    {
        super (scope, name, type);
    }
    
    public void setValue (Object value)
    {
    	if (value instanceof Boolean) {
    		boolean v = (boolean) value;
    		if (v) this.value = -1;
    		else this.value = 0;
    	} else {
    		this.value = (Integer) value;
    	}
    }
    
    
    public Object getValue ()
    {
    	return this.value;
    }
}
