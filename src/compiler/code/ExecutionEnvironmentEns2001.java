package compiler.code;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import compiler.semantic.type.TypeSimple;
import compiler.CompilerContext;
import compiler.intermediate.*;
import es.uned.lsi.compiler.code.ExecutionEnvironmentIF;
import es.uned.lsi.compiler.code.MemoryDescriptorIF;
import es.uned.lsi.compiler.code.RegisterDescriptorIF;
import es.uned.lsi.compiler.intermediate.QuadrupleIF;
import es.uned.lsi.compiler.semantic.type.TypeIF;
import es.uned.lsi.compiler.intermediate.OperandIF;

/**
 * Class for the ENS2001 Execution environment.
 */

public class ExecutionEnvironmentEns2001 
    implements ExecutionEnvironmentIF
{    
    private final static int      MAX_ADDRESS = 65535; 
    private final static String[] REGISTERS   = {
       ".PC", ".SP", ".SR", ".IX", ".IY", ".A", 
       ".R0", ".R1", ".R2", ".R3", ".R4", 
       ".R5", ".R6", ".R7", ".R8", ".R9"
    };
    
    private RegisterDescriptorIF registerDescriptor;
    private MemoryDescriptorIF   memoryDescriptor;
    
    /**
     * Constructor for ENS2001Environment.
     */
    public ExecutionEnvironmentEns2001 ()
    {       
        super ();
    }
    
    /**
     * Returns the size of the type within the architecture.
     * @return the size of the type within the architecture.
     */
    @Override
    public final int getTypeSize (TypeSimple type)
    {      
        return 1;  
    }
    
    /**
     * Returns the registers.
     * @return the registers.
     */
    @Override
    public final List<String> getRegisters ()
    {
        return Arrays.asList (REGISTERS);
    }
    
    /**
     * Returns the memory size.
     * @return the memory size.
     */
    @Override
    public final int getMemorySize ()
    {
        return MAX_ADDRESS;
    }
           
    /**
     * Returns the registerDescriptor.
     * @return Returns the registerDescriptor.
     */
    @Override
    public final RegisterDescriptorIF getRegisterDescriptor ()
    {
        return registerDescriptor;
    }

    /**
     * Returns the memoryDescriptor.
     * @return Returns the memoryDescriptor.
     */
    @Override
    public final MemoryDescriptorIF getMemoryDescriptor ()
    {
        return memoryDescriptor;
    }
    
    private String translateOperand (OperandIF o) 
    {
    	try {
	    	if (o instanceof Value) {
	    		return "#" + ((Value)o).getValue();
	    	} 
	    	if (o instanceof Temporal) {
	    		return "#-" + ((Temporal)o).getAddress() + "[.IX]";
	    	} 
	    	if (o instanceof Variable ) {
	    		Variable v = (Variable) o;
	    		if(v.isGlobal()) {
	    			return "/" + v.getAddress();
	    			//return null;
	    		} else {
	    			return "#-" + v.getAddress() + "[.IX]";
	    			//return null;
	    		}
	    	}
	    	if (o instanceof Label) {
	    		return ((Label)o).getName();
	    	}
    	} catch (NullPointerException e) {return null;}
    	  catch (ClassCastException c) {
    		  System.out.println("Op: " + o.toString() + " Excep: " + c.toString()); 
    		  return null;
    		 }
    	return null;
	}


    /**
     * Translate a quadruple into a set of final code instructions. 
     * @param cuadruple The quadruple to be translated.
     * @return a quadruple into a set of final code instructions. 
     */
    @Override
    public final String translate (QuadrupleIF quadruple)
    {  	
    	// Traduccion de cada operando
    	String oper1 = this.translateOperand(quadruple.getFirstOperand());
    	String oper2 = this.translateOperand(quadruple.getSecondOperand());
    	String operR = this.translateOperand(quadruple.getResult());
    	 
    	// Variables auxiliares
    	StringBuffer sB = new StringBuffer();		// Salida codigo final
    	String sName = "";		// Nombre del subprograma
    	
    	// Genera codigo final
    	switch (quadruple.getOperation ()) {
    	case "DATA":		
    		// Asigna espacio a los elementos en memoria
    		MemoryAssigner.assigment(); 
    		
    		// Asigna espacio a las cadenas de datos
    		sB.append(String.format("%s /%s", "BR", (MemoryAssigner.getDirEstatica () + Data.getTotalSize() + 1)));
    		sB.append("\n" + "ORG " + (MemoryAssigner.getDirEstatica () + 1)  + "\n");
    		Iterator<String> it = Data.cadenas.keySet().iterator();
    		while(it.hasNext()) {
    			String s = it.next();
    			sB.append(s + ": DATA "+"\""+Data.cadenas.get(s)+"\""+"\n");
    		}
    		sB.append("ORG " + (MemoryAssigner.getDirEstatica () + Data.getTotalSize() + 1) + "\n");	
    		
    		// Reserva espacio para la estructura Display
    		int spInicial = this.getMemorySize() - MemoryAssigner.getMaxScopeLevel() - 1;
    		sB.append("MOVE #" + spInicial + ", .SP" + "\n");
    		
    		//Prepara el R.A. de main
    		int mainRASize = MemoryAssigner.getTamanoRA("main");
    		sB.append("MOVE .SP, .IX" + "\n");				// Establece el registro IX al final de la pila
    		sB.append("PUSH #-1" + "\n");					// Introduce el valor de retorno
    		sB.append("PUSH .IY" + "\n");					// Introduce el enlace de control
    		sB.append("PUSH #-1" + "\n");					// Introduce el enlace de acceso
    		sB.append("PUSH .SR" + "\n");					// Introduce el estado de la máquina
    		sB.append("PUSH #-1" + "\n");					// Introduce la direccion de retorno
    		sB.append("SUB .IX, #" + mainRASize + "\n");	// Establece el SP
    		sB.append("MOVE .A, .SP");
    		return sB.toString();
/////////////////////
    	case "DATA_SUB":	// Prepara el RA de un subprograma
    		sName = operR.substring(2);	
    		int enlaceAcceso = this.getMemorySize() - MemoryAssigner.getProfAnidamiento(sName);	
    		sB.append("; DATA_SUB " + sName + "\n");
    		sB.append("MOVE .SP, .R8" + "\n");						// Salva el SP
    		sB.append("PUSH #-1" + "\n");							// Introduce el valor de retorno
    		sB.append("PUSH .IX" + "\n");							// Introduce el enlace de control
    		sB.append("MOVE #" + enlaceAcceso + ", .R5" + "\n");	// Guarda su posicion del display en R5
    		sB.append("PUSH [.R5]" + "\n");							// Guarda el enlace de acceso		
    		sB.append("PUSH .SR");									// Introduce el estado de la máquina
    		return sB.toString();
///////////////////// 
    	case "HALT":	// Fin de programa
    		sB.append("HALT");
    		return sB.toString();
/////////////////////    		
    	case "MV":	// Copia el contenido de oper1 en el temporal operR
    		sB.append("MOVE " + oper1 + ", " + operR);
    		return sB.toString();
/////////////////////    		
    	case "MVP":		// Copia el valor de una direccion absoluta en el temporal oper1 a otro temporal operR
    		sB.append("MOVE " + oper1 + ", .R1" + "\n");
    		sB.append("MOVE [.R1], " + operR);
    		return sB.toString();
/////////////////////    		
    	case "MVA":	   // Copia la direccion absoluta de una variable oper1 a un temporal operR
    		Variable v = (Variable) quadruple.getFirstOperand();
    		if (v.isGlobal()) {
    			sB.append("MOVE #" + v.getAddress() + ", " + operR);
    		} else {
    			int posDisplay = this.getMemorySize() - v.getLevel();	// Ubicacion del enlace acceso
    			sB.append("ADD #" + posDisplay + ", #0" + "\n");		// Guarda en el acumulador la direccion
    			sB.append("SUB [.A], #" + v.getAddress() + "\n");		// Resta a la dir de inicio la dir de la variable
    			sB.append("MOVE .A, " + operR);
    		}
    		return sB.toString();
/////////////////////    		
    	case "STP":  // Copia el valor del temporal oper1 en la direccion apuntada por operR
    		sB.append("ADD " + operR + ", #0" + "\n");
    		sB.append("MOVE " + oper1 + ", [.A]");
    		return sB.toString();
/////////////////////
    	case "SUB":
    		sB.append("SUB " + oper1 + ", " + oper2 + "\n");
    		sB.append("MOVE .A, " + operR);
    		return sB.toString();
/////////////////////
    	case "ADD":
    		sB.append("ADD " + oper1 + ", " + oper2 + "\n");
    		sB.append("MOVE .A, " + operR);
    		return sB.toString();
/////////////////////
    	case "MUL":
    		sB.append("MUL " + oper1 + ", " + oper2 + "\n");
    		sB.append("MOVE .A, " + operR);
    		return sB.toString();
/////////////////////
    	case "INC":
    		sB.append("INC " + operR);
    		return sB.toString();
/////////////////////
    	case "GR":
    		sB.append("CMP " + oper2 + ", " + oper1 + "\n");
    		sB.append("MOVE #-1, " + operR + "\n");
    		sB.append("BN $3 " + "\n");		// Salta 3 posiciones (las 2 de la instruccion y otra mas)
    		sB.append("MOVE #0, " + operR);
    		return  sB.toString();
/////////////////////
    	case "EQ":	// Efectua una comparacion
    		sB.append("CMP " + oper1 + ", " + oper2 + "\n");
    		sB.append("MOVE #-1, " + operR + "\n");
    		sB.append("BZ $3 " + "\n");		// Salta 3 posiciones (las 2 de la instruccion y otra mas)
    		sB.append("MOVE #0, " + operR);
    		return sB.toString();
/////////////////////
    	case "OR":
    		sB.append("OR " + oper1 + ", " + oper2 + "\n");
    		sB.append("MOVE .A, " + operR);
    		return sB.toString();
/////////////////////
    	case "NOT":
    		sB.append("NOT " + operR);
    		return sB.toString();
/////////////////////
    	case "WRTSTR":		// Escribe cadena de texto
    		sB.append("WRSTR /" + quadruple.getResult().toString() + "\n");
    		sB.append("WRCHAR #10 " + "\n");
    		sB.append("WRCHAR #13");
    		return sB.toString(); 
/////////////////////
    	case "WRTLN":		// Escribe linea en blanco
    		sB.append("WRCHAR #10" + "\n");
    		sB.append("WRCHAR #13");
    		return sB.toString();
/////////////////////
    	case "WRTINT":		// Escribe un entero 
    		sB.append("WRINT " + operR + "\n");
    		sB.append("WRCHAR #10 " + "\n");
    		sB.append("WRCHAR #13");
    		return sB.toString();
/////////////////////
    	case "BRF":	 //(T, L) Si temporal T FALSO, salto a etiqueta L
    		sB.append("ADD #0, " + operR + "\n");
    		sB.append("BZ /" + oper1);
    		return sB.toString();
/////////////////////
    	case "BR":	// Salto incondicional
    		sB.append("BR /" + operR);
    		return sB.toString();
/////////////////////
    	case "BRT":
    		sB.append("ADD #0, " + operR + "\n");
    		sB.append("BNZ /" + oper1);
    		return sB.toString();
/////////////////////
    	case "LABEL": 		// Inserta etiqueta 
    		sB.append(operR + ": NOP");
    		return sB.toString();
/////////////////////
    	case "CALL":		// Llama a un subprograma
    		sB.append("; CALL " + operR + "\n");
    		sB.append("MOVE .R8, .IX" + "\n");			// Establece el IX al valor anterior de SP
    		sB.append("MOVE .IX, [.R5]" + "\n");		// Guarda IX en el Display
    		sB.append("CALL /" + operR);				// Salta al subprograma (salva el SP en la pila)
    		return sB.toString();
/////////////////////
    	case "LABEL_SUB": 		// Inicio de subprograma
    		sName = operR.substring(2);				// Nombre del subprograma
    		int size = MemoryAssigner.getTamanoRA(sName);	// Tamaño del RA
    		sB.append("; LABEL_SUB " + operR + "\n");
    		sB.append(operR + ": NOP" + "\n");				// Inserta etiqueta
    		sB.append("SUB .R8, #" + size + "\n");			// Calcula nueva posicion del SP
    		sB.append("MOVE .A, .SP");						// Actualiza el SP
    		return sB.toString();
/////////////////////
    	case "RET":			// Sentencia de retorno subprograma
    		sB.append("MOVE " + operR + ", [.IX]" + "\n");		// Guarda el valor de retorno
    		sB.append("BR /" + "FS_" + oper1.toString());		// Salta a etiqueta de salida
    		return sB.toString();
/////////////////////
    	case "PARAM":		// Parametros de subprograma (paso por valor)
    		sB.append("; PARAM " + operR + "\n");
    		sB.append("PUSH " + operR);
    		return sB.toString();
/////////////////////
    	case "FIN_SUB":		// Fin de subprograma
    		sName = operR.substring(2);	
    		int eAcceso = this.getMemorySize() - MemoryAssigner.getProfAnidamiento(sName);	
    		sB.append("FS_" + operR.toString() + ": NOP" + "\n");	// Añade etiqueta de salida
    		int nParam = Integer.valueOf(oper1.substring(1));		// Elimina #
    		int undoSize = nParam + 5;								// Tamaño a deshacer (nº params + 5)
    		sB.append("; FIN_SUB " + operR + "\n");		
    		sB.append("MOVE #" + eAcceso + ", .R4" + "\n");						
    		sB.append("MOVE #-2[.IX], [.R4]" + "\n");				// Restaura el enlace de acceso de su nivel
    		sB.append("SUB .IX,  #" + undoSize + "\n"); 			// Rescata la direccion de retorno
    		sB.append("MOVE .A, .SP" + "\n");						// Apunta SP a direccion de retorno
    		sB.append("RET");						 				// Extrae de la pila el PC de vuelta
    		return sB.toString();
/////////////////////
    	case "UNDO_SUB":		// Fin de subprograma, restaura RA, guarda en temporal el valor de retorno
    		sB.append("; UNDO_SUB " + "\n");
    		sB.append("MOVE .IX, .R6"  + "\n");			// Salva el valor de retorno en R6
    		sB.append("SUB .IX, #3 " + "\n");		    // Rescata informacion de registros
    		sB.append("MOVE [.A], .SR" + "\n");			// Restaura informacion de registros
    		sB.append("MOVE .IX, .SP" + "\n");			// Restablece SP
    		sB.append("SUB .IX, #1" + "\n");			// Rescata enlace control
    		sB.append("MOVE [.A], .IX" + "\n");			// Restaura IX a enlace contol
    		sB.append("MOVE .R6, " + operR);			// Guarda el valor de retorno en el temporal
    		return sB.toString();
    	}
        return "NOP \n"; 
    }
}
