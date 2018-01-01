/**
 * Created by jorge on 10/03/17.
 */
public class Test1 {
    public static int permStringToInt(String permString) {
        int res=0;
        int parUno=0;
        int parDos=0;
        int parTres=0;
        String parteUno = "";
        String parteDos = "";
        String parteTres = "";

        if(permString.length()==9){
            parteUno=permString.substring(0, 3);
            parteDos=permString.substring(3, 6);
            parteTres=permString.substring(6, 9);
        }

        parUno= permisosToInt(parteUno);
        parDos= permisosToInt(parteDos);
        parTres= permisosToInt(parteTres);



        return res;
    }

    public static int permisosToInt(String cadena){
        int res=0;
        int suma=0;
        for (int x=0;x<cadena.length();x++){

            char letra = cadena.charAt(x);
            if( letra=='r' ){
                suma+=4;
            }else if(letra=='w'){
                suma+=2;
            }else if(letra=='x'){
                suma+=1;
            }

        }

        return suma;

    }

    public static void main(String[] args) {
        // Should write 752
        System.out.println(Test1.permStringToInt("rwxr-x-w-"));
    }
}
