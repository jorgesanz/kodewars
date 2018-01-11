package candidates;

/**
 * Created by jorge on 30/03/17.
 *
 * Write a function that converts a UNIX permission string into its numerical representation.

 The string consists of 3 blocks of 3 characters each. The returned number will have three digits, one for each block.
 Each digit is the sum of all permissions present in the block:

 CHARACTER	VALUE
 r	4
 w	2
 x	1
 -	0
 For example rwxr-x-w- is decoded as:

 BLOCK	VALUE
 rwx	4+2+1 = 7
 r-x	4+0+1 = 5
 -w-	0+2+0 = 2

 So the returned number would be 752.
 */
public class Permissions {
    public static int permStringToInt(String permString) {
        String a = "rwxr-x-w-";
        String root = a.substring(0, 2);
        String group = a.substring(3, 5);
        String user = a.substring(6, 8);

        int iroot = 0;
        int igroup = 0;
        int iuser = 0;
        int result = 0;
        for(int i=0; i<root.length();i++){
            iroot+=value(root.substring(i));
        }
        result += iroot!=0?iroot*100:0;
        for(int i=0; i<group.length();i++){
            igroup+=value(root.substring(i));
        }
        result += igroup!=0?iroot*10:0;
        for(int i=0; i<user.length();i++){
            iuser+=value(root.substring(i));
        }
        result += iuser!=0?iroot*10:0;
        return result;



    }

    private static int value(String c){
        if(c.equals("r")){
            return 4;
        }
        if(c.equals("w")){
            return 2;
        }
        if(c.equals("x")){
            return 1;
        }
        return 0;
    }

    public static void main(String[] args) {
        // Should write 752
        System.out.println(Permissions.permStringToInt("rwxr-x-w-"));
    }
}
