package cn.itcast.ssm.controller.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/**
 * 
 * @author Fangchao
 *
 */
public class CustomDateConverter implements Converter<String, Date>{

	@Override
	public Date convert(String source) {
		 SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 try{
			 return dateFormat.parse(source);
		 }
		 catch(ParseException exception){
			 exception.printStackTrace();
		 }
		 //参数绑定失败返回null
		return null;
	}

}
