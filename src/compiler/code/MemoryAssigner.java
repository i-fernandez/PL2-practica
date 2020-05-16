package compiler.code;


import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import compiler.CompilerContext;
import compiler.semantic.symbol.*;
import compiler.intermediate.*;
import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.symbol.SymbolIF;
import es.uned.lsi.compiler.intermediate.TemporalIF;

/**
0   	- Valor retorno
1		- Enlace control
2		- Enlace acceso
3		- Estado registros
4   	- Parametros
4+p 	- Direccion retorno
5+p 	- Variables locales
5+p+v  	- Temporales
*/

public class MemoryAssigner {

	private static int dirEstatica = 1;				// Direccion actual para datos estaticos (solo en main)	
	private static int incrementoRetorno = 1;		// Incremento para la direccion de retorno
	private static int inicioRA = 4;				// Direccion de inicio de cada RA (valor retorno + control + acceso + registros)
	
	private static HashMap<String, Integer> tamanosRA = new HashMap<>();		// Tamaños de los R.A.
	private static HashMap<String, Integer> profAnidamiento = new HashMap<>();	// Profundidades de anidamiento de cada ambito
	
	private static int maxScopeLevel = 0;			// Profundidad de anidamiento maxima
	
	
	/**
	 * Asigna direcciones de memoria a los elementos
	 */
	public static void assigment () { 
		List<ScopeIF> scopes = CompilerContext.getScopeManager().getAllScopes ();
		for (ScopeIF scope: scopes) {
			if (scope.getLevel() > maxScopeLevel) maxScopeLevel = scope.getLevel();
			profAnidamiento.put(scope.getName(), scope.getLevel());
			
			int direccionRA = inicioRA ; 
			List<SymbolIF> symbols = scope.getSymbolTable ().getSymbols();
			// Asigna direcciones a todos los parametros
			for (SymbolIF s: symbols) {	
				if (s instanceof SymbolParameter) { 
					((SymbolParameter)s).setAddress(direccionRA);
					direccionRA = direccionRA + s.getType().getSize(); 
				}
			}
			
			// Actualiza el RA con las direcciones de retorno
			direccionRA = direccionRA + incrementoRetorno;
			
			// Asigna direcciones a las variables
			for (SymbolIF s: symbols) {
				if (s instanceof SymbolVariable) {
					if (scope.getLevel () == 0) { 	// Variable global
						((SymbolVariable)s).setAddress(dirEstatica); 
						dirEstatica += s.getType().getSize(); 
					} else { 	// Variable local
						((SymbolVariable)s).setAddress(direccionRA); 
						direccionRA = direccionRA + s.getType().getSize(); 
					}
				} 
			}
			
			// Asigna direcciones a los temporales
			List<TemporalIF> temporals = scope.getTemporalTable ().getTemporals();
			for (TemporalIF t: temporals) {
				if (t instanceof Temporal) {
					((Temporal)t).setAddress(direccionRA); 
					direccionRA = direccionRA + ((Temporal)t).getSize(); 
				}
			}
			
			// Añade los datos ambito/tamaño al HashMap
			if (scope.getName().equals("main")) {
				int temporalSize = scope.getTemporalTable().getSize();
				tamanosRA.put(scope.getName(), temporalSize + inicioRA + incrementoRetorno);
			} else {
				tamanosRA.put(scope.getName(), direccionRA);
			}
		} 
	}
	
	/**
	 * @return Ultima direccion asignada a las variables estaticas
	 */
	public static int getDirEstatica () { return dirEstatica; }
	
	/**
	 * Devuelve el tamaño de un ambito concreto
	 * @param scope Ambito a consultar
	 * @return Tamaño del ambito
	 */
	public static int getTamanoRA (String scope) 
	{ 
		if (tamanosRA.containsKey(scope)) return tamanosRA.get(scope);
		return 0;
	}
	
	/**
	 * Devuelve la profundidad de anidamiento de un ambito
	 * @param scope Ambito a consultar
	 * @return Profundida de anidamiento del ambito
	 */
	public static int getProfAnidamiento (String scope)
	{
		if (profAnidamiento.containsKey(scope)) return profAnidamiento.get(scope);
		return -1;
	}
	
	/**
	 * @return Máximo nivel de anidamiento
	 */
	public static int getMaxScopeLevel () { return maxScopeLevel; }
	
	/**
	 * Muestra en pantalla los tamaños de RA de los ambitos
	 */
	public static void printRAs () 
	{
		Iterator<String> it = tamanosRA.keySet().iterator();
		while (it.hasNext()) {
			String n = it.next();
			System.out.println(n + " - " + tamanosRA.get(n));
		}
	}
}
