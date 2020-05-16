package compiler.syntax.nonTerminal;


public class SWriteString 
	extends NonTerminal 
{
    /**
     * Constructor for SWriteString.
     */
    public SWriteString ()
    {
        super (); 
    }
    
    public long hashcode ()
    {
    	return 37 * this.getIntermediateCode().hashCode()+
    			this.getTemp().hashCode();
    }
    
    public boolean equals (Object o)
    {
    	if (!(o instanceof SWriteString)) return false;
    	else {
    		SWriteString e = (SWriteString) o;
    		return e.getIntermediateCode().equals(this.getIntermediateCode()) &&
    				e.getTemp().equals(this.getTemp());
    	}
    }
    
    public String toString ()
    {
    	return getIntermediateCode ().toString();
    }

}
