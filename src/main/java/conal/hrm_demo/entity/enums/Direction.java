package conal.hrm_demo.entity.enums;

public enum Direction {
    ASC("ASC"), DESC("DESC"), UNSORTED("DESC");
    private final String direction;

    Direction(String direction) {
        this.direction = direction;
    }

    public String getDirection() {
        return direction;
    }
}
