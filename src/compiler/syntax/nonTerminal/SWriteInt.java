package compiler.syntax.nonTerminal;


public class SWriteInt 
	extends NonTerminal 
{
    /**
     * Constructor for SWriteInt.
     */
    public SWriteInt ()
    {
        super (); 
    }
    
    public long hashcode ()
    {
    	return 29 * this.getIntermediateCode().hashCode()+
    			this.getTemp().hashCode();
    }
    
    public boolean equals (Object o)
    {
    	if (!(o instanceof SWriteInt)) return false;
    	else {
    		SWriteInt e = (SWriteInt) o;
    		return e.getIntermediateCode().equals(this.getIntermediateCode()) &&
    				e.getTemp().equals(this.getTemp());
    	}
    }
    
    public String toString ()
    {
    	return getIntermediateCode ().toString();
    }


}
