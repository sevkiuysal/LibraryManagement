package com.koylumuhendis.librarymanagement.dto;

public enum CategoryType {
    RESEARCH_HISTORY("Araştirma - Tarih"),
    SCIENCE("Bilim"),
    COMIC("Çizgi Roman"),
    CHILD_AND_YOUTH("Çoçuk ve Gençlik");

    private final String value;
    CategoryType(String value) {this.value=value;
    }

    public String getValue() {
        return value;
    }
}
