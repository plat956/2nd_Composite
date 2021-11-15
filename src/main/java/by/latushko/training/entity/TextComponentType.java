package by.latushko.training.entity;

public enum TextComponentType {
    PARAGRAPH("\t", "\r\n"),
    SENTENCE("", ""),
    LEXEME("", ""),
    WORD(" ", ""),
    LETTER("", ""),
    CHARACTER("", "");

    private final String prefix;
    private final String postfix;

    TextComponentType(String prefix, String postfix) {
        this.prefix = prefix;
        this.postfix = postfix;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getPostfix() {
        return postfix;
    }
}
