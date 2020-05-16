package compiler.syntax.nonTerminal;


public class SWriteLn 
	extends NonTerminal 
{
    /**
     * Constructor for SWriteLn.
     */
    public SWriteLn ()
    {
        super (); 
    }
    
    public long hashcode ()
    {
    	return 31 * this.getIntermediateCode().hashCode()+
    			this.getTemp().hashCode();
    }
    
    public boolean equals (Object o)
    {
    	if (!(o instanceof SWriteLn)) return false;
    	else {
    		SWriteLn e = (SWriteLn) o;
    		return e.getIntermediateCode().equals(this.getIntermediateCode()) &&
    				e.getTemp().equals(this.getTemp());
    	}
    }
    
    public String toString ()
    {
    	return getIntermediateCode ().toString();
    }


}
