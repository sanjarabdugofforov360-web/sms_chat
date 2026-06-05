package repository;

import entity.Profile;

import java.util.Optional;

public class UserRepository {

    private  static UserRepository authrepository;

    private UserRepository(){

    }
    public static UserRepository geInstanse(){
        if(authrepository==null){
            authrepository=new UserRepository();
        }
        return authrepository;
    }
    public  void  savaUser(Profile profile){





    }
    public Optional<Profile> getUserByEmail(String email){



        return Optional.empty();
    }



}
