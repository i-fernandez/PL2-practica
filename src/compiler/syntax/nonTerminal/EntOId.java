package compiler.syntax.nonTerminal;


public class EntOId 
	extends NonTerminal 
{
	private Integer value;
	
    /**
     * Constructor for EntOId.
     */
    public EntOId ()
    {
        super (); 
    }
   
    
    public EntOId (Integer val)
    {
    	this();
    	this.value = val;
    }
    
    public int getValue()
    {
    	return this.value;
    }
    
    public long hashcode ()
    {
    	return 73 * value.hashCode();
    }
    
    public boolean equals (Object o)
    {
    	if (!(o instanceof EntOId)) return false;
    	else {
    		EntOId e = (EntOId) o;
    		return (e.getValue() == value);
    	}
    }
    
    public String toString ()
    {
    	return value.toString();
    }

}
