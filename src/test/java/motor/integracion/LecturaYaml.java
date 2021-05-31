package motor.integracion;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import modelo.jugador.plantillas.ModeloUnidad;

class LecturaYaml {

	@Test
	void test() throws FileNotFoundException {
		//Comprobamos que lo lee sin disparar excepciones
		InputStream is= new FileInputStream(new File("src/test/resources/escenariotest1.yml"));
		Yaml yml=new Yaml();
		Map<String, Object> datos= yml.load(is);
		System.out.println(datos);
	}
	
	@Test
	void testCostructor() throws FileNotFoundException {
		//Comprobamos que lo lee sin disparar excepciones
		InputStream is= new FileInputStream(new File("src/test/resources/escenariotest1.yml"));
		Yaml yml=new Yaml(new Constructor(ModeloUnidad.class));
		Map<String, Object> datos= yml.load(is);
		System.out.println(datos);
	}

}
