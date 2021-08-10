package cs.exam;
import java.util.logging.Logger;

public class User {
    private String username;
    private String password;

    static final Logger logger = Logger.getLogger(User.class.getName());

    public User(String username, String password){
        if(isPalindrome(password)){
            this.username = username;
            this.password = password;
        } else {
            logger.info("Elige una contraseña palíndroma.");
        }
    }

    public String getUsername() {
        if(username==null){
            return null;
        }
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        if(password==null){
            return null;
        }
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private boolean isPalindrome(String password){
        int size = password.length();
        int begin = 0;
        int end = size-1;
        int middle = (begin + end)/2;
        int i;
        for (i = begin; i <= middle; i++) {
            if (password.charAt(begin) == password.charAt(end)) {
                begin++; end--;
            }
            else {
                break;
            }
        }
        return i == middle + 1;
    }

}
