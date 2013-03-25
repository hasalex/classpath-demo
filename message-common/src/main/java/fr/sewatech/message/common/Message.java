package fr.sewatech.message.common;

public class Message {
    private String template;
    private Object[] params;

    public Message(String template, Object... params) {
        this.template = template;
        this.params = params;
    }
    public String getTemplate() {
        return template;
    }
    public Object[] getParams() {
        return params;
    }
}
