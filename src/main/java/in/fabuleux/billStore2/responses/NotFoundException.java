package in.fabuleux.billStore2.responses;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason = "Not found",code = HttpStatus.NOT_FOUND,value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException 
{
	public NotFoundException(String message) 
	{
		super(message);
	}
	
}
