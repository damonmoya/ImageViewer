package model;

public interface Image {
    Image next();
    Image prev();
    Object bitMap();
}