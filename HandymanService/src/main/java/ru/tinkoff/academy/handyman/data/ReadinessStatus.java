package ru.tinkoff.academy.handyman.data;

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
