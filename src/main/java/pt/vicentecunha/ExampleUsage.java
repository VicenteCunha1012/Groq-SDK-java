package pt.vicentecunha;

public class ExampleUsage {
    public static void main(String[] args) {
        final String API_KEY = "123";

        GroqClient client = new GroqClient(API_KEY);

        System.out.println(client.singleQuery("answer this in under 100 words", "What is the largest ocean in the world?"));

        System.out.println(client.singleQuery("What is the largest ocean in the world?"));
    }
}
