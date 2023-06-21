package controller;

import model.Card;

import java.util.ArrayList;

public class PlayerSinglenton {
    private static PlayerSinglenton instance = null;

    ArrayList <Card> cards = new ArrayList<Card>();

    ArrayList <Card> mazoDescarte = new ArrayList<Card>();

    private String name;

    private PlayerSinglenton() {
    }

    public static PlayerSinglenton getInstance() {
        if(instance == null) {
            instance = new PlayerSinglenton();
        }
        return instance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList getCards(){
        return cards;
    }

    public ArrayList getMazoDescarte(){
        return mazoDescarte;
    }

    public void  setCards(Card card){
        cards.add(card);
    }

    public void  setMazoDescarte(Card card){
        mazoDescarte.add(card);
    }
}
