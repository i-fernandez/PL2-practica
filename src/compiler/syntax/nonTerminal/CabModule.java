package compiler.syntax.nonTerminal;


/**
 * Abstract Class for CabModule non terminal.
 */
public class CabModule 
	extends NonTerminal 
{
	private String name;
	
    /**
     * Constructor for CabModule.
     */
    public CabModule ()
    {
        super (); 
    }
    
    
    public CabModule (String name)
    {
    	this();
    	this.name = name;
    }
    
    
    public String getName()
    {
    	return this.name;
    }
    
    public long hashcode ()
    {
    	return 67 * name.hashCode();
    }
    
    public boolean equals (Object o)
    {
    	if (!(o instanceof CabModule)) return false;
    	else {
    		CabModule e = (CabModule) o;
    		return e.getName().equals(name);
    	}
    }
    
    public String toString ()
    {
    	return name;
    }
    
}
