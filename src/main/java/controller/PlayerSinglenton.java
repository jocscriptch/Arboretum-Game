package controller;

import model.Card;

import java.util.ArrayList;

public class PlayerSinglenton {
    private static PlayerSinglenton instance = null;

    private ArrayList <Card> cards = new ArrayList();

    private ArrayList <Card> mazoDescarte = new ArrayList();

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

    public ArrayList<Card> getCards(){
        return cards;
    }

    public ArrayList<Card> getMazoDescarte(){
        return mazoDescarte;
    }

    public void  setCards(Card card){
        cards.add(card);
    }

    public void  setMazoDescarte(Card card){
        mazoDescarte.add(card);
    }
}
