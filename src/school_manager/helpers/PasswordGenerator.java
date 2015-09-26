/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school_manager.helpers;

import java.util.Random;

/**
 *
 * @author abrasha
 */
public class PasswordGenerator {

    private static final String SET = "0123456789AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz";
    private static final int SET_LENGTH = SET.length();
    private static final int PASSWORD_LENGTH = 8;
    private static Random random = new Random();
    
    public static String generate(){
        StringBuilder sb = new StringBuilder(PASSWORD_LENGTH);
        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            sb.append(SET.charAt(random.nextInt(SET_LENGTH)));
        }
        return sb.toString();
    }

}
