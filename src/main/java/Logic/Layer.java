package Logic;

import java.util.HashMap;

/**
 *
 * @author MadsF
 */
public class Layer {
   private Side length;
   private Side width;

    public Side getLength() {
        return length;
    }

    public void setLength(Side length) {
        this.length = length;
    }

    public Side getWidth() {
        return width;
    }

    public void setWidth(Side Width) {
        this.width = Width;
    }

    @Override
    public String toString() {
        return "Layer{" + "lengthSide=" + length + ", widthSide=" + width + '}';
    }
    
   
   
}
