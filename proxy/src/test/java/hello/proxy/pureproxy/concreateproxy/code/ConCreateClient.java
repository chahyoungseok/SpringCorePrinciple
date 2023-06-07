package hello.proxy.pureproxy.concreateproxy.code;

public class ConCreateClient {

    private ConCreateLogic conCreateLogic;

    public ConCreateClient(ConCreateLogic conCreateLogic) {
        this.conCreateLogic = conCreateLogic;
    }

    public void execute(){
        conCreateLogic.operation();
    }
}
