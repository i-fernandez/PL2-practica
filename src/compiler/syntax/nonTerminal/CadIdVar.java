package compiler.syntax.nonTerminal;

import java.util.ArrayList;


public class CadIdVar 
	extends NonTerminal 
{
	private ArrayList<String> ids;
	
    /**
     * Constructor for CadIdVar.
     */
    public CadIdVar ()
    {
        super (); 
        ids = new ArrayList<String>();
    }
	
    /**
     * Constructor with one value
     * @param id identifier
     */
	public CadIdVar (String id)
	{
		this();
		ids.add(id);
	}
	
	/**
	 * Copy constructor plus one value
	 */
	public CadIdVar (CadIdVar c, String newId)
	{
		this();
		ids.add(newId);
		for (String id : c.getIds()) {
			this.ids.add(id);
		}
		
	}
	
	public ArrayList<String> getIds()
	{
		return this.ids;
	}
		
    public long hashcode ()
    {
    	return 79 * ids.hashCode();
    }
    
    public boolean equals (Object o)
    {
    	if (!(o instanceof CadIdVar)) return false;
    	else {
    		CadIdVar e = (CadIdVar) o;
    		return e.getIds().equals(ids);
    	}
    }
    
    public String toString ()
    {
    	return ids.toString();
    }
}
