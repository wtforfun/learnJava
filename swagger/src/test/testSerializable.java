import org.junit.Test;
import org.springproject.swagger.entity.Person;
import org.springproject.swagger.util.SerializationUtils;
import sun.security.provider.Sun;

public class testSerializable {
   @Test
    public  void testWrite(){
        Person person=new Person();
        person.setId(1);
        person.setName("张丹");
        SerializationUtils.write(person);
    }

    @Test
    public  void testRead(){
        Person p = (Person) SerializationUtils.read();
        System.out.println(p.getName());
    }

    @Test
    public void test3(){
        System.out.println(Euler(33));

    }

    int Euler(int x)
    {
        int res=x,a=x;
        for(int i=2;i*i<=a;i++){
            if(a%i ==  0){
                res=res/i*(i-1);
                while(a%i == 0) a=a/i;
            }
        }
        if(a>1) res=res/a*(a-1);
        return res;
    }
}