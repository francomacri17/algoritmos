/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

/**
 *
 * @author franc
 */
public class ComboItem {

    private String key;
    private int value;

    /**
     *
     * @param key
     * @param value
     */
    public ComboItem(String key, int value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return key;
    }

    public String getKey() {
        return key;
    }

    public int getValue() {
        return value;
    }

    /**
     *
     * @return
     */

}
