import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args){

        try {
            File f = new File("d://My.txt");
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String s = new String();
            String s1 = new String();
            while((s1=br.readLine())!=null){

                s = s + s1;

            }
            System.out.println(s);
            int count = 0;
            Pattern pat = Pattern.compile("[*]He*");
            Matcher mat = pat.matcher(s);
System.out.println("zfdfd");
            while(mat.find()){
                  if(mat.find()){
                      mat.start();
                      count++;
                  }
            }
           System.out.println(count);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

}