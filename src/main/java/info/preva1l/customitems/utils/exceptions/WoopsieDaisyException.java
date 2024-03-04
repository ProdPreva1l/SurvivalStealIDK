package info.preva1l.customitems.utils.exceptions;

import info.preva1l.customitems.utils.Console;

public class WoopsieDaisyException extends RuntimeException {
    public WoopsieDaisyException(String message) {
        super(message);
        Console.severe("woopsy daisies");
    }
}