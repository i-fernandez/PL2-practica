package compiler.syntax.nonTerminal;


public class SentAsign 
	extends NonTerminal 
{
    /**
     * Constructor for SentAsign.
     */
    public SentAsign ()
    {
        super (); 
    }
    
    public long hashcode ()
    {
    	return 9 * this.getIntermediateCode().hashCode() +
    			this.getTemp().hashCode();
    }
    
    public boolean equals (Object o)
    {
    	if (!(o instanceof SentAsign)) return false;
    	else {
    		SentAsign e = (SentAsign) o;
    		return e.getIntermediateCode().equals(this.getIntermediateCode()) &&
    				e.getTemp().equals(this.getTemp());
    	}
    }
    
    public String toString ()
    {
    	return getIntermediateCode ().toString ();
    }

}
