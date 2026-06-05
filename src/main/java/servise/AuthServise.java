package servise;

import dtos.UserDto;

public class AuthServise {
private  static AuthServise authServise;

private AuthServise(){

}
public static AuthServise geInstanse(){
    if(authServise==null){
        authServise=new AuthServise();
    }
    return authServise;
}


    public boolean registraton(UserDto userDto) {




        return true;
    }
}
