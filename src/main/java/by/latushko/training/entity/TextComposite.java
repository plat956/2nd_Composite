package by.latushko.training.entity;

import java.util.ArrayList;
import java.util.List;

public class TextComposite implements TextComponent{
    private List<TextComponent> components = new ArrayList<>();
    private TextComponentType type;

    public TextComposite(TextComponentType type) {
        this.type = type;
    }

    @Override
    public void add(TextComponent component) {
        components.add(component);
    }

    @Override
    public void remove(TextComponent component) {
        components.remove(component);
    }

    @Override
    public TextComponentType getType() {
        return type;
    }

    @Override
    public List<TextComponent> getComponents() {
        return components;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();

        for(TextComponent c: components) {
            if (type == TextComponentType.PARAGRAPH) {
                sb.append(type.getPrefix());
            }

            String temp = c.getType().getPrefix() + c + c.getType().getPostfix();

            if(type == TextComponentType.PARAGRAPH && c.getType() == TextComponentType.SENTENCE) {
                temp = temp.substring(1); //remove space prefix if the sentence is first in this paragraph
            }

            sb.append(temp);

            if (type == TextComponentType.PARAGRAPH) {
                sb.append(type.getPostfix());
            }
        }

        return sb.toString();
    }
}
