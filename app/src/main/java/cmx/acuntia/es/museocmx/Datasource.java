package cmx.acuntia.es.museocmx;


import android.content.res.Resources;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class Datasource {

    static List GOYA = new ArrayList<Cuadro>();
    static List VELAZQUEZ = new ArrayList<Cuadro>();
    static List PICASO = new ArrayList<Cuadro>();
    static List VAN_GOGH = new ArrayList<Cuadro>();

    static{


        GOYA.add(new Cuadro("Los fusilamientos del 3 de Mayo","Goya",R.mipmap.fusilamientos,"Los Fusilamientos del 3 de Mayo en la Montaña del Príncipe Pío de Madrid, es uno de los mayores logros de la pintura española y posiblemente un hito de la pintura universal. Es uno de los cuadros de temática histórica más dramáticos de la historia del arte. Goya plasma en el lienzo toda la fuerza y el dramatismo del hecho histórico, con un lenguaje plástico, no visto hasta ese momento y que se anticipa al expresionismo moderno. Los rostros de los fusilados expresan el horror del momento en contraposición de los soldados que aparecen como autómatas sin rostro."));
        GOYA.add(new Cuadro("El 2 de mayo","Goya",R.mipmap.dosdemayo," "));
        GOYA.add(new Cuadro("La familia de Carlos IV","Goya",R.mipmap.carlosiv," "));
        GOYA.add(new Cuadro("La maja desnuda","Goya",R.mipmap.desnuda," "));
        GOYA.add(new Cuadro("La maja vestida","Goya",R.mipmap.vestida," "));
        GOYA.add(new Cuadro("Saturno devorande a un hijo","Goya",R.mipmap.saturno," "));
    }

}

