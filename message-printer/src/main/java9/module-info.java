module message.printer {
    requires transitive message.common;
    requires slf4j.api;

    exports fr.sewatech.message.printer;
}