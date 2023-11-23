package custom_component;

import java.awt.*;

public class DefaultFont extends Font{
    public static final String NAME="맑은고딕";
    public static final int STYLE = Font.TRUETYPE_FONT;
    public static final int SIZE = 50;


    public DefaultFont(){
        super(NAME, STYLE, SIZE);
    }

    public DefaultFont(int size) {
        super(NAME, STYLE, size);
    }

    public DefaultFont(String name){
        super(name, STYLE, SIZE);
    }

    public DefaultFont(int size, int style){
        super(NAME, style, size);
    }

    public DefaultFont(String name, int size, int style){
        super(name, style, size);
    }

}
