package Laboral;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Nomina {
	
	/**
	 * Logger
	 */
	final static Logger LOGGER = LoggerFactory.getLogger(Nomina.class);

    /**
     * Sueldo base de los empleados
     */
    private int sueldo_base;

    /**
     * Cáculo del sueldo del empleado
     *
     * @param emp
     * @return sueldo
     */
    public double sueldo(Empleado emp) {
    	
    	LOGGER.info("Inicio del método sueldo");
    	
        double sueldo = sueldo_base + 5000 * emp.anyos;
        LOGGER.info("Fin del método sueldo");
        return sueldo;
        
        
    }
}
