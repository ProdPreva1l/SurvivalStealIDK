package info.preva1l.customitems.utils.exceptions;

import info.preva1l.customitems.utils.Console;

public class FileReaderGotFuckedException extends RuntimeException {
    public FileReaderGotFuckedException(String message) {
        super(message);
        Console.severe("the file reader got fucked in the ass");
    }
}