module cove.infrastructure.excel {
    exports plus.cove.infrastructure.excel.handler;
    exports plus.cove.infrastructure.excel.listener;
    exports plus.cove.infrastructure.excel.importable;

    requires easyexcel;
    requires cove.infrastructure;
    requires poi;
    requires lombok;
}