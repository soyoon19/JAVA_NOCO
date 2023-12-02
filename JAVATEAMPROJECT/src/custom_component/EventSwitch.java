package custom_component;

/*
이벤트를 잠시 비활성화 시키고 싶을 때 사용하는 클스이다.
 */
public class EventSwitch {
    private  boolean sw = true;

    public EventSwitch(){}
    public EventSwitch(boolean sw){
        this.sw =  sw;
    }

    public boolean getSw(){
        return sw;
    }

    public void setSw(boolean sw) {
        this.sw = sw;
    }
}
