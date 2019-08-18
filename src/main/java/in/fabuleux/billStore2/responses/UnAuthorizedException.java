package in.fabuleux.billStore2.responses;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason = "Unauthorized",code = HttpStatus.UNAUTHORIZED,value = HttpStatus.UNAUTHORIZED)
public class UnAuthorizedException extends RuntimeException {
	public UnAuthorizedException(String message) 
	{
		super(message);
	}
}
