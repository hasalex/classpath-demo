module message.service {
    requires message.common;
    requires message.printer;
    requires slf4j.api;
    exports fr.sewatech.message;
}