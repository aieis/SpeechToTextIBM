import com.ibm.watson.developer_cloud.service.security.IamOptions;
import com.ibm.watson.developer_cloud.speech_to_text.v1.SpeechToText;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.RecognizeOptions;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechRecognitionResults;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

public class SpeechToTextIBM {

    private final String API_KEY = "RKJZUgL5ymmvsvsdpDbBZECs1ipzUbL11Os6mBmAK8rc";

    public static void main(String[] args){
        String filename = "D:\\Libraries\\Documents\\Workspaces\\Intellij\\SpeechToTextIBM\\test\\recording2.mp3";

        new SpeechToTextIBM().startAPI(filename, "mp3", new String[1]);

    }
    void startAPI(String filename, String extension, String[] keywords) {

        IamOptions options = new IamOptions.Builder()
                .apiKey(API_KEY)
                .build();

        SpeechToText speechToText = new SpeechToText();
        speechToText.setEndPoint("https://gateway-lon.watsonplatform.net/speech-to-text/api");

        String audioType = "audio/mp3";
        try {
            List<String> files = Arrays.asList(filename);
            for (String file : files) {
                RecognizeOptions recognizeOptions = new RecognizeOptions.Builder()
                        .audio(new File(file))
                        .contentType(audioType)
//                        .timestamps(true)
//                        .wordAlternativesThreshold((float) 0.9)
//                        .keywords(Arrays.asList("colorado", "tornado", "tornadoes"))
//                        .keywordsThreshold((float) 0.5)
                        .build();

                SpeechRecognitionResults speechRecognitionResults =
                        speechToText.recognize(recognizeOptions).execute();
                System.out.println(speechRecognitionResults);
//                speechRecognitionResults.getResults();
//                System.out.println(speechRecognitionResults.getResults().get(0));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
