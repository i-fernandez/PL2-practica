package compiler.syntax.nonTerminal;

import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.type.TypeIF;
import compiler.semantic.type.*;

public class ValorConst 
	extends NonTerminal 
{
	private TypeIF type;
	private Object value;
	
    /**
     * Constructor for ValorConst.
     */
    public ValorConst ()
    {
        super (); 
    }
    
    public ValorConst(Object val, ScopeIF scope)
    {
    	this();
    	this.value = val;
    	if (value instanceof Integer) type = new TypeSimple(scope, "ENTERO");
    	if (value instanceof Boolean) type = new TypeSimple(scope, "BOOLEANO");
    }
    
    
    public Object getValue ()
    {
    	return value;
    }
    
    public TypeIF getType() 
    {
    	return this.type;
    }
    
    
    public long hashcode ()
    {
    	return 43 * type.hashCode() + this.value.hashCode();
    }
    
    public boolean equals (Object o)
    {
    	if (!(o instanceof ValorConst)) return false;
    	else {
    		ValorConst e = (ValorConst) o;
    		return e.getType().equals(this.getType()) &&
    				e.value.equals(this.value);
    	}
    }
    
    public String toString ()
    {
    	if (type.getName().equals("ENTERO")) return ((Integer) value).toString();
    	else return ((Boolean) value).toString();
    }


}
