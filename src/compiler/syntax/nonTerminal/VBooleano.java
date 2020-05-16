package compiler.syntax.nonTerminal;


public class VBooleano 
	extends NonTerminal 
{
	private boolean value;
	
    /**
     * Constructor for CabModule.
     */
    public VBooleano ()
    {
        super (); 
    }
    
    public VBooleano(boolean val)
    {
    	this();
    	setValue(val);
    }
    
    public boolean getValue()
    {
    	return value;
    }
    
    public void setValue(boolean val)
    {
    	this.value = val;
    }
    
    public long hashcode ()
    {
    	if (value) return 53 * 53;
    	else return 53;
    }
    
    public boolean equals (Object o)
    {
    	if (!(o instanceof VBooleano)) return false;
    	else {
    		VBooleano v = (VBooleano) o;
    		return (v.toString().equals(this.toString()));
    	}
    }
    
    public String toString ()
    {
    	if (value) return "true";
    	else return "false";
    }

}
