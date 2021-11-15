package by.latushko.training.entity;

import java.util.List;

public interface TextComponent {
    void add(TextComponent component);

    void remove(TextComponent component);

    TextComponentType getType();

    List<TextComponent> getComponents();

    String toString();
}
