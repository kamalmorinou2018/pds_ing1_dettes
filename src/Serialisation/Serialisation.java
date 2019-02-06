package Serialisation;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Serialisation {

	@SuppressWarnings("unchecked")
	public JSONObject serialisationDTO( Object dto ) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
 		JSONObject V1 = new JSONObject();
 		Field [] fields = dto.getClass().getDeclaredFields();
 		for (Field field : fields)
 			V1.put(field.getName(), dto.getClass().getDeclaredMethod("get"+field.getName(), null).invoke(dto, null));
		return V1;
	}
 		
	public JSONObject deserialisation(String string) throws ParseException{
		JSONParser jsonParser = new JSONParser();
		JSONObject z = (JSONObject) jsonParser.parse(string);
		return z;	
	}

	
	
	
}
