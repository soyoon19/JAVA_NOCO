package dto;

public class RoomOptionDTO {
    private int option, width, height;

    public RoomOptionDTO() {}

    public RoomOptionDTO(int option, int width, int height) {
        this.option = option;
        this.width = width;
        this.height = height;
    }

    public int getOption() {
        return option;
    }

    public void setOption(int option) {
        this.option = option;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
