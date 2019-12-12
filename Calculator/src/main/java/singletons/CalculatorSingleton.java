package singletons;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;

//@Startup en despliegue
@LocalBean
@Singleton(name = CalculatorSingleton.BEAN_NAME)
public class CalculatorSingleton {

    public static final String BEAN_NAME = "calculatorSingleton";

    private int operationsInServer;

    @PostConstruct
    public void initSingleton() {
        this.operationsInServer = 0;
    }

    public int getOperationsInServer() {
        return operationsInServer;
    }

    public void setOperationsInServer(int operationsInServer) {
        this.operationsInServer = operationsInServer;
    }

    public void increase() { this.operationsInServer++; }

    @Override
    public String toString() {
        return String.valueOf(this.operationsInServer);
    }
}
