package com.opencode.test.validate;

import com.opencode.test.entity.Game;

public class ValidateGameNumber implements Validate<String> {
    @Override
    public boolean validate(String s) {
        if (s.length() != Game.SIZE_NUMBER) {
            return false;
        }

        for (int n = 0; n < s.length(); n++) {
            byte b = (byte) (s.charAt(n) - '0');
            if (b < 0 || b > 9) {
                return false;
            }
        }

        return true;
    }
}
