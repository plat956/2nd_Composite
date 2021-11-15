package by.latushko.training.entity;

import java.util.List;

public class CharacterNode implements TextComponent{
    private TextComponentType type;
    private char character;

    public CharacterNode(TextComponentType type, char character) {
        this.type = type;
        this.character = character;
    }

    @Override
    public void add(TextComponent component) {
        throw new UnsupportedOperationException("Operation 'add' is unsupported");
    }

    @Override
    public void remove(TextComponent component) {
        throw new UnsupportedOperationException("Operation 'remove' is unsupported");
    }

    @Override
    public TextComponentType getType() {
        return type;
    }

    @Override
    public List<TextComponent> getComponents() {
        throw new UnsupportedOperationException("No components to return because of the operation is unsupported");
    }

    @Override
    public String toString() {
        return String.valueOf(character);
    }
}
