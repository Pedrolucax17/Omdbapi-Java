package br.com.alura.screenmatchSpring.model;

public enum Category {
    ACTION("Action", "Ação"),
    ROMANCE("Romance", "Romance"),
    COMEDY("Comedy", "Comédia"),
    CRIME("Crime", "Drama"),
    DRAMA("Drama", "Crime");

    private String categoryOmdb;

    private String categoryPortuguese;

    Category(String categoriaOmdb, String categoriaPortuguese){
        this.categoryOmdb = categoriaOmdb;
        this.categoryPortuguese = categoriaPortuguese;
    }

    public static Category fromString(String text) {
        for (Category category : Category.values()) {
            if (category.categoryOmdb.equalsIgnoreCase(text)) {
                return category;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }

    public static Category fromPortugues(String text) {
        for (Category category : Category.values()) {
            if (category.categoryPortuguese.equalsIgnoreCase(text)) {
                return category;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }

}
