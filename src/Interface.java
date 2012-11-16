import javax.swing.*;
import java.awt.*;

public class Interface extends JPanel{
	public Interface(){
		getTopLevelAncestor();
		this.setSize(Container.WIDTH, getTopLevelAncestor().HEIGHT);
	}
}
