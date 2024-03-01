package csc435.app;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;

public class Data implements Serializable {
    private ConnectionType type;
    private boolean request;
    private boolean response;
    private boolean status;
    private String filename;
    private HashMap<String, Integer> wordCount;
    private String clientId;
    private boolean isIndexed;
    private Set<String> words;
    private Set<String> result;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Set<String> getWords() {
        return words;
    }

    public void setWords(Set<String> words) {
        this.words = words;
    }

    public Set<String> getResult() {
        return result;
    }

    public void setResult(Set<String> result) {
        this.result = result;
    }

    public String getFilename(){
        return filename;
    }
    public void setFilename(String filename){
        this.filename=filename;
    }

    public HashMap<String, Integer> getWordCount() {
        return wordCount;
    }

    public void setWordCount(HashMap<String, Integer> wordCount) {
        this.wordCount = wordCount;
    }

    public boolean isIndexed() {
        return isIndexed;
    }

    public void setIndexed(boolean indexed) {
        isIndexed = indexed;
    }

    public boolean isStatus(){
        return status;
    }

    public void setStatus(boolean status){
        this.status=status;
    }

    public boolean isRequest() {
        return request;
    }

    public void setRequest(boolean request) {
        this.request = request;
    }

    public boolean isResponse() {
        return response;
    }

    public void setResponse(boolean response) {
        this.response = response;
    }

    public ConnectionType getConnectionType(){
        return this.type;
    }

    public void setConnectionType(ConnectionType type){
        this.type = type;
    }

    @Override
    public String toString() {
        return "Data{" +
                "type=" + type +
                ", request=" + request +
                ", response=" + response +
                ", status=" + status +
                ", filename='" + filename + '\'' +
                ", wordCount=" + wordCount +
                ", isIndexed=" + isIndexed +
                ", words=" + words +
                ", result=" + result +
                '}';
    }
}
