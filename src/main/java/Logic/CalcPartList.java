/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

/**
 *
 * @author MadsF
 */
public class CalcPartList {

    public static Legohouse createPartList(Legohouse house) {
        for (int i = 0; i < house.getHeight(); i++) {
            boolean evenLayer = (i % 2 == 0);
            house.addLayer(createLayer(house.getLength(), house.getWidth(), evenLayer));
        }
        return house;
    }

    private static Layer createLayer(int length, int width, boolean even) {
        Layer layer = new Layer();
        Side side1And3;
        Side side2And4;
        if (even) { // in even layers, the "length side" will overlap the corners on the width
            side1And3 = createSide(length);
            side2And4 = createSide(width - 4); // withdrawing 2 from each end of the sideLength.
        } else { // in uneven layers, the "width side" will overlap the corners on the length
            side1And3 = createSide2(length - 4); // withdrawing 2 from each end of the sideWidth.
            side2And4 = createSide2(width); 
        }
        layer.setLength(side1And3);
        layer.setWidth(side2And4);
        return layer;
    }

    private static Side createSide(int dots) {
        int fourXtwo = dots / 4; // number of 4x2 bricks
        int twoXtwo = (dots % 4) / 2;
        int oneXtwo = ((dots % 4) - twoXtwo * 2);
        Side side = new Side();
        side.setBricks("fourXtwo", fourXtwo);
        side.setBricks("twoXtwo", twoXtwo);
        side.setBricks("oneXtwo", oneXtwo);
        return side;
    }
    
    private static Side createSide2(int dots) {
        int fourXtwo = dots / 4; // number of 4x2 bricks
        int twoXtwo = (dots % 4) / 2;
        int oneXtwo = ((dots % 4) - twoXtwo * 2);
        Side side = new Side();
        side.setBricks("fourXtwo", fourXtwo);
        side.setBricks("twoXtwo", twoXtwo);
        side.setBricks("oneXtwo", oneXtwo);
        return side;
    }

}
