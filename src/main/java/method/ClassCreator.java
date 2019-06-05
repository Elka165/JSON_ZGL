package method;

public class ClassCreator {

    public ClassCreator(String className){
        createClass(className);
    }
    public void createClass(String className) {
        try {
            Class.forName(className).newInstance();

        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
