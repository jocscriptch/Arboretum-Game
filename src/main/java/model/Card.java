package model;

public class Card {
    private String name;
    private String type;
    private String extension;
    private int points;
    private boolean placed;

    public Card(String name, String type, String extension , int points, boolean placed) {
        this.name = name;
        this.type = type;
        this.extension = extension;
        this.points = points;
        this.placed = false;
    }

    public String obtenerNombre() {
        return name;
    }

    public String obtenerTipo() {
        return type;
    }
    public String getExtension(){
        return extension;
    }
    public void setExtension(String extension){
        this.extension = extension;
    }
    public int obtenerPuntos() {
        return points;
    }

    public boolean estaColocada() {
        return placed;
    }
    public void setPlaced(boolean placed){
        this.placed = placed;
    }

    public void colocarEnJardin() {
        placed = true;
    }

    public void removerDeJardin() {
        placed = false;
    }
}
