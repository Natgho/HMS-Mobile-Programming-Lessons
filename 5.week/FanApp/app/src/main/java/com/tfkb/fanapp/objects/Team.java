package com.tfkb.fanapp.objects;

public class Team {
    private String name;
    private int logo;
    private int colorFirst;
    private int colorSecond;
    private int colorThird;
    private String president;
    private String stadium;

    public Team(String name, int logo, int colorFirst, int colorSecond, int colorThird, String president, String stadium) {
        this.name = name;
        this.logo = logo;
        this.colorFirst = colorFirst;
        this.colorSecond = colorSecond;
        this.colorThird = colorThird;
        this.president = president;
        this.stadium = stadium;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }

    public int getColorFirst() {
        return colorFirst;
    }

    public void setColorFirst(int colorFirst) {
        this.colorFirst = colorFirst;
    }

    public int getColorSecond() {
        return colorSecond;
    }

    public void setColorSecond(int colorSecond) {
        this.colorSecond = colorSecond;
    }

    public int getColorThird() {
        return colorThird;
    }

    public void setColorThird(int colorThird) {
        this.colorThird = colorThird;
    }

    public String getPresident() {
        return president;
    }

    public void setPresident(String president) {
        this.president = president;
    }

    public String getStadium() {
        return stadium;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }
}
