package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mario on 27.5.2017..
 */
public class ProductsViewModel {

    public static String LIGHTBEER = "container_svijetlo";
    public static String DARKBEER = "container_tamno";
    public static String WHEATBEER = "container_psenicno";
    public static String OTHERBEER = "container_ostalo";
    public static String ALLBEER = "container_sve";

    private ArrayList<ProductViewModel> lightBeer;
    private ArrayList<ProductViewModel> darkBeer;
    private ArrayList<ProductViewModel> wheatBeer;
    private ArrayList<ProductViewModel> otherBeer;
    private ArrayList<ProductViewModel> specials;


    public ArrayList<ProductViewModel> getLightBeer() {

        if (lightBeer == null)
            lightBeer = new ArrayList<>();

        return lightBeer;
    }

    public void setLightBeer(ArrayList<ProductViewModel> lightBeer) {
        this.lightBeer = lightBeer;
    }

    public ArrayList<ProductViewModel> getDarkBeer() {

        if (darkBeer == null)
            darkBeer = new ArrayList<>();

        return darkBeer;
    }

    public void setDarkBeer(ArrayList<ProductViewModel> darkBeer) {
        this.darkBeer = darkBeer;
    }

    public ArrayList<ProductViewModel> getWheatBeer() {

        if (wheatBeer == null)
            wheatBeer = new ArrayList<>();

        return wheatBeer;
    }

    public void setWheatBeer(ArrayList<ProductViewModel> wheatBeer) {
        this.wheatBeer = wheatBeer;
    }

    public ArrayList<ProductViewModel> getOtherBeer() {

        if (otherBeer == null)
            otherBeer = new ArrayList<>();

        return otherBeer;
    }

    public void setOtherBeer(ArrayList<ProductViewModel> otherBeer) {
        this.otherBeer = otherBeer;
    }

    public ArrayList<ProductViewModel> getSpecials() {

        if (specials == null)
            specials = new ArrayList<>();

        return specials;
    }

    public void setSpecials(ArrayList<ProductViewModel> specials) {
        this.specials = specials;
    }

    public ArrayList<ProductViewModel> getAll() {

        ArrayList<ProductViewModel> all = new ArrayList<>();

        all.addAll(getLightBeer());
        all.addAll(getDarkBeer());
        all.addAll(getWheatBeer());
        all.addAll(getOtherBeer());

        return all;
    }

    public Map<String, ArrayList<ProductViewModel>> getContainers() {

        Map<String, ArrayList<ProductViewModel>> model = new HashMap<>();

        model.put(LIGHTBEER, getLightBeer());
        model.put(DARKBEER, getDarkBeer());
        model.put(WHEATBEER, getWheatBeer());
        model.put(OTHERBEER, getOtherBeer());
        model.put(ALLBEER, getAll());

        return model;
    };

}
