package custom_component;

import java.awt.*;

/*
만약 폰트를 설정할 경우 다음 객체를 사용한다.

기본값이 들어 있어 그냥 사용해도 되지만
크기를 변경하고 싶다면 생성할 때 int형으로 사이즈 크기로 넘겨주면된다.
다른 기능들은 생성자를 참고하자
 */
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
