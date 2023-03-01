package ru.tinkoff.academy.landscape;

public enum ReadinessStatus {
    OK,
    NOK,
    MALFUNCTION;

    @Override
    public String toString() {
        return switch (this) {
            case OK -> "OK";
            case NOK -> "NOK";
            case MALFUNCTION -> "Malfunction";
        };
    }
}
