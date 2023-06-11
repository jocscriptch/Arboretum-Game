package model;

public class Card {
    private String name;
    private String type;
    private int points;
    private boolean placed;

    public Card(String name, String type, int points, boolean placed) {
        this.name = name;
        this.type = type;
        this.points = points;
        this.placed = false;
    }

    public String obtenerNombre() {
        return name;
    }

    public String obtenerTipo() {
        return type;
    }

    public int obtenerPuntos() {
        return points;
    }


    public boolean estaColocada() {
        return placed;
    }

    public void colocarEnJardin() {
        placed = true;
    }

    public void removerDeJardin() {
        placed = false;
    }
}
