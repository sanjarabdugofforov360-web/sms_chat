package repository;

import entity.Profile;

import java.util.Optional;

public class UserRepository {

    private  static UserRepository userRepository;

    private UserRepository(){

    }
    public static UserRepository geInstanse(){
        if(userRepository==null){
            userRepository=new UserRepository();
        }
        return userRepository;
    }
    public  void  savaUser(Profile profile){





    }
    public Optional<Profile> getUserByEmail(String email){



        return Optional.empty();
    }



}
