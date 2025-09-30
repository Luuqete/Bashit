package textProcessor;

public class TextProcessor {

    private final int MAX_LINES = 2000;

    private String currentText;

    public TextProcessor(){
        currentText = "";
    }

    public String getInput(String terminalInput){
        return terminalInput.substring(currentText.length());
    }

    public String appendText(String newText){
        currentText += newText;

        if(currentText.lines().count() > MAX_LINES){
            int firstNewLineIndex = currentText.indexOf("\n");
            currentText = currentText.substring(firstNewLineIndex + 1);
        }

        return currentText;
    }

    public String getCurrentText(){
        return currentText;
    }

    public void setCurrentText(String text){
        currentText = text;
    }

    public boolean isOnLimit(String text){
        return  currentText.length() <= text.length() && currentText.equals( text.substring(0, currentText.length()) );
    }

}
