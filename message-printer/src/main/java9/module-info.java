module message.printer {
    requires transitive message.common;
    requires slf4j.api;

    exports private fr.sewatech.message.printer;
}