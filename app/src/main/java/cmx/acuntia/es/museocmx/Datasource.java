package cmx.acuntia.es.museocmx;


import java.util.ArrayList;
import java.util.List;


public class Datasource {

    static List GOYA = new ArrayList<Cuadro>();
    static List VELAZQUEZ = new ArrayList<Cuadro>();
    static List PICASO = new ArrayList<Cuadro>();
    static List VAN_GOGH = new ArrayList<Cuadro>();

    static{


        GOYA.add(new Cuadro("Los fusilamientos del 3 de Mayo","Goya",R.mipmap.fusilamientos,"Los Fusilamientos del 3 de Mayo en la Montaña del Príncipe Pío de Madrid, es uno de los mayores logros de la pintura española y posiblemente un hito de la pintura universal. Es uno de los cuadros de temática histórica más dramáticos de la historia del arte. Goya plasma en el lienzo toda la fuerza y el dramatismo del hecho histórico, con un lenguaje plástico, no visto hasta ese momento y que se anticipa al expresionismo moderno. Los rostros de los fusilados expresan el horror del momento en contraposición de los soldados que aparecen como autómatas sin rostro.",R.drawable.fusilamientos));
        GOYA.add(new Cuadro("El 2 de mayo","Goya",R.mipmap.dosdemayo,"El lienzo \"El Dos de Mayo\", conocido también como \"La carga de los mamelucos en la Puerta del Sol de Madrid\", junto con \"Los Fusilamientos del 3 de Mayo\", fueron encargados a Goya después de la guerra de la Independencia en 1814, por la Junta de Regencia, para perpetuar las acciones heroicas del pueblo de Madrid contra las tropas napoleónicas. Estas dos obras son consideradas de forma unánime las mejores de toda su producción. ",R.drawable.dosdemayo));
        GOYA.add(new Cuadro("La familia de Carlos IV","Goya",R.mipmap.carlosiv," \"La Familia de Carlos IV\", oleo sobre lienzo, pintado por Goya en 1800. Es un retrato colectivo de la familia real, que se conserva en el Museo del Prado. El cuadro, es la culminación de Goya, después de una amplia labor como retratista. Es una de sus composiciones más complejas. ",R.drawable.familiacarlosiv));
        GOYA.add(new Cuadro("La maja desnuda","Goya",R.mipmap.desnuda,"\"La Maja Desnuda\", es una de las más celebres obras de Goya. Pintada antes de 1800, luego formaría pareja con \"La Maja Vestida\". Se especula que el retrato represente a la Duquesa de Alba. Es una obra audaz y atrevida para su época, como lo es la expresión del rostro y la actitud corporal de la modelo, que parece sonreír satisfecha de sus gracias. ",R.drawable.majadesnuda));
        GOYA.add(new Cuadro("La maja vestida","Goya",R.mipmap.vestida," Es una de las obras más conocidas de Goya, realizado en oleo sobre lienzo de 95 por 188 cm. Fue pintado entre 1802 y 1808 y se encuentra en el Museo del Prado. Es hermano de \"La Maja Desnuda\". ",R.drawable.majavestida));
        GOYA.add(new Cuadro("Saturno devorande a un hijo","Goya",R.mipmap.saturno," Es una de las pinturas al oleo sobre revoco, que formó parte de la decoración de los muros de la casa que Goya adquirió en 1819, llamada la Quinta del Sordo. La obra pertenece a la serie de Pinturas Negras. ",R.drawable.saturno));
    }

}

