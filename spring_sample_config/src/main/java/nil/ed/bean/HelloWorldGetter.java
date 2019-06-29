package nil.ed.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class HelloWorldGetter {
    @Autowired
    private HelloWorldBean bean;

    public HelloWorldBean getBean(){
        return bean;
    }
}
