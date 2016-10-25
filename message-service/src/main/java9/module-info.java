module message.service {
    requires message.printer;
    requires slf4j.api;

    exports fr.sewatech.message;
}